package com.xhSmart.test;


import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import java.lang.management.ThreadMXBean;

import java.util.TimerTask;



public class TestTimeTnterceptor {

	/**
	 * @param args
	 */
	static class Mytask extends TimerTask{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			System.out.println(Runtime.getRuntime().maxMemory());
			System.out.println(Runtime.getRuntime().freeMemory());
			System.out.println(Runtime.getRuntime().totalMemory());

		}
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MemoryMXBean memorymbean = ManagementFactory.getMemoryMXBean();
		MemoryUsage usage = memorymbean.getHeapMemoryUsage();
		System.out.println("INIT HEAP: " + usage.getInit());
		System.out.println("MAX HEAP: " + usage.getMax());
		System.out.println("USE HEAP: " + usage.getUsed());
		System.out.println("\nFull Information:");
		System.out.println("Heap Memory Usage: "
	    +memorymbean.getHeapMemoryUsage());
		System.out.println("Non-Heap Memory Usage: "
		+memorymbean.getNonHeapMemoryUsage());
		System.out.println("ThreadMXBean");
		ThreadMXBean tm=(ThreadMXBean)ManagementFactory.getThreadMXBean();
		System.out.println("getThreadCount"+tm.getThreadCount());
		System.out.println("getPeakThreadCount"+tm.getPeakThreadCount());
		System.out.println("getCurrentThreadCpuTime"+tm.getCurrentThreadCpuTime());
		System.out.println("getDaemonThreadCount"+tm.getDaemonThreadCount());
		System.out.println("getCurrentThreadUserTime"+tm.getCurrentThreadUserTime());
	}
	

}
