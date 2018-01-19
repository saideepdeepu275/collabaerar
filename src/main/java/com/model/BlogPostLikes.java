package com.model;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="blogpostlikes")
public class BlogPostLikes {
	
		@Id
		@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	@ManyToOne
	private BlogPost blogPost;
	@ManyToOne
	private UserDetails user;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public BlogPost getBlogPost() {
		return blogPost;
	}
	public void setBlogPost(BlogPost blogPost) {
		this.blogPost = blogPost;
	}
	public UserDetails getUser() {
		return user;
	}
	public void setUser(UserDetails user) {
		this.user = user;
	}
	}
	