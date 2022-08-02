package io.github.keebler17.cpu.register;

import io.github.keebler17.cpu.Bus;

public class ALU extends Register {
	private Register a;
	private Register b;
	
	public ALU(Register a, Register b) {
		super();
	}
	
	@Override
	protected void enable() {
		Bus.write((byte)(a.data + b.data));
	}
}
