package app.service.impl;

import java.io.Serializable;


import java.util.List;

import org.apache.log4j.Logger;

import app.model.Category;
import app.service.CategoryService;

public class CategoryServiceImpl extends BaseServiceImpl implements CategoryService {
	private static final Logger logger = Logger.getLogger(CategoryServiceImpl.class);

	@Override
	public Category saveOrUpdate(Category entity) {
		try {
			return getCategoryDAO().saveOrUpdate(entity);
		} catch (Exception e) {
			logger.error(e);
			throw e;
		}
	}


	@Override
	public Category findById(Serializable key) {
		try {
			return getCategoryDAO().findById(key);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public boolean delete(Category entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Category> list() {
		try {
			return getCategoryDAO().list();
		} catch (Exception e) {
			return null;
		}

	}
}