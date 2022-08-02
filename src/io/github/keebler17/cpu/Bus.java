package io.github.keebler17.cpu;

public class Bus {
	private static byte data;
	
	public static void clear() {
		data = (byte) 0;
	}
	
	public static void write(byte _data) {
		data = _data;
	}
	
	public static byte read() {
		return data;
	}
}
