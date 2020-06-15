package com.phone.api.provider.vodafone;

import com.phone.api.provider.IProvider;

public class Vodafone implements IProvider {

	@Override
	public void send(String mobile, String message) {
		System.out.println("Vodafone");
	}

}
