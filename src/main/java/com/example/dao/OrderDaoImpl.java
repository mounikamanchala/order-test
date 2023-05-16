package com.example.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.model.Order;

@Component("oderDao")
public class OrderDaoImpl implements OrderDao {

	public final DataSource dataSource;

	@Autowired
	public OrderDaoImpl(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public Order createOrder(Order order) throws SQLException {
		Connection connection = dataSource.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement("insert into order_data values(?,?,?)");
		preparedStatement.setInt(1, order.getOrderId());
		preparedStatement.setString(2, order.getOrderName());
		preparedStatement.setInt(3, order.getOrderPrice());

		preparedStatement.executeUpdate();
		return order;
	}

	@Override
	public List<Order> getAllOrders() throws SQLException {
		List<Order> list = new ArrayList<Order>();

		Connection connection = dataSource.getConnection();
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery("select * from order_data");
		while (resultSet.next()) {
			list.add(new Order(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3)));

		}

		return list;

	}

	@Override
	public Order findOrderByID(int orderId) throws SQLException {
		Order order = null;
		Connection connection = dataSource.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement("select * from order_data where order_id=?");
		preparedStatement.setInt(1, orderId);
		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			order = new Order(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3));
		}
		return order;
	}

	@Override
	public void deleteAllOrders() throws SQLException {
		Connection connection = dataSource.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement("delete from order_data ");
		int count = preparedStatement.executeUpdate();
		if (count > 0) {
			System.out.println("orders deleted successfully :: ");
		}
	}

	@Override
	public void deleteOderById(int orderId) throws SQLException {
		Connection connection = dataSource.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement("delete from order_data where order_id=?");
		preparedStatement.setInt(1, orderId);
		int count = preparedStatement.executeUpdate();
		if (count > 0) {
			System.out.println("order deleted with ID :: " + orderId);
		} else {
			System.out.println("order not found");
		}

	}

	@Override
	public void updateOrderByName(Order order) throws SQLException{
		Connection connection = dataSource.getConnection();
		PreparedStatement preparedStatement=connection.prepareStatement("update order_data set order_name=? where order_id=?");
		
		preparedStatement.setString(1, order.getOrderName());
		preparedStatement.setInt(2, order.getOrderId());
	
		preparedStatement.executeUpdate();

	}

	@Override
	public void updateOrderByPrice(Order order) throws SQLException{
		Connection connection = dataSource.getConnection();
		PreparedStatement preparedStatement=connection.prepareStatement("update order_data set order_price=? where order_id=?");
		
		preparedStatement.setString(1, order.getOrderName());
		preparedStatement.setInt(2, order.getOrderId());
	
		preparedStatement.executeUpdate();
	}

	@Override
	public int getMaxOrderPrice() throws SQLException {
		Connection connection = dataSource.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement("select max(order_price) from order_data");
		ResultSet resultSet = preparedStatement.executeQuery();
		int max = 0;
		while (resultSet.next()) {
			max = resultSet.getInt(1);
		}
		return max;
	}

	@Override
	public int getMinOrderPrice() throws SQLException {
		Connection connection = dataSource.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement("select min(order_price) from order_data");
		ResultSet resultSet = preparedStatement.executeQuery();
		int min = 0;
		while (resultSet.next()) {
			min = resultSet.getInt(1);
		}
		return min;
	}

}
