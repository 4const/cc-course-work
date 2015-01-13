package ru.nstu.cs.cconst.repository;


import org.springframework.data.repository.Repository;
import ru.nstu.cs.cconst.model.Group;
import ru.nstu.cs.cconst.model.Student;

import java.util.List;

public interface StudentRepository extends Repository<Student, Integer> {

    Student save(Student student);

	Student findOne(int id);

	List<Student> findAll();

	List<Student> findByGroup(Group group);

	void delete(Student student);
}
