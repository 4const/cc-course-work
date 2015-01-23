package ru.nstu.cs.cconst.controller.access;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.nstu.cs.cconst.service.access.UserService;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/user", method = RequestMethod.POST)
	@ResponseBody
	public boolean signIn(@RequestParam String login,
						  @RequestParam String password, HttpSession session) {
		return true;
	}
}
