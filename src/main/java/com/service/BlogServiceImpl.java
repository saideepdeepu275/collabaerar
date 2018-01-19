package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Dao.BlogPostDao;
import com.model.BlogComment;
import com.model.BlogPost;


@Service
public class BlogServiceImpl implements BlogService {
	
	
	
	@Autowired
	BlogPostDao blogPostDao;

	public void saveBlogPost(BlogPost blogPost) {
		blogPostDao.saveBlogPost(blogPost);

	}

	public List<BlogPost> getBlogs(int approved) {
		// TODO Auto-generated method stub
		return blogPostDao.getBlogs(approved);
	}

	public BlogPost getBlogById(int id) {
		// TODO Auto-generated method stub
		return blogPostDao.getBlogById(id);
	}

	public void updateBlogPost(BlogPost blogPost, String rejectionReason) {
		blogPostDao.updateBlogPost(blogPost, rejectionReason);

	}

	public void addComment(BlogComment blogComment) {
		blogPostDao.addComment(blogComment);
	}

}
