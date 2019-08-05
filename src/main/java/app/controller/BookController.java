package app.controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Locale;

import javax.validation.Valid;

import app.model.Book;
import app.model.Comment;
import app.model.Review;
import app.service.BookService;
import app.service.CategoryService;
import app.service.CommentService;

@Controller
public class BookController {
	private static final Logger logger = Logger.getLogger(BookController.class);

	@Autowired
	private BookService bookService;

	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private CommentService commentService;
	
	@Autowired
	private MessageSource messageSource;

	@RequestMapping(value = "/books")
	public ModelAndView index() {
		ModelAndView model = new ModelAndView("front/book/index");	
		model.addObject("books", bookService.loadBooks());
		return model;
	}
	
	@RequestMapping(value = "/books/{id}")
	public ModelAndView show(@PathVariable("id") int id) {
		ModelAndView model = new ModelAndView("front/book/show");
		Book book =  bookService.findById(id);
		if (book == null) {
			model.addObject("error", messageSource.getMessage("book.notFound", null, Locale.US));
		} else {
			Review review = book.getReview();
			model.addObject("book", book);
			model.addObject("review", review);
			model.addObject("comments", commentService.listComments(id));
			model.addObject("comment", new Comment());
			logger.info(commentService.listComments(id));
		}
		return model;
	}
	
	@RequestMapping(value = "/books/new")
	public ModelAndView create() {
		ModelAndView model = new ModelAndView("front/book/create");
		model.addObject("categories", categoryService.list());
		model.addObject("book", new Book());
		model.addObject("review", new Review());
		return model;
	}

	@RequestMapping(value = "/books/{id}/edit")
	public ModelAndView edit(@PathVariable("id") int id) {
		try {
			ModelAndView model = new ModelAndView("front/book/create");
			Book book = bookService.findById(id);
			Review review = book.getReview();
			model.addObject("book", book);
			model.addObject("review", review);
			return model;
		} catch (Exception e) {
			ModelAndView model = new ModelAndView("front/404");
			model.addObject("error", messageSource.getMessage("book.notFound", null, Locale.US));
			return model;
		}
	}

	@RequestMapping(value = "/books/save", method = RequestMethod.POST)
	public String saveOrUpdate(@Valid @ModelAttribute Book book, BindingResult bindingBook, @Valid @ModelAttribute Review review, BindingResult bindingReview,
			final RedirectAttributes redirectAttributes, Model model) {
		logger.info(review.getContent());
		if (bindingBook.hasErrors() || bindingReview.hasErrors()) {
			return "front/book/create";
		}
		Book newBook = bookService.save(book, review);	
		if(newBook == null) {
			redirectAttributes.addFlashAttribute("css", "danger");
			redirectAttributes.addFlashAttribute("msg", messageSource.getMessage("book.create.fail", null, Locale.US));
		} else {
			redirectAttributes.addFlashAttribute("css", "success");
			redirectAttributes.addFlashAttribute("msg", messageSource.getMessage("book.create.success", null, Locale.US));
		}
		
		return "redirect:/books/"+ newBook.getId();
	}
}
