package ru.nstu.cs.cconst.repository;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import ru.nstu.cs.cconst.model.Student;

import java.util.List;

public interface StudentRepository extends Repository<Student, Integer> {

    Student save(Student student);

	Student findOne(Integer id);

	@Query("from Student order by id asc")
	List<Student> findAll();

    @Query("from Student where group.id = ?1")
	List<Student> findByGroup(int groupId);

	void delete(Student student);
}
