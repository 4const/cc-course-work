package ru.nstu.cs.cconst.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.nstu.cs.cconst.model.Student;
import ru.nstu.cs.cconst.repository.StudentRepository;

import java.util.List;

@Service
@Transactional
public class StudentService {

	@Autowired
	private StudentRepository studentRepository;

    public Student save(Student student) {
        return studentRepository.save(student);
    }

    public Student get(int id) {
        return studentRepository.findOne(id);
    }

    public List<Student> getByGroup(int groupId) {
        return studentRepository.findByGroup(groupId);
    }

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
