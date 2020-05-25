package com.task;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"com.task"})
@EntityScan("com.task")
@EnableJpaRepositories("com.task")
@EnableDiscoveryClient
public class TaskApplication extends SpringBootServletInitializer {

	public static void main(String args[]) {
		SpringApplication.run(TaskApplication.class, args);
	}
}
