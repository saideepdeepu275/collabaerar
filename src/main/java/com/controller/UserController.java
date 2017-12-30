package com.controller;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.model.UserDetails;
import com.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/signUp", method = RequestMethod.POST)
	public ResponseEntity<?> registerUser(@RequestBody UserDetails user) {
	
	

		boolean result = userService.saveOrUpdate(user);
		if (result) {
			return new ResponseEntity<UserDetails>(user, HttpStatus.OK);
		} else {
			Error error = new Error("unable to register user details");
			return new ResponseEntity<Error>(error, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	/************************Login***********************************/
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public ResponseEntity<?> login(@RequestBody UserDetails users,HttpSession session)
	{ 
	    System.out.println("Is Session New For" + users.getUserName() + session.isNew());
	    
	   
	    
	    UserDetails validUser=userService.login(users.getUserName(),users.getPassword());
	    if(validUser==null)

	    {
		    Error error=new Error("Invalid username and password.. please enter valid credentials");
		    return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}	   
	    else	
	    {
	        validUser.setIsonline(true);
		    validUser=userService.updateUser(validUser);
		    session.setAttribute("user", validUser);
		    return new ResponseEntity<UserDetails>(validUser,HttpStatus.OK);    
		}
	}
	/************************Logout ***********************************/
	@RequestMapping(value="/logout",method=RequestMethod.GET)
	public ResponseEntity<?> logout(HttpSession session)
	{ 
	  
	    UserDetails validUser=(UserDetails) session.getAttribute("user");
	    if(validUser==null)

	    {
	    	Error error=new Error("Unauthorized user");
		    return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}	   
	    else	
	    {
	        validUser.setIsonline(false);
	        userService.updateUser(validUser);
		    session.removeAttribute("user");
		    session.invalidate();
		    return new ResponseEntity<UserDetails>(validUser,HttpStatus.OK);    
		}
	}
}