package ru.nstu.cs.cconst.repository;


import org.springframework.data.repository.Repository;
import ru.nstu.cs.cconst.model.Student;

import java.util.List;

public interface StudentRepository extends Repository<Student, Integer> {

    Student save(Student student);

	Student findOne(Integer id);

	List<Student> findAll();

	void delete(Student student);
}
