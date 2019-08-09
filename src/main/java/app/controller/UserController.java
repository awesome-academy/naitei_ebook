package app.controller;

import java.util.Locale;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
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

import app.model.User;
import app.service.UserService;

@Controller("UserController")
@RequestMapping(value = "/users/")
public class UserController extends BaseController {
	private static final Logger logger = Logger.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public ModelAndView showUser(@PathVariable("id") int id, Authentication authentication) {
		logger.info("detail user");
		ModelAndView model = new ModelAndView("/front/user/show");

		User user = userService.findById(id);
		if (user == null) {
			model.addObject("error", messageSource.getMessage("user.notFound", null, Locale.US));

		} else {
			String email = authentication.getName();
			if (!email.equals(user.getEmail())) {
				model.addObject("error", messageSource.getMessage("user.notCorrect", null, Locale.US));
			} else {
				model.addObject("user", user);
			}
		}

		return model;
	}

	@RequestMapping(method = RequestMethod.POST, value = "**/")
	public String saveOrUpdate(@Valid @ModelAttribute("userForm") User user, @RequestParam("password") String password,
			BindingResult bindingResult, Model model, final RedirectAttributes redirectAttributes) {

		logger.info("edit user");

		// model.addAttribute("status", "edit");
		if (bindingResult.hasErrors()) {
			logger.info("edit user binding error");
			logger.info(bindingResult);
			return "/front/user/user-form";
		}
		User user1 = userService.findById(user.getId());
		if (BCrypt.checkpw(password, user1.getPassword()) == false) {
			model.addAttribute("error", messageSource.getMessage("Password.user.fail", null, Locale.US));
			return "/front/user/user-form";
		}
		user.setPassword(BCrypt.hashpw(password, BCrypt.gensalt(4)));
		try {
			userService.saveOrUpdate(user);
			redirectAttributes.addFlashAttribute("success",
					messageSource.getMessage("saveOrUpdateSuccess", null, Locale.US));
			logger.info("edit user success");
			return "redirect:/users/" + user.getId();

		} catch (Exception e) {

			model.addAttribute("error", messageSource.getMessage("Email.user.email.exist", null, Locale.US));
			logger.info("edit user error");
			return "/front/user/user-form";
		}
	}

	@RequestMapping(value = "{id}/edit", method = RequestMethod.GET)
	public String editProfile(@PathVariable("id") int id, Model model) {

		User user = userService.findById(id);
		model.addAttribute("userForm", user);
		model.addAttribute("status", "edit");

		return "/front/user/user-form";

	}

}
