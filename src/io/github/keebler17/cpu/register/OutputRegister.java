package io.github.keebler17.cpu.register;

public class OutputRegister extends Register {
	public OutputRegister() {
		super();
	}
	
	@Override
	protected void enable() {
		System.out.print((char)data);
	}
}
