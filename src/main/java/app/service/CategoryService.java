package app.service;

import java.util.List;

import app.model.Category;

public interface CategoryService extends BaseService<Integer, Category> {

	List<Category> list();
}