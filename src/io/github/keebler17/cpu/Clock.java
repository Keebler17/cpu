package io.github.keebler17.cpu;

import java.util.ArrayList;

public class Clock {
	private static ArrayList<ClockListener> clockListeners = new ArrayList<ClockListener>();
	
	public static void registerClock(ClockListener obj) {
		clockListeners.add(obj);
	}
	
	public static void clock() {
		for(ClockListener listener : clockListeners) {
			listener.clock();
		}
	}
}
