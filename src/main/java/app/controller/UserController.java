package app.controller;

import org.apache.log4j.Logger;


import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.ui.Model;

import java.util.List;

import app.model.User;
import app.service.UserService;

@Controller
public class UserController {
	private static final Logger logger = Logger.getLogger(UserController.class);

	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "users/{id}", method = RequestMethod.GET)
	public ModelAndView show(@PathVariable("id") int id) {
		logger.info("detail user");
		ModelAndView model = new ModelAndView("/front/user/show");
		User user = userService.findById(id);
		if(user == null) {
			model.addObject("error",true);
		}
		else {
			model.addObject("user", user);

		}
		return model;
	}

}
