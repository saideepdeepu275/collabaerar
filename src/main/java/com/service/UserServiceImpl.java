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

	public boolean saveOrUpdate(UserDetails users) {
		// TODO Auto-generated method stub
		return userDao.saveOrUpdate(users);
	}

	public UserDetails updateUser(UserDetails users) {
		// TODO Auto-generated method stub
		return userDao.updateUser(users);
	}

	public void delete(UserDetails user) {
		userDao.delete(user);
		
	}

	public UserDetails getUserByUserName(String userName) {
		// TODO Auto-generated method stub
		return userDao.getUserByUsername(userName);
	}

	public UserDetails viewUser(int userid) {
		// TODO Auto-generated method stub
		return userDao.viewUser(userid);
	}

	public List<UserDetails> UserList() {
		// TODO Auto-generated method stub
		return userDao.UserList();
	}

	public UserDetails login(String username, String password) {
		// TODO Auto-generated method stub
		return userDao.login(username, password);
	}

	public boolean isUsernameValid(String username) {
		// TODO Auto-generated method stub
		return userDao.isUsernameValid(username);
	}

	public boolean isEmailValid(String email) {
		// TODO Auto-generated method stub
		return userDao.isEmailValid(email);
	}


	
	

}
