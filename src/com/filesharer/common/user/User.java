package com.filesharer.common.user;

public class User {
	private String name;
	private String ip;
	
	private static final User unauthenticatedUser = new User(null, null);
	
	public static final ThreadLocal<User> current = new ThreadLocal<User>() {
		@Override
		protected User initialValue() {
			return unauthenticatedUser;
		}
	};
	
	public User(String name, String ip) {
		this.name = name;
		this.ip = ip;
	}
	
	public void login(String name, String ip, Authenticator authenticator) {
		if (authenticator.authenticate()) {
			current.set(new User(name, ip));
		}
	}
	
	public User current() {
		return current.get();
	}
	
	public void logout() {
		current.set(unauthenticatedUser);
	}
	
	public String getUserName() {
		return name;
	}
	
	public String getIP() {
		return ip;
	}
	
	public boolean isAuthenticated() {
		return current.get() != unauthenticatedUser;
	}
}
