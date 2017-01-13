package com.kongxiaogang.service;

import org.springframework.stereotype.Service;

import com.kongxiaogang.model.UserModel;

public interface UserService {
	public UserModel getUserByID(String userId);
}
