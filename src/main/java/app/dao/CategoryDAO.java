package app.dao;

import java.util.List;

import app.model.Category;

public interface CategoryDAO extends BaseDAO<Integer, Category> {
	
	List<Category> list();

}