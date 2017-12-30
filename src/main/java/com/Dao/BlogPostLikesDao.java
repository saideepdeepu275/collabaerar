package com.Dao;

import com.model.BlogPost;
import com.model.BlogPostLikes;
import com.model.UserDetails;

public interface BlogPostLikesDao {
	//select * from blogpostlikes where blogpost_id=? and user_username=?
	//if user already liked the post, 1 object
	//if user has not yet liked the post, null object
BlogPostLikes userLikes(BlogPost blogPost,UserDetails user);

//increment / decrement the number of likes
//insert into blogpostlikes / delete from blogpostlikes 
BlogPost updateLikes(BlogPost blogPost, UserDetails user);
}