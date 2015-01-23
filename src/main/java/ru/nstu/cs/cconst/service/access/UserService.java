package ru.nstu.cs.cconst.service.access;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.nstu.cs.cconst.model.access.User;
import ru.nstu.cs.cconst.repository.access.UserRepository;

@Service
@Transactional
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public User get(String login) {
		return userRepository.findOne(login);
	}
}
