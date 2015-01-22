package ru.nstu.cs.cconst.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.nstu.cs.cconst.model.access.User;
import ru.nstu.cs.cconst.service.access.UserService;

import java.util.Map;

@Component
public class SessionContainer {

	@Autowired
	private UserService service;

	Map<String, User> users;



	private class UserInfo {
		public long loginTime;

	}
}
