package org.isf.controller;

import org.isf.menu.model.User;
import org.isf.menu.service.UserIoOperationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DefaultController {
	private final UserIoOperationRepository userService;

	@Autowired
	public DefaultController(UserIoOperationRepository userService) {
		this.userService = userService;
	}

	@GetMapping(value = "/login")
	public ModelAndView login() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("login");
		return modelAndView;
	}

	@GetMapping(value = "/home")
	public ModelAndView home() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findByUserName(auth.getName());

		ModelAndView mv = new ModelAndView();
		mv.addObject("userName", "Welcome " + user.getUserName() + "/" + user.getUserName() + " " + user.getDesc() + " (" + user.getUserGroupName() + ")");
		mv.addObject("adminMessage", "Content Available Only for Users with Admin/Guest Role");
		mv.setViewName("home");
		return mv;
	}

	@GetMapping("/admin")
	public String admin() {
		return "/admin";
	}

	@GetMapping("/guest")
	public String guest() {
		return "/guest";
	}

	@GetMapping("/403")
	public String error403() {
		return "/403";
	}
}
