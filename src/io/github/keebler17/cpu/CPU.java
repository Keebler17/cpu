package io.github.keebler17.cpu;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

import io.github.keebler17.cpu.control.Instruction;
import io.github.keebler17.cpu.control.InstructionType;
import io.github.keebler17.cpu.control.MicrocodeExecutor;
import io.github.keebler17.cpu.ram.RAM;
import io.github.keebler17.cpu.register.ALU;
import io.github.keebler17.cpu.register.ArgumentRegister;
import io.github.keebler17.cpu.register.InstructionRegister;
import io.github.keebler17.cpu.register.MARegister;
import io.github.keebler17.cpu.register.OutputRegister;
import io.github.keebler17.cpu.register.ProgramCounter;
import io.github.keebler17.cpu.register.Register;

 // 0x03 0x06 0x08 0x00 0x14 0x00 0x48 EOF

public class CPU {
	private static FileInputStream program;
	
	public static Register a;
	public static Register b;
	public static OutputRegister o;
	public static ALU alu;
	public static ProgramCounter pc;
	public static MARegister mar;
	public static InstructionRegister ir;
	public static ArgumentRegister ar;
	
	public static void main(String[] args) throws IOException {
		
		Scanner s = new Scanner(System.in);
		System.out.println("Have you pre-loaded a program? [Y/n]");
		if(s.nextLine().equalsIgnoreCase("n")) {
			File f = new File("program");
			if(!f.delete() || !f.createNewFile()) System.exit(1);
			
			System.out.println("Input hexadecimal unsigned bytes to write to the program. When you are done, type EOF. Other inputs will be rejected.");
			FileOutputStream out = new FileOutputStream(f);
			System.out.println("0x00: ");
			String in = s.nextLine();
			int p = 1;
			while(!in.equalsIgnoreCase("EOF")) {
				try {
					if(Integer.valueOf(in, 16) < 0 || 255 < Integer.valueOf(in, 16)) throw new IllegalArgumentException();
					if(127 < Integer.valueOf(in, 16)) in = String.valueOf(Integer.valueOf(in, 16)-0xFF);
					out.write(Byte.valueOf(in, 16));
				} catch(Exception e) {
					System.err.println("Invalid input.");
				}
				System.out.println("0x" + Integer.toHexString(p++) + ": ");
				in = s.nextLine();
			}
			
			out.flush();
			out.close();
			s.close();
		}
		
		program = new FileInputStream(new File("program"));
		
		System.out.println("Reading program...");
		int ramPointer = 0x00;
		Byte programByte = readByte();
		
		byte[] ram = new byte[RAM.RAM_SIZE];
		
		while(programByte != null) {
			ram[ramPointer++] = programByte.byteValue();
			programByte = readByte();
		}
		
		System.out.print("\n");
		
		// initializing components
		
		a = new Register();
		b = new Register();
		o = new OutputRegister();
		alu = new ALU(a, b);
		pc = new ProgramCounter();
		
		RAM.initializeRAM(ram);
		mar = new MARegister();
		
		ir = new InstructionRegister();
		ar = new ArgumentRegister();
		
		while(true) {
			MicrocodeExecutor.executeMicrocode();
		}
		
//		RAM.dumpHumanReadable();
	}
	
	private static Instruction readLine() throws IOException  {
		int b1 = program.read();
		int b2 = program.read();
		System.out.print(Integer.toHexString(b1) + " ");
		System.out.print(Integer.toHexString(b2) + " ");
		if(b1 == -1 || b2 == -1) return null;
		return new Instruction(InstructionType.values()[b1], b2);
	}
	
	private static Byte readByte() throws IOException {
		int b = program.read();
		System.out.print(Integer.toHexString(b) + " ");
		if(b == -1) return (Byte) null;
		return (byte) b;
	}
	
	public static void exit() {
		System.out.println("End of program reached. Terminating.");
		RAM.dumpHumanReadable();
		System.exit(0);
	}
}
