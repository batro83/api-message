package com.phone.api.provider.orange;

import com.phone.api.provider.IProvider;

public class Orange implements IProvider {

	@Override
	public void send(String mobile, String message) {
		System.out.println("Vodafone");
	}
}
