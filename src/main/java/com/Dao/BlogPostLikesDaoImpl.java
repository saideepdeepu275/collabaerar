package com.Dao;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.model.BlogPost;
import com.model.BlogPostLikes;
import com.model.UserDetails;
@Repository
@Transactional
public class BlogPostLikesDaoImpl {
	@Autowired
private SessionFactory sessionFactory;
	public BlogPostLikes userLikes(BlogPost blogPost, UserDetails userDetails) {
		Session session=sessionFactory.getCurrentSession();
		//select * from blogpostlikes_s180133 where blogpost_id=? and user_username=?
		Query query=session.createQuery("from BlogPostLikes where blogPost.id=? and user.username=? ");
		System.out.println("BlogPost id  " + blogPost.getId());
		System.out.println("Username " + userDetails.getUserName());
		query.setInteger(0, blogPost.getId());
		query.setString(1, userDetails.getUserName());
		//blogPostlikes = null [glyphicon in black color] / 1 [glyphicon in blue color] object
		BlogPostLikes blogPostLikes=(BlogPostLikes)query.uniqueResult();
		System.out.println(blogPostLikes);
		return blogPostLikes;
		
	}

	public BlogPost updateLikes(BlogPost blogPost, UserDetails usersDetails) {
		Session session=sessionFactory.getCurrentSession();
		BlogPostLikes blogPostLikes=userLikes(blogPost,usersDetails);
		//insert and increment  / delete and decrement
		//like
		if(blogPostLikes==null){ //insert into blogpostlikes, increment blogpost.likes
			BlogPostLikes insertLikes=new BlogPostLikes();
			insertLikes.setBlogPost(blogPost);//FK blogpost_id
			insertLikes.setUser(usersDetails);//FK user_username
			session.save(insertLikes); //insert into blogpostlikes
			blogPost.setLikes(blogPost.getLikes() + 1); //increment the number of likes
			session.update(blogPost);//update blogpost set likes=.. where id=?
		}
		else{//unlike
			session.delete(blogPostLikes); //delete from blogpostlikes
			blogPost.setLikes(blogPost.getLikes()-1); //decrement the number of likes
			session.merge(blogPost); //update blogpost set likes ...
		}
		return blogPost;
	}
}
