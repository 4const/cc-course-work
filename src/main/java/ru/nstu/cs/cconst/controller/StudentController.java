package ru.nstu.cs.cconst.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.nstu.cs.cconst.controller.json.StudentJson;
import ru.nstu.cs.cconst.model.Student;
import ru.nstu.cs.cconst.service.StudentService;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Controller
public class StudentController {

	@Autowired
	private StudentService studentService;

	@RequestMapping(value = "/student", method = RequestMethod.GET)
	@ResponseBody
	public List<StudentJson> getAll() throws Throwable {
		return studentService.all().stream()
			.map(studentToJson)
			.collect(Collectors.toList());
	}

	@RequestMapping(value = "/student", method = RequestMethod.DELETE)
	public void delete(@RequestParam("id") Integer id) throws Throwable {
		studentService.delete(id);
	}

	private static Function<Student, StudentJson> studentToJson =
		s -> new StudentJson(
			s.getId(),
			s.getFirstName(),
			s.getFirstName(),
			s.getPatronymic(),
			s.getGroup().getId(),
			s.getGroup().getName(),
			s.getGrant());
}
