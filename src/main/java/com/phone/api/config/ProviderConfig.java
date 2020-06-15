package com.phone.api.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.phone.api.provider.IProvider;
import com.phone.api.provider.movistar.Movistar;
import com.phone.api.provider.orange.Orange;
import com.phone.api.provider.vodafone.Vodafone;

@Configuration
public class ProviderConfig {

	@Bean
	public Movistar movistar() {
		return new Movistar();
	}

	@Bean
	public Orange orange() {
		return new Orange();
	}

	@Bean
	public Vodafone vodafone() {
		return new Vodafone();
	}

	@Bean
	public Map<String, IProvider> mapProvider() {
		Map<String, IProvider> providers = new HashMap<>();
		providers.put("P1", movistar());
		providers.put("P2", vodafone());
		providers.put("P3", orange());
		return providers;
	}

}
