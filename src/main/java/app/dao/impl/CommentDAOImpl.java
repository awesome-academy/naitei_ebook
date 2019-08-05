package app.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;

import app.dao.CommentDAO;
import app.dao.GenericDAO;
import app.model.Comment;

public class CommentDAOImpl extends GenericDAO<Integer, Comment> implements CommentDAO {
	private static final Logger logger = Logger.getLogger(CommentDAOImpl.class);

	public CommentDAOImpl() {
		super(Comment.class);
	}

	@Override
	public List<Comment> listComments() {
		return getSession().createQuery("from Comment").getResultList();
	}

	@Override
	public List<Comment> listComments(Integer book_id) {
		return getSession().createQuery("FROM Comment WHERE (reply_id IS NULL AND book_id = :book_id) ")
				.setParameter("book_id", book_id)
				.getResultList();
	}

}