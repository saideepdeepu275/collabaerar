package com.controller;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Dao.BlogPostDao;
import com.Dao.BlogPostLikesDao;
import com.Dao.UserDao;
import com.model.BlogComment;
import com.model.BlogPost;
import com.model.BlogPostLikes;
import com.model.UserDetails;
import com.service.BlogService;
import com.service.UserService;
import com.model.Error;

@RestController
public class BlogPostController {

	@Autowired
	private BlogService blogService;
	@Autowired
	private UserService userService;
	@Autowired
	private BlogPostLikesDao blogPostLikesDao;
	
	
	@RequestMapping(value = "/saveblog", method = RequestMethod.POST)
	public ResponseEntity<?> saveBlogPost(@RequestBody BlogPost blogPost, HttpSession session) {
		UserDetails validUser = (UserDetails) session.getAttribute("validUser");
		/*if (validUser == null) {
	
			Error error = new Error(5,"Unauthorized access");
			return new ResponseEntity<Error>(error, HttpStatus.UNAUTHORIZED);// 401
		}*/
	//	UserDetails user=userService.getUserByUserName(validUser.getUserName());//
				
				
		blogPost.setPostedOn(new Date());
		blogPost.setPostedBy(validUser);// FK column postedby_username ['adam']
		try {
			blogService.saveBlogPost(blogPost);
		} catch (Exception e) {
			Error error = new Error(6,"Unable to insert blog details " + e.getMessage());
			return new ResponseEntity<Error>(error, HttpStatus.INTERNAL_SERVER_ERROR);// 500
		}
		return new ResponseEntity<BlogPost>(blogPost, HttpStatus.OK);
	}

	
	
	
	@RequestMapping(value="/getblogs/{approved}",method=RequestMethod.GET)
	public ResponseEntity<?> getBlogs(@PathVariable int approved,HttpSession session){
		UserDetails validUser = (UserDetails) session.getAttribute("validUser");
		if(validUser==null){
			Error error=new Error(5,"Unauthorized access");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);//401
		}
		if(approved==0){//list of blogs waiting for approval
			UserDetails user=userService.getUserByUserName(validUser.getUserName());
		if(!user.getRole().equals("ADMIN")){
			Error error=new Error(7,"Access Denied");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
		}
		List<BlogPost> blogPosts=blogService.getBlogs(approved);
		return new ResponseEntity<List<BlogPost>>(blogPosts,HttpStatus.OK);
		
	}
	@RequestMapping(value="/getblog/{id}",method=RequestMethod.GET)
	public ResponseEntity<?> getBlogPost(@PathVariable int id){
//		String username=(String)session.getAttribute("username");
//		if(username==null){
//			ErrorClazz error=new ErrorClazz(5,"Unauthorized access");
//			return new ResponseEntity<ErrorClazz>(error,HttpStatus.UNAUTHORIZED);//401
//		}
		BlogPost blogPost=blogService.getBlogById(id);
		return new ResponseEntity<BlogPost>(blogPost,HttpStatus.OK);
	}
	@RequestMapping(value="/updateapprovalstatus",method=RequestMethod.PUT)
	public ResponseEntity<?> updateApprovalStatus(@RequestBody BlogPost blogPost,
			          @RequestParam(required=false) String rejectionReason,HttpSession session){
		UserDetails validUser = (UserDetails) session.getAttribute("validUser");
		if(validUser==null){
			Error error=new Error(5,"Unauthorized access");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);//401
		}
		try{
			//if admin selects Approve, blogPost.approved=1
			//if admin selects Reject, blogPost.approved=0
			System.out.println(blogPost);
			blogService.updateBlogPost(blogPost,rejectionReason);
		}catch(Exception e){
			Error error=new Error(7,"Unable to update blogpost approval status "+e.getMessage());
			return new ResponseEntity<Error>(error,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@RequestMapping(value="/userLikes/{id}",method=RequestMethod.GET)
	public ResponseEntity<?> userLikes(@PathVariable int id,HttpSession session){
		UserDetails validUser = (UserDetails) session.getAttribute("validUser");
		if(validUser==null){
			Error error=new Error(5,"Unauthorized access");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);//401
		}
		UserDetails user=userService.getUserByUserName(validUser.getUserName());
		BlogPost blogPost=blogService.getBlogById(id);
		//blogPostLikes = null / 1 object
		// if user has not yet liked the blogPost, blogPostLikes = null
		//if user has liked the blogPost already, blogPostLikes= 1 object
		BlogPostLikes blogPostLikes=blogPostLikesDao.userLikes(blogPost, user);
		return new ResponseEntity<BlogPostLikes>(blogPostLikes,HttpStatus.OK);
	}
	@RequestMapping(value="/updatelikes",method=RequestMethod.PUT)
	public ResponseEntity<?> updateLikes(@RequestBody BlogPost blogPost,HttpSession session){
		UserDetails validUser = (UserDetails) session.getAttribute("validUser");
		if(validUser==null){
			Error error=new Error(5,"Unauthorized access");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);//401
		}
		UserDetails user=userService.getUserByUserName(validUser.getUserName());
		BlogPost updatedBlogPost=blogPostLikesDao.updateLikes(blogPost, user);
		return new ResponseEntity<BlogPost>(updatedBlogPost,HttpStatus.OK);
	}
	@RequestMapping(value="/addcomment",method=RequestMethod.POST)
	//http://localhost:8080/project2middleware/addcomment?commentText='Thanks'&id=484
	public ResponseEntity<?> addBlogComment(@RequestParam String commentText , @RequestParam int id
			,HttpSession session){
		UserDetails validUser = (UserDetails) session.getAttribute("validUser");
		if(validUser==null){
			Error error=new Error(5,"Unauthorized access");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);//401
		}
		UserDetails user=userService.getUserByUserName(validUser.getUserName());
		//Construct blogcomment object
		BlogComment blogComment=new BlogComment();
		
		blogComment.setCommentText(commentText);
		blogComment.setCommentedBy(user);
		BlogPost blogPost=blogService.getBlogById(id);
		blogComment.setBlogPost(blogPost);
		blogComment.setCommentedOn(new Date());
		
		
		try{
			blogService.addComment(blogComment);
		}catch(Exception e){
			Error error=new Error(7,"Unable to post comments " + e.getMessage());
			return new ResponseEntity<Error>(error,HttpStatus.INTERNAL_SERVER_ERROR);//500
		}
		 blogPost=blogService.getBlogById(id);
		return new ResponseEntity<BlogPost>(blogPost,HttpStatus.OK);
		
	}

}