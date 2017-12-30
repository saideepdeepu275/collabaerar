package com.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Dao.UserDao;
import com.model.UserDetails;

@Service

public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;


	public boolean saveOrUpdate(UserDetails user) {

		return userDao.saveOrUpdate(user);
	}

	public void delete(UserDetails user) {
		userDao.delete(user);
		
	}

	public UserDetails getUser(String username) {
		return userDao.getUser(username);
	}

	public UserDetails viewUser(int userid) {
		
		return userDao.viewUser(userid);
	}

	public List<UserDetails> UserList() {
	
		return userDao.UserList();
	}

	public UserDetails login(String userName,String password) {
		
		return userDao.login(userName,password);
	}

	
	
	public boolean isUsernameValid(String username) {
	
		return userDao.isUsernameValid(username);
	}

	public boolean isEmailValid(String email) {
	
		return userDao.isEmailValid(email);
	}

	public UserDetails updateUser(UserDetails users) {

		return userDao.updateUser(users);
	}

	
	

}
