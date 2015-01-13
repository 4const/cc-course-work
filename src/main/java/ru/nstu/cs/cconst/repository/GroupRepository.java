package ru.nstu.cs.cconst.repository;


import org.springframework.data.repository.Repository;
import ru.nstu.cs.cconst.model.Group;

import java.util.List;

public interface GroupRepository extends Repository<Group, Integer> {

    Group save(Group group);

    Group findOne(Integer id);

	List<Group> findAll();

	void delete(Group student);
}
