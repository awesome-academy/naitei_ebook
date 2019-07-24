package app.service.impl;

import java.io.Serializable;


import java.util.List;

import org.apache.log4j.Logger;

import app.model.Book;
import app.service.BookService;

public class BookServiceImpl extends BaseServiceImpl implements BookService {
	private static final Logger logger = Logger.getLogger(BookServiceImpl.class);

	@Override
	public Book saveOrUpdate(Book entity) {
		try {
			return getBookDAO().saveOrUpdate(entity);
		} catch (Exception e) {
			logger.error(e);
			throw e;
		}
	}


	@Override
	public Book findById(Serializable key) {
		try {
			return getBookDAO().findById(key);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public boolean delete(Book entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Book> loadBooks() {
		try {
			return getBookDAO().loadBooks();
		} catch (Exception e) {
			return null;
		}

	}
}