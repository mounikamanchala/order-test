package com.example.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.model.User;

@Component("userDao")
public class UserDaoImpl implements UserDao {

	public final DataSource dataSource;

	@Autowired
	public UserDaoImpl(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public boolean isUserExists(User userInput) throws SQLException {
		User userFromDb = null;
		Connection connection = dataSource.getConnection();
		PreparedStatement preparedStatement = connection
				.prepareStatement("select * from user where user_id=? and password=?");
		preparedStatement.setInt(1, userInput.getUserId());
		preparedStatement.setString(2, userInput.getPassword());
		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			userFromDb = new User(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3));
		}
		if (userFromDb != null) {
			return true;
		}
		return false;
	}

}
