package ru.nstu.cs.cconst.repository.access;

import org.springframework.data.repository.Repository;
import ru.nstu.cs.cconst.model.access.User;

public interface UserRepository extends Repository<User, Integer> {

	User findOne(String login, String password);
}
