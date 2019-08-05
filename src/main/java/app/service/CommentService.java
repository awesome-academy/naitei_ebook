package app.service;

import java.util.List;

import app.model.Comment;

public interface CommentService extends BaseService<Integer, Comment> {

	List<Comment> listComments();

	List<Comment> listComments(Integer book_id);
	
	Comment saveComment(int book_id, Comment comment);
}