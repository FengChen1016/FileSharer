package com.filesharer.common.user;

public class AnonymousAuthenticator implements Authenticator {

	@Override
	public boolean authenticate() {
		return true;
	}
	
}
