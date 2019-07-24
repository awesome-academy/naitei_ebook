package app.service.impl;

import app.dao.BookDAO;
import app.dao.CategoryDAO;
import app.dao.UserDAO;

public class BaseServiceImpl {
	protected BookDAO bookDAO;
	protected UserDAO userDAO;
	protected CategoryDAO categoryDAO;

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
}
