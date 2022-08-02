package io.github.keebler17.cpu.register;

import io.github.keebler17.cpu.ram.RAM;

public class MARegister extends Register {
	
	public MARegister() {
		super();
		enable = true;
	}
	
	@Override
	public void clock() {
		super.clock();
		enable();
	}
	
	@Override
	protected void enable() {
		RAM.in(data);
	}
}
