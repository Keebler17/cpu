package io.github.keebler17.cpu.register;

public class ProgramCounter extends Register {
	public ProgramCounter() {
		super();
	}
	
	public void increment() {
		data++;
	}
}
