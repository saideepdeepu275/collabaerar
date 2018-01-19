package com.model;


import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name="C_USER")
public class UserDetails  implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private int user_id;
	private String firstName;
	private String lastName;
	private String userName;
	private String contact;
	private String email;
	private String password;
	private String role;
	private boolean isonline;
	private boolean enable;
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public boolean isIsonline() {
		return isonline;
	}
	public void setIsonline(boolean isonline) {
		this.isonline = isonline;
	}
	public boolean getsEnable() {
		return enable;
	}
	public void setEnable(boolean enable) {
		this.enable = enable;
	}


}
