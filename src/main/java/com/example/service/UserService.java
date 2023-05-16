package com.example.service;

import java.sql.SQLException;

import com.example.model.User;

public interface UserService {
	public boolean isUserExists(User user) throws SQLException;
}
