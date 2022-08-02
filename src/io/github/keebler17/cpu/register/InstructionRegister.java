package io.github.keebler17.cpu.register;

import io.github.keebler17.cpu.control.MicrocodeExecutor;

public class InstructionRegister extends Register {
	public InstructionRegister() {
		super();
	}
	
	@Override
	protected void enable() {
		MicrocodeExecutor.setInstruction(data);
	}
}
