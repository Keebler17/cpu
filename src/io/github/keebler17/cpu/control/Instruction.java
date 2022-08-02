package io.github.keebler17.cpu.control;

public class Instruction {
	private InstructionType type;
	private int argument;
	
	public Instruction(InstructionType type, int argument) {
		this.type = type;
		this.argument = argument;
	}
	
	public InstructionType getInstructionType() {
		return type;
	}
	
	public int getArgument() {
		return argument;
	}
}
