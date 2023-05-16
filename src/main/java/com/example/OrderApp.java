package com.example;

import java.sql.SQLException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import com.example.model.Order;
import com.example.model.User;
import com.example.service.OrderService;
import com.example.service.UserService;

public class OrderApp {
	static Scanner scanner = new Scanner(System.in);

	public static boolean isUserExists(UserService userService) throws SQLException {
		for (int i = 0; i < 3; i++) {
			System.out.println("Enter User id");
			int userId = scanner.nextInt();
			System.out.println("Enter User password");
			String password = scanner.next();
			User userInput = new User(userId, "", password);
			if (userService.isUserExists(userInput)) {
				System.out.println("user exists");
				return true;

			} else {
				System.out.println("incorrect password try again");
			}
		}
		return false;
	}

	public static void processOrder(OrderService orderService) throws SQLException {
		int choice;
		do {
			System.out.println("1. Create Order");
			System.out.println("2. View All Orders");
			System.out.println("3. View Orders By Id");
			System.out.println("4. Update Order By Name");
			System.out.println("5. Update Order By Price");
			System.out.println("6. Maximum Order By Id");
			System.out.println("7. Minimum All Orders");
			System.out.println("8. Delete Order By Id");
			System.out.println("9. Delete All Orders");
			System.out.println("10. Exit");

			System.out.println("Enter your choice.");
			choice = Integer.parseInt(scanner.next());
			switch (choice) {
			case 1:
				System.out.print("Enter Order Name ");
				String orderName = scanner.next();
				System.out.print("Enter Order Price ");
				int orderPrice = scanner.nextInt();

				Order order = orderService.createOrder(new Order(new Random().nextInt(1000), orderName, orderPrice));

				System.out.println("Order Created: " + order);
				break;
			case 2:

				List<Order> OrderList = orderService.getAllOrders();
				for (Order c : OrderList) {
					System.out.println(c);
				}
				break;
			case 3:
				System.out.print("Enter OrderId: ");
				int orderId = scanner.nextInt();
				Order order1 = orderService.findOrderByID(orderId);
				if (order1 != null) {
					System.out.println("Order details are: " + order1);
				} else {
					System.err.println("No such Order found");
				}
				break;
			case 4:
				Order updateOrderByName = new Order();

				System.out.print("Enter Order Id: ");
				int orderId1 = scanner.nextInt();
				updateOrderByName.setOrderId(orderId1);
				System.out.print("Enter Order Name to Update: ");
				String orderName1 = scanner.next();
				updateOrderByName.setOrderName(orderName1);

				orderService.updateOrderByName(updateOrderByName);

				System.out.println("Order Name updated!");

				break;
			case 5:
				Order updateOrderByPrice = new Order();

				System.out.print("Enter Order Id: ");
				updateOrderByPrice.setOrderId(scanner.nextInt());

				System.out.print("Enter Order Price to Update: ");
				updateOrderByPrice.setOrderName(scanner.next());

				orderService.updateOrderByPrice(updateOrderByPrice);

				System.out.println("Order Price updated!");
				break;
			case 6:
				System.out.println("Maximum Order!");

				System.out.println(orderService.getMaxOrderPrice());
				break;
			case 7:
				System.out.println("Minimum Order!");

				System.out.println(orderService.getMinOrderPrice());
				break;
			case 8:
				System.out.println("Enter the order Id to delete!");
				int deleteOrderById = scanner.nextInt();

				orderService.deleteOderById(deleteOrderById);
				System.out.println("Order Deleted!");
				break;
			case 9:
				System.out.println("Deleting All the Orders!");

				orderService.deleteAllOrders();
				break;

			}
		} while (choice != 0);

	}

}
