package io.github.keebler17.cpu.control;

import io.github.keebler17.cpu.CPU;
import io.github.keebler17.cpu.Clock;
import io.github.keebler17.cpu.ram.RAM;
import io.github.keebler17.cpu.register.Register;

public class MicrocodeExecutor {
	public static void executeMicrocode() {
		CPU.ir.setEnable(true);
		switch(InstructionType.values()[instruction]) {
		case NOP:
			CPU.pc.setEnable(true);
			CPU.mar.setLoad(true);
			Clock.clock();
			CPU.pc.setEnable(false);
			CPU.mar.setLoad(false);
			CPU.ir.setLoad(true);
			CPU.mar.setEnable(true);
			RAM.read();
			Clock.clock();
			CPU.mar.setEnable(false);
			CPU.ir.setLoad(false);
			
			CPU.pc.increment();
			CPU.pc.setEnable(true);
			CPU.mar.setLoad(true);
			Clock.clock();
			CPU.pc.setEnable(false);
			CPU.mar.setLoad(false);
			CPU.ar.setLoad(true);
			CPU.mar.setEnable(true);
			RAM.read();
			Clock.clock();
			CPU.mar.setEnable(false);
			CPU.ar.setLoad(false);
			
			CPU.ir.setEnable(true);
			return;
		case LDA:
			ldX(CPU.b);
			break;
		case LDB:
			ldX(CPU.b);
			break;
		case LDO:
			ldX(CPU.o);
			break;
		case STA:
			stX(CPU.a);
			break;
		case STB:
			stX(CPU.b);
			break;
		case STO:
			stX(CPU.o);
			break;
		case ADD:
			CPU.alu.setEnable(true);
			CPU.a.setLoad(true);
			Clock.clock();
			CPU.alu.setEnable(false);
			CPU.a.setLoad(false);
			break;
		case OUT:
			CPU.o.setEnable(true);
			CPU.o.setEnable(false);
			break;
		case JMP:
//			CPU.ar.setEnable(true);
//			CPU.mar.setLoad(true);
//			Clock.clock();
//			CPU.mar.setEnable(true);
//			RAM.read();
//			CPU.pc.setLoad(true);
//			Clock.clock();
//			CPU.mar.setEnable(false);
//			CPU.pc.setLoad(false);
			break;
		case HLT:
			break;
		case EXIT:
			CPU.exit();
			
		}
		
		CPU.pc.increment();
		CPU.pc.setEnable(true);
		CPU.mar.setLoad(true);
		Clock.clock();
		CPU.pc.setEnable(false);
		CPU.mar.setLoad(false);
		CPU.ir.setLoad(true);
		CPU.mar.setEnable(true);
		RAM.read();
		Clock.clock();
		CPU.mar.setEnable(false);
		CPU.ir.setLoad(false);
		
		CPU.pc.increment();
		CPU.pc.setEnable(true);
		CPU.mar.setLoad(true);
		Clock.clock();
		CPU.pc.setEnable(false);
		CPU.mar.setLoad(false);
		CPU.mar.setEnable(true);
		CPU.ar.setLoad(true);
		RAM.read();
		CPU.mar.setEnable(false);
		Clock.clock();
		CPU.ar.setLoad(false);
	}
	
	private static void ldX(Register x) {
		CPU.ar.setEnable(true);
		CPU.mar.setLoad(true);
		Clock.clock();
		
		CPU.ar.setEnable(false);
		CPU.mar.setLoad(false);
		CPU.mar.setEnable(true);
		RAM.read();
		x.setLoad(true);
		Clock.clock();
		CPU.mar.setEnable(false);
		x.setLoad(false);
	}
	
	private static void stX(Register x) {
		CPU.ar.setEnable(true);
		CPU.mar.setLoad(true);
		Clock.clock();
		
		CPU.ar.setEnable(false);
		CPU.mar.setLoad(false);
		CPU.mar.setEnable(true);
		x.setEnable(true);
		RAM.write();
		CPU.mar.setEnable(false);
		x.setEnable(false);
	}
	
	public static void setInstruction(byte _instruction) {
		instruction = _instruction;
	}
	
	private static byte instruction;
}
