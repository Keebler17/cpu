package io.github.keebler17.cpu.ram;

import java.io.IOException;
import java.io.OutputStream;

import io.github.keebler17.cpu.Bus;

public class RAM {
	public final static int RAM_SIZE = 256;
	
	private static byte[] ram = new byte[RAM_SIZE];
	
	private static int ramInput = 0;
	
	public static void initializeRAM() {
		for(int i = 0; i < RAM_SIZE; i++) {
			ram[i] = (byte)0;
		}
	}
	
	public static void initializeRAM(byte[] _ram) {
		ram = _ram;
	}
	
	public static void read() {
		Bus.write(ram[ramInput]);
	}
	
	public static void write() {
		ram[ramInput] = Bus.read();
	}
	
	public static void dump(OutputStream out) {
		try {
			out.write(ram);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void dumpHumanReadable() {
		for(int i = 0; i < RAM_SIZE; i++) {
			System.out.print(Integer.toHexString((int)ram[i]) + " ");
		}
	}
	
	public static void in(byte in) {
		ramInput = in;
	}
}
