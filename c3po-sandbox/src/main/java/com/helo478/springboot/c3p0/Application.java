package com.helo478.springboot.c3p0;

import java.beans.PropertyVetoException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@SpringBootApplication
public class Application {

	private static final int MAX_IDLE_TIME = 0;
	private static final int MIN_POOL_SIZE = 25;
	private static final int MAX_POOL_SIZE = 50;

	@Autowired
	private HelloRepository helloRepository;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {

			helloRepository.createTable();

			helloRepository.putHello("Hello, World!");
			helloRepository.putHello("Hello to you");
			helloRepository.putHello("Hail and well met");
		};
	}

	@Bean
	public ComboPooledDataSource dataSource() throws PropertyVetoException {
		ComboPooledDataSource dataSource = new ComboPooledDataSource();
		dataSource.setMinPoolSize(MIN_POOL_SIZE);
		dataSource.setMaxPoolSize(MAX_POOL_SIZE);
		dataSource.setMaxIdleTime(MAX_IDLE_TIME);
		dataSource.setJdbcUrl("jdbc:h2:mem:AZ;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE");
		dataSource.setPassword("");
		dataSource.setUser("sa");
		dataSource.setDriverClass("org.h2.Driver");
		return dataSource;
	}

}
