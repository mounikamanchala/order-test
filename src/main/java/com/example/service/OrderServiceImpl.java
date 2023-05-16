package com.example.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.dao.OrderDao;
import com.example.model.Order;

@Component("orderService")
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderDao orderDao;

	@Override
	public Order createOrder(Order order) throws SQLException {
		return orderDao.createOrder(order);
	}

	@Override
	public List<Order> getAllOrders() throws SQLException {
		return orderDao.getAllOrders();
	}

	@Override
	public Order findOrderByID(int orderId) throws SQLException {
		return orderDao.findOrderByID(orderId);
	}

	@Override
	public void deleteAllOrders() throws SQLException {
		orderDao.deleteAllOrders();
	}

	@Override
	public void deleteOderById(int orderId) throws SQLException {
		orderDao.deleteOderById(orderId);
	}

	@Override
	public void updateOrderByName(Order order) throws SQLException {
		orderDao.updateOrderByName(order);
	}

	@Override
	public void updateOrderByPrice(Order order) throws SQLException {
		orderDao.updateOrderByPrice(order);
	}

	@Override
	public int getMaxOrderPrice() throws SQLException {
		return orderDao.getMaxOrderPrice();
	}

	@Override
	public int getMinOrderPrice() throws SQLException {
		return orderDao.getMinOrderPrice();
	}

}
