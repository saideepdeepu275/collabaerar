package com.service;
import java.util.List;

import com.model.UserDetails;
public interface UserService {
	public boolean saveOrUpdate(UserDetails user);
	public UserDetails updateUser(UserDetails users);
	public void delete(UserDetails user);
	public UserDetails getUser(String username);
	public UserDetails viewUser(int userid);
	public List<UserDetails> UserList();
	public UserDetails login(UserDetails user);
	public boolean isUsernameValid(String username);
	public boolean isEmailValid(String email);

}