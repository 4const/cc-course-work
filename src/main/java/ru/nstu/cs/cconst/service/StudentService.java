package ru.nstu.cs.cconst.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.nstu.cs.cconst.model.Student;
import ru.nstu.cs.cconst.repository.StudentRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class StudentService {

	@Autowired
	private StudentRepository studentRepository;

	public List<Student> all() {
		return studentRepository.findAll();
	}

	public void delete(Integer id) {
		Student student = studentRepository.findOne(id);

		if (student != null) {
			studentRepository.delete(student);
		}
	}
}
