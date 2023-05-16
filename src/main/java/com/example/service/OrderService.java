package com.example.service;

import java.sql.SQLException;
import java.util.List;

import com.example.model.Order;

public interface OrderService {
	public Order createOrder(Order order) throws SQLException;

	public List<Order> getAllOrders() throws SQLException;

	public Order findOrderByID(int orderId) throws SQLException;

	public void deleteAllOrders() throws SQLException;

	public void deleteOderById(int orderId) throws SQLException;

	public void updateOrderByName(Order order) throws SQLException;

	public void updateOrderByPrice(Order order) throws SQLException;

	public int getMaxOrderPrice() throws SQLException;

	public int getMinOrderPrice() throws SQLException;
}
