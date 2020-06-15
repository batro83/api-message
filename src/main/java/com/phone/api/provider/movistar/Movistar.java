package com.phone.api.provider.movistar;

import com.phone.api.provider.IProvider;

public class Movistar implements IProvider {

	@Override
	public void send(String mobile, String message) {
		System.out.println("Movistar");
	}

}
