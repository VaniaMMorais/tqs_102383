package com.tqsenvmonitor.envmonitor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql. Statement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EnvmonitorApplication {

	public static void main(String[] args) {
		SpringApplication.run(EnvmonitorApplication.class, args);
	}
}
