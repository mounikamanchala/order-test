package com.example.dao;

import java.sql.SQLException;

import com.example.model.User;

public interface UserDao {
	
	public boolean isUserExists(User user) throws SQLException;

}
