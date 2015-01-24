package ru.nstu.cs.cconst.controller.access;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.nstu.cs.cconst.controller.json.LoginJson;
import ru.nstu.cs.cconst.security.SecurityContext;

import java.util.Optional;

@Controller
public class UserController {

	@RequestMapping(value = "/user", method = RequestMethod.GET)
	@ResponseBody
	public LoginJson check() {
		Optional<UserDetails> detailsOp = SecurityContext.getUserDetails();

		if (!detailsOp.isPresent()) {
			return null;
		}

		return LoginJson.success(detailsOp.get());
	}
}
