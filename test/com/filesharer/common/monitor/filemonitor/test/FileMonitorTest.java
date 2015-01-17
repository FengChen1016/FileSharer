package com.filesharer.common.monitor.filemonitor.test;

import com.filesharer.common.monitor.Monitor;
import com.filesharer.common.monitor.Observer;
import com.filesharer.common.monitor.filemonitor.FileListener;
import com.filesharer.common.monitor.filemonitor.FileSubject;

public class FileMonitorTest {
	
	public static void main(String[] args) {
		Monitor fm = new Monitor(3000);
		Observer ob = new Observer(new FileSubject("C:/WorkFolder/TestFolder/test/1.txt"));
		ob.addListener(new FileListener());
		fm.addObserver(ob);
		//
		fm.start();

		try {
			Thread.sleep(60 * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		fm.stop();
		
		try {
			Thread.sleep(10 * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("exit!");
	}
}
