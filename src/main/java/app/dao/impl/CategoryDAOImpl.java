package app.dao.impl;

import java.io.Serializable;
import java.util.List;


import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;

import app.dao.CategoryDAO;
import app.dao.GenericDAO;
import app.model.Category;

public class CategoryDAOImpl extends GenericDAO<Integer, Category> implements CategoryDAO {
	private static final Logger logger = Logger.getLogger(CategoryDAOImpl.class);

	public CategoryDAOImpl() {
		super(Category.class);
	}

	@Override
	public List<Category> list() {
		return getSession().createQuery("from Category").getResultList();
	}

}