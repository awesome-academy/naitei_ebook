package app.service;

import java.util.List;

import app.model.Book;

public interface BookService extends BaseService<Integer, Book> {

	List<Book> loadBooks();
}