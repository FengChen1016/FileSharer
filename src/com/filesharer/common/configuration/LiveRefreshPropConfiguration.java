package com.filesharer.common.configuration;

import com.filesharer.common.monitor.ChangeEvent;
import com.filesharer.common.monitor.Listener;
import com.filesharer.common.monitor.Monitor;
import com.filesharer.common.monitor.Observer;
import com.filesharer.common.monitor.Subject;
import com.filesharer.common.monitor.filemonitor.FileSubject;

public class LiveRefreshPropConfiguration implements IConfiguration {
	
	private final PropConfiguration propConfig;
	private long refreshInterval = 10 * 1000; // default: 10 seconds
	private final Monitor monitor = new Monitor(refreshInterval);
	
	public LiveRefreshPropConfiguration(String path) {
		this.propConfig = new PropConfiguration(path);
		initMonitor();
	}
	
	public LiveRefreshPropConfiguration(String path, long refreshInterval) {
		this.propConfig = new PropConfiguration(path);
		this.refreshInterval = refreshInterval;
		initMonitor();
	}
	
	public LiveRefreshPropConfiguration(PropConfiguration propConfig) {
		this.propConfig = propConfig;
		initMonitor();
	}
	
	public LiveRefreshPropConfiguration(PropConfiguration propConfig, long refreshInterval) {
		this.propConfig = propConfig;
		this.refreshInterval = refreshInterval;
		initMonitor();
	}
	
	private void initMonitor() {
		Subject subject = new FileSubject(propConfig.getPath());
		Observer observer = new Observer(subject);
		observer.addListener(new Listener() {
			@Override
			public void onChange(Subject subject, ChangeEvent changeEvent) {
				reload();  // just reload configuration
			}
		});
		monitor.addObserver(observer);
	}
	
	public void start() {
		monitor.start();
	}
	
	public void stop() {
		monitor.stop();
	}
	
	public void reload() {
		propConfig.reload();
	}

	@Override
	public void load() {
		propConfig.load();
		
	}

	@Override
	public String getStringValue(String key) {
		return propConfig.getStringValue(key);
	}

	@Override
	public String getStringValue(String key, String defaultValue) {
		return propConfig.getStringValue(key, defaultValue);
	}

	@Override
	public boolean getBooleanValue(String key) {
		return propConfig.getBooleanValue(key);
	}

	@Override
	public boolean getBooleanValue(String key, boolean defaultValue) {
		return propConfig.getBooleanValue(key, defaultValue);
	}

	@Override
	public int getIntValue(String key) {
		return propConfig.getIntValue(key);
	}

	@Override
	public int getIntValue(String key, int defaultValue) {
		return propConfig.getIntValue(key, defaultValue);
	}

	@Override
	public void setValue(String key, String value) {
		propConfig.setValue(key, value);
		
	}

	@Override
	public void setValue(String key, boolean value) {
		propConfig.setValue(key, value);
	}

	@Override
	public void setValue(String key, int value) {
		propConfig.setValue(key, value);
	}

	@Override
	public String[] getStringArray(String key) {
		return propConfig.getStringArray(key);
	}

	@Override
	public void setStringArray(String key, String[] value) {
		propConfig.setStringArray(key, value);
	}

	@Override
	public void save() {
		propConfig.save();
	}

}
