package app.controller;

import java.util.Locale;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import app.model.Comment;
import app.service.CommentService;

@Controller
public class CommentController {
	private static final Logger logger = Logger.getLogger(CommentController.class);

	@Autowired
	private CommentService commentService;

	@Autowired
	private MessageSource messageSource;

	@RequestMapping(value = "/books/{book_id}/comments", method = RequestMethod.POST)
	public String saveOrUpdate(@Valid @ModelAttribute Comment comment, BindingResult bindingComment,
			final RedirectAttributes redirectAttributes, @PathVariable("book_id") int book_id, Model model) {
		logger.info("create comment");
		if (bindingComment.hasErrors()) {
			redirectAttributes.addFlashAttribute("css", "danger");
			redirectAttributes.addFlashAttribute("msg",
					messageSource.getMessage("comment.create.fail", null, Locale.US));
			return "redirect:/books/" + comment.getBook().getId();
		}
		
		Comment newComment = commentService.saveComment(book_id, comment);

		if (newComment == null) {
			redirectAttributes.addFlashAttribute("css", "danger");
			redirectAttributes.addFlashAttribute("msg",
					messageSource.getMessage("comment.create.fail", null, Locale.US));
		} else {
			redirectAttributes.addFlashAttribute("css", "success");
			redirectAttributes.addFlashAttribute("msg",
					messageSource.getMessage("comment.create.success", null, Locale.US));
		}

		return "redirect:/books/" + newComment.getBook().getId();
	}
}