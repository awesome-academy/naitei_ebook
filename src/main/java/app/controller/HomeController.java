package app.controller;

import java.util.Locale;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import app.model.User;
import app.service.UserService;

@Controller
public class HomeController extends BaseController {
	private static final Logger logger = Logger.getLogger(HomeController.class);
	@Autowired
	private UserService userService;

	@RequestMapping("/")
	public String index(Model model, HttpSession session) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		if (!(auth instanceof AnonymousAuthenticationToken) && auth.isAuthenticated()) {
			session.setAttribute("current_user", userService.findByEmail(auth.getName()));
			model.addAttribute("currentUser", auth.getName());
		}

		return "front/home";
	}

	@RequestMapping("/login")
	public String login(@RequestParam(value = "error", required = false) final String error,
			@RequestParam(value = "registerSucess", required = false) final String register, final Model model) {
		logger.info("login page");
		if (error != null) {
			model.addAttribute("css", "error");
			model.addAttribute("msg", messageSource.getMessage("login.fail", null, Locale.US));
		}
		if (register != null) {
			model.addAttribute("msg", messageSource.getMessage("register.success", null, Locale.US));
		}
		return "/front/login";
	}

	@RequestMapping("/logout")
	public String logout(final Model model) {
		return "front/login";
	}

	@RequestMapping(value = "/register")
	public String register(Model model) {
		logger.info("register page");
		model.addAttribute("userForm", new User());
		return "/front/register";
	}

	@RequestMapping(value = "/registerProcess", method = RequestMethod.POST)
	public String submitAddOrUpdateUser(@Valid @ModelAttribute("userForm") User user, BindingResult bindingResult,
			Model model) {
		logger.info("submit register user");
		if (bindingResult.hasErrors()) {
			return "/front/register";
		}
		user.setRole(0);
		logger.info(user.getRole());
		String hash = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(4));
		user.setPassword(hash);
		try {
			userService.saveOrUpdate(user);
		} catch (Exception e) {
			model.addAttribute("css", "success");

			model.addAttribute("msg", messageSource.getMessage("register.fail", null, Locale.US));
			return "/front/register";
		}
		return "redirect:/login?registerSucess=true";
	}

}
