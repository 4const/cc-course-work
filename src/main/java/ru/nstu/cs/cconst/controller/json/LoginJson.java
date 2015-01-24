package ru.nstu.cs.cconst.controller.json;

import org.springframework.security.core.userdetails.UserDetails;
import ru.nstu.cs.cconst.security.SecurityContext;

public class LoginJson {
	public String status;
	public String name;
	public boolean admin;

	private LoginJson(String status, String name, boolean admin) {
		this.status = status;
		this.name = name;
		this.admin = admin;
	}

	public static LoginJson success(UserDetails details) {

		return new LoginJson("ok",
			details.getUsername(),
			SecurityContext.isAdmin(details));
	}

	public static LoginJson fail() {
		return new LoginJson("rejected", null, false);
	}
}
