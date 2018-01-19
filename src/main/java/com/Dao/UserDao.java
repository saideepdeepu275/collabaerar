package com.Dao;

import java.util.List;

import com.model.UserDetails;

public interface UserDao {

	public boolean saveOrUpdate(UserDetails users);
	public UserDetails updateUser(UserDetails users);
	public void delete(UserDetails user);
	public UserDetails getUserByUsername(String username);
	public UserDetails viewUser(int userid);
	public List<UserDetails> UserList();
	public UserDetails login(String username,String password);
	public boolean isUsernameValid(String username);
	public boolean isEmailValid(String email);
}

