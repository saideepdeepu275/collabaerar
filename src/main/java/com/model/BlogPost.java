package com.model;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="C_BlogPost")
 public class BlogPost {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
private int id;
private String blogTitle;
@Lob
private String blogContent;
@ManyToOne
private UserDetails postedBy;
private Date postedOn;
private boolean approved;
private int likes;
/*@OneToMany(mappedBy="blogPost",fetch=FetchType.EAGER)
private List<BlogComment> blogComments;*/
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getBlogTitle() {
	return blogTitle;
}
public void setBlogTitle(String blogTitle) {
	this.blogTitle = blogTitle;
}
public String getBlogContent() {
	return blogContent;
}
public void setBlogContent(String blogContent) {
	this.blogContent = blogContent;
}
public UserDetails getPostedBy() {
	return postedBy;
}
public void setPostedBy(UserDetails postedBy) {
	this.postedBy = postedBy;
}
public Date getPostedOn() {
	return postedOn;
}
public void setPostedOn(Date postedOn) {
	this.postedOn = postedOn;
}
public boolean isApproved() {
	return approved;
}
public void setApproved(boolean approved) {
	this.approved = approved;
}
public int getLikes() {
	return likes;
}
public void setLikes(int likes) {
	this.likes = likes;
}
/*public List<BlogComment> getBlogComments() {
	return blogComments;
}
public void setBlogComments(List<BlogComment> blogComments) {
	this.blogComments = blogComments;
}*/



}