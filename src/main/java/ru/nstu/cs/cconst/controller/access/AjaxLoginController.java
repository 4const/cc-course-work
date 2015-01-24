package ru.nstu.cs.cconst.controller.access;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.nstu.cs.cconst.controller.json.LoginJson;
import ru.nstu.cs.cconst.security.SecurityContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/login")
public class AjaxLoginController {

	@Autowired
	@Qualifier("authenticationManager")
	private AuthenticationManager authenticationManager;

	@Autowired
	SecurityContextRepository repository;

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public void login() {}

	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public LoginJson performLogin(
		@RequestParam("username") String username,
		@RequestParam("password") String password,
		HttpServletRequest request, HttpServletResponse response)
	{
		UsernamePasswordAuthenticationToken token =
			new UsernamePasswordAuthenticationToken(username, password);
		try {
			Authentication auth = authenticationManager.authenticate(token);
			SecurityContextHolder.getContext().setAuthentication(auth);
			repository.saveContext(SecurityContextHolder.getContext(), request, response);

			return LoginJson.success(SecurityContext.getUserDetails().get());
		} catch (BadCredentialsException ex) {
			return LoginJson.fail();
		}
	}
}
