package app.service.impl;

import app.dao.BookDAO;
import app.dao.CategoryDAO;
import app.dao.CommentDAO;
import app.dao.ReviewDAO;
import app.dao.UserDAO;

public class BaseServiceImpl {
	protected BookDAO bookDAO;
	protected ReviewDAO reviewDAO;
	protected UserDAO userDAO;
	protected CategoryDAO categoryDAO;
	protected CommentDAO commentDAO;

	public BookDAO getBookDAO() {
		return bookDAO;
	}

	public void setBookDAO(BookDAO bookDAO) {
		this.bookDAO = bookDAO;
	}

	public UserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
	
	public CategoryDAO getCategoryDAO() {
		return categoryDAO;
	}

	public void setCategoryDAO(CategoryDAO categoryDAO) {
		this.categoryDAO = categoryDAO;
	}

	public ReviewDAO getReviewDAO() {
		return reviewDAO;
	}

	public void setReviewDAO(ReviewDAO reviewDAO) {
		this.reviewDAO = reviewDAO;
	}
	
	public CommentDAO getCommentDAO() {
		return commentDAO;
	}

	public void setCommentDAO(CommentDAO commentDAO) {
		this.commentDAO = commentDAO;
	}
}
