package io.github.keebler17.cpu.register;

import io.github.keebler17.cpu.Bus;
import io.github.keebler17.cpu.Clock;
import io.github.keebler17.cpu.ClockListener;

public class Register implements ClockListener {
	protected byte data = (byte)0;
	
	protected boolean enable = false;
	protected boolean load = false;
	
	
	public Register() {
		Clock.registerClock(this);
	}
	
	public void clock() {
		if(load) load();
		if(enable) enable();
	}
	
	public final void setEnable(boolean enable) {
		this.enable = enable;
		
		if(enable) enable();
	}
	
	public final void setLoad(boolean load) {
		this.load = load;
	}
	
	protected void enable() {
		Bus.write(data);
	}
	
	protected void load() {
		data = Bus.read();
	}
	
}
