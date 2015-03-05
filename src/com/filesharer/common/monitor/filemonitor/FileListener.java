package com.filesharer.common.monitor.filemonitor;

import com.filesharer.common.monitor.ChangeEvent;
import com.filesharer.common.monitor.Listener;
import com.filesharer.common.monitor.Subject;

public class FileListener implements Listener {

	@Override
	public void onChange(Subject subject, ChangeEvent changeEvent) {
		
		System.out.println(((FileSubject)subject).getKey() + " changed. Change event:" + changeEvent);
		switch (changeEvent) {
			case CREATE: 
				onFileCreate((FileSubject)subject);
				break;
			case DELETE:
				onFileDelete((FileSubject)subject);
				break;
			case CHANGE:
				onFileChange((FileSubject)subject);
				break;
			default:
				System.out.print("Unknown change event.");
		}
		
	}

	private void onFileChange(FileSubject subject) {
		System.out.println("File changed.");
		
	}

	private void onFileDelete(FileSubject subject) {
		System.out.println("File deleted.");
		
	}

	private void onFileCreate(FileSubject subject) {
		System.out.println("File created.");
		
	}

}
