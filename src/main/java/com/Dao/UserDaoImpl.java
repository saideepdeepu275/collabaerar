package com.Dao;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.*;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.model.UserDetails;


@Repository("UserDao")
public class UserDaoImpl implements UserDao {
	
Logger Logger=LoggerFactory.getLogger(UserDaoImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;

	public UserDaoImpl(SessionFactory sessionFactory) {
	
		this.sessionFactory=sessionFactory;
	}

	public boolean saveOrUpdate(UserDetails users) {
		Logger.info("save Operation started", users.getUserName());
		Session session=sessionFactory.openSession();

		Transaction tx=session.beginTransaction();
		users.setEnable(true);
		users.setIsonline(false);
		session.saveOrUpdate(users);
		tx.commit();
		Logger.info("User object has been saved successfually", users.getUserName());
	
		return true;			
	
	}


	
	
	
	@Transactional
	public UserDetails updateUser(UserDetails validUser) {
		Session session=sessionFactory.openSession();
		Transaction tx=session.beginTransaction();
		session.update(validUser);
		tx.commit();
		
		return validUser;		
	}
	
	
	
	
	
	@Transactional
	public void delete(UserDetails user) {
		sessionFactory.getCurrentSession().delete(user);
		}
		
	
	
	
		@SuppressWarnings("deprecation")
		@Transactional
		public UserDetails getUserByUserName(String username) {
		Criteria c=sessionFactory.getCurrentSession().createCriteria(UserDetails.class);
		c.add(Restrictions.eq("userName", username));
		UserDetails user=(UserDetails)c.uniqueResult();
		return user;
	}		
	

	
		
		
		
		@SuppressWarnings("deprecation")
		@Transactional
		public UserDetails viewUser(int userid) {
		Criteria c=sessionFactory.getCurrentSession().createCriteria(UserDetails.class);
		c.add(Restrictions.eq("userid", userid));
		UserDetails user=(UserDetails) c.uniqueResult();
		return user;
				
	}


		@SuppressWarnings({ "unchecked", "deprecation" })
		@Transactional
		public List<UserDetails> UserList() {
		Criteria c=sessionFactory.openSession().createCriteria(UserDetails.class);
		List<UserDetails> l = c.list();
		return l;		
	}


	@SuppressWarnings({ "rawtypes", "deprecation" })
	@Transactional
	public UserDetails login(String userName,String password) {
		Session session=sessionFactory.openSession();

		Query query=session.createQuery("from UserDetails where userName=? and password=? and enable=?");
	
		query.setString(0,userName); //for assigning the values to parameter username
		query.setString(1, password);
		query.setBoolean(2, true);
		UserDetails validUsers=(UserDetails)query.uniqueResult();
	
		System.out.println("Dao completed");
		return validUsers;		
	}

	@Transactional
	public boolean isUsernameValid(String username) {
		List<UserDetails> list = UserList();

		for (UserDetails usersDetail : list) {
			if (usersDetail.getUserName().equals(username)) {
				return false;// invalid user
			}
		}
		return true;// valid user		
	}

	@Transactional
	public boolean isEmailValid(String email) {
		List<UserDetails> list = UserList();

		for (UserDetails usersDetail : list) {
			if (usersDetail.getEmail().equals(email)){
				return false;// invalid user
			}
		}
		return true;// valid user
		
	}

	public UserDetails getUserByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}


}
