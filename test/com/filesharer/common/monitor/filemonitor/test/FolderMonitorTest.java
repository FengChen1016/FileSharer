package com.filesharer.common.monitor.filemonitor.test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.filesharer.common.monitor.filemonitor.FolderMonitor;

public class FolderMonitorTest {

	public static void main(String[] args) {
		//Path path = new File("C:/WorkFolder/TestFolder/test/1.txt").toPath();
		Path path = Paths.get("C:/WorkFolder/TestFolder/test");
		FolderMonitor monitor = new FolderMonitor(path);
		try {
			monitor.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			Thread.sleep(6*60*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		monitor.stop();

	}

}
