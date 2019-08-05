package app.service.impl;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;

import app.model.Book;
import app.model.Comment;
import app.service.CommentService;

public class CommentServiceImpl extends BaseServiceImpl implements CommentService {
	private static final Logger logger = Logger.getLogger(CommentServiceImpl.class);

	@Override
	public Comment saveComment(int book_id, Comment comment) {
		try {
			Book book = getBookDAO().findById(book_id);
			if (book == null)
				return null;

			comment.setBook(book);
			return getCommentDAO().saveOrUpdate(comment);
		} catch (Exception e) {
			logger.error("Has errors at saveComment with messsage " + e);
			throw e;
		}
	}

	@Override
	public Comment findById(Serializable key) {
		try {
			return getCommentDAO().findById(key);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public boolean delete(Comment entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Comment> listComments() {
		try {
			return getCommentDAO().listComments();
		} catch (Exception e) {
			return null;
		}

	}

	@Override
	public List<Comment> listComments(Integer book_id) {
		try {
			return getCommentDAO().listComments(book_id);
		} catch (Exception e) {
			return null;
		}
	}

}