package ru.nstu.cs.cconst.service.access;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.nstu.cs.cconst.repository.access.UserRepository;
import ru.nstu.cs.cconst.security.SecurityContext;

import java.util.Arrays;

@Service
@Transactional
public class UserService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		ru.nstu.cs.cconst.model.access.User user = userRepository.findOne(username);
		if (user == null) {
			throw new UsernameNotFoundException(username);
		}
		String role = user.getRole() == ru.nstu.cs.cconst.model.access.User.USER_ADMIN ? SecurityContext.ROLE_ADMIN : SecurityContext.ROLE_USER;

		return new User(user.getLogin(), user.getPassword(), Arrays.asList(new SimpleGrantedAuthority(role)));
	}
}
