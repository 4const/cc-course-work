package ru.nstu.cs.cconst.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.nstu.cs.cconst.controller.json.StudentJson;
import ru.nstu.cs.cconst.model.Group;
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
	@ResponseBody
	public void delete(@RequestParam("id") Integer id) throws Throwable {
		studentService.delete(id);
	}

    @RequestMapping(value = "/student", method = RequestMethod.PUT)
	@ResponseBody
	public StudentJson save(@RequestBody StudentJson studentJson) throws Throwable {
        Student saved = studentService.save(jsonToStudent.apply(studentJson));

        return studentToJson.apply(saved);
    }

    private Function<Student, StudentJson> studentToJson =
		s -> new StudentJson(
			s.getId(),
			s.getLastName(),
			s.getFirstName(),
			s.getPatronymic(),
			s.getGroup().getId(),
			s.getGroup().getName(),
			s.getGrant());

    private Function<StudentJson, Student> jsonToStudent =
        s -> new Student(
            s.id,
            s.lastName,
            s.firstName,
            s.patronymic,
            new Group(s.groupId, s.group),
            s.grant);
}
