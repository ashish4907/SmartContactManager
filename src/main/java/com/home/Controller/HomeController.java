package com.home.Controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.home.DTO.Contact;
import com.home.DTO.User;
import com.home.Repo.UserRepo;
import com.home.helper.Message;

@Controller
public class HomeController {

	@Autowired
	private UserRepo userRepo;

	@RequestMapping("/")

	public String home(Model model) {
		model.addAttribute("title", "Home - Smart Contact Mnager");
		return "home";
	}

	@RequestMapping("/about")

	public String about(Model model) {
		model.addAttribute("title", "About - Smart Contact Mnager");
		return "about";
	}

	@RequestMapping("/signup")

	public String signup(Model model) {
		model.addAttribute("title", "Register - Smart Contact Mnager");
		model.addAttribute("user", new User());
		return "signup";
	}

//	Handle for registering user

	@RequestMapping(value = "/do_register", method = RequestMethod.POST)
	public String registerUser(@ModelAttribute("user") User user,
			@RequestParam(value = "agreement", defaultValue = "false") boolean agreement, Model model,
			HttpSession session) {

		try {

			if (!agreement) {
				System.out.println();
				throw new Exception("You have not agreed the terms and condition");
			}

			user.setRole("ROLE_USER");
			user.setEnabled(true);

			System.out.println("Agreement" + agreement);
			System.out.println("User" + user);

			User result = this.userRepo.save(user);

			model.addAttribute("user", new User());
			session.setAttribute("message", new Message("Successfully registered!!", "aler-success"));
			return "signup";

		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("user", user);
			session.setAttribute("message", new Message("Something went wrong!!" + e.getMessage(), "aler-danger"));
			return "signup";
		}

	}

}
