package com.howtodoinjava.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.howtodoinjava.nk.CassRepoAndServiceLoaderFactory;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
//		CassRepoAndServiceLoaderFactory.launchFactory();
		SpringApplication.run(DemoApplication.class, args);
//		try {
//			Thread.sleep(1000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		try {
//			CassRepoAndServiceLoaderFactory.factory().modelSetups.get("EmployeeEntity").svcs.getAll();
//		}catch (NullPointerException e) {
//			System.err.println(e.getMessage());
//		}
//		
	}

}
