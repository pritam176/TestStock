/**
 * 
 */
package com.mmt.controller;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author pkumar
 *
 */
@Controller
@RequestMapping("mmt")
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	
	ObjectMapper mapper = new ObjectMapper();

	@GetMapping(value = { "/home.html" })
	public String getHomePage(Model model, HttpServletResponse resp) {

		logger.debug("getHomePage");

		return "home.tiles";

	}

	@GetMapping(value = { "/accessdenied.html" })
	public String getAccesDenied(Model model, HttpServletResponse resp) {

		logger.debug("getHomePage");

		return "accessdenied.tiles";

	}

	@GetMapping(value = { "/error" })
	public String getErrorPage(Model model, HttpServletResponse resp) {

		logger.debug("getErrorPage");

		return "redirect:/mmt/thankyou.html?msg=Some Thing Went Wrong";

	}

	@GetMapping(value = "/thankyou.html")
	public String getthankyouPage(Model model, HttpServletResponse resp,
			@RequestParam(value = "msg", required = false) String msg) {

		logger.debug("thankyou");

		model.addAttribute("msg", msg);

		return "thankyou.tiles";

	}

	// Spring Security see this :
	@RequestMapping(value = "/login.html", method = RequestMethod.GET)
	public String login(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout, Model model) {
		logger.debug("login");

		if (error != null) {
			model.addAttribute("error", "Invalid username and password!");
		}

		if (logout != null) {
			model.addAttribute("msg", "You've been logged out successfully.");
		}
		// model.setViewName("login");

		return "login.tiles";

	}

	@RequestMapping(value = "/logout.html", method = RequestMethod.GET)
	public String logout(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout, Model model) {

		logger.debug("logout");

		return "logout.tiles";

	}

	

}
