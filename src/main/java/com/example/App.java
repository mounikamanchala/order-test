package com.example;

import java.sql.SQLException;
import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.example.config.SpringConfig;
import com.example.service.OrderService;
import com.example.service.UserService;

/**
 * Hello world!
 *
 */
public class App {
	static Scanner scanner = new Scanner(System.in);
	static ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);

	static OrderService orderService = context.getBean("orderService", OrderService.class);
	static UserService userService = context.getBean("userService", UserService.class);

	public static void main(String[] args) throws SQLException {
		System.out.println("*** Order Management ****");
		if (OrderApp.isUserExists(userService)) {
			OrderApp.processOrder(orderService);
		} else {
			System.out.println("incorrect credentials");
		}
	}

}
