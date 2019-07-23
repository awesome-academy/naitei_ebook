package app.service.impl;

import app.dao.BookDAO;
import app.dao.UserDAO;

public class BaseServiceImpl {
	protected BookDAO bookDAO;
	protected UserDAO userDAO;

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
}
