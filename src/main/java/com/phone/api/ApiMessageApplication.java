package com.phone.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import com.phone.api.dao.ProviderDao;
import com.phone.api.model.Provider;

@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class ApiMessageApplication implements CommandLineRunner {

	@Autowired
	private ProviderDao providerDao;

	public static void main(String[] args) {
		SpringApplication.run(ApiMessageApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		providerDao.deleteAll();
		Provider provider1 = Provider.builder().id("1").name("P1").prefix("0034").cost(1).build();
		providerDao.save(provider1);
		Provider provider2 = Provider.builder().id("2").name("P2").prefix("0034").cost(2).build();
		providerDao.save(provider2);
		Provider provider3 = Provider.builder().id("3").name("P3").prefix("0034").cost(1).build();
		providerDao.save(provider3);
		Provider provider4 = Provider.builder().id("4").name("P3").prefix("0033").cost(3).build();
		providerDao.save(provider4);
	}

}
