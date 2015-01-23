package ru.nstu.cs.cconst.repository.access;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import ru.nstu.cs.cconst.model.access.User;

public interface UserRepository extends Repository<User, Integer> {

	@Query("from User where login = ?1")
	User findOne(String login);
}
