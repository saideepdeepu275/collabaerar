package com.service;

import java.util.List;

import com.model.BlogComment;
import com.model.BlogPost;

public interface BlogService {
	
	
	void saveBlogPost(BlogPost blogPost);
	//return list of blogs waiting for approval(0) / list of blogs approved(1)
	//getBlogs(0) -> list of blogs waiting for approval
	//getBlogs(1) -> list of blogs approved
	List<BlogPost> getBlogs(int approved);//values = 0 / 1
	BlogPost getBlogById(int id);
	void updateBlogPost(BlogPost blogPost,String rejectionReason);
	void addComment(BlogComment blogComment);

}
