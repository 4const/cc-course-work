package ru.nstu.cs.cconst.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.nstu.cs.cconst.controller.json.StudentJson;
import ru.nstu.cs.cconst.model.Group;
import ru.nstu.cs.cconst.model.Student;
import ru.nstu.cs.cconst.security.SecurityContext;
import ru.nstu.cs.cconst.service.StudentService;

import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Controller
public class StudentController {

	@Autowired
	private StudentService studentService;

	@PreAuthorize("hasRole('ROLE_USER') || hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/student", method = RequestMethod.GET)
	@ResponseBody
	public List<StudentJson> getAll() throws Throwable {
		List<StudentJson> jsons = studentService.all().stream()
			.map(studentToJson)
			.collect(Collectors.toList());

		Comparator<StudentJson> comparator =
			SecurityContext.isAdmin() ?
				(l, r) -> l.lastName.compareTo(r.lastName) :
				(l, r) -> {
					int groupCompare = Integer.compare(l.groupId, r.groupId);
					return groupCompare == 0 ?
						l.lastName.compareTo(r.lastName) : groupCompare;
				};
		jsons.sort(comparator);

		return jsons;
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/student", method = RequestMethod.DELETE)
	@ResponseBody
	public void delete(@RequestParam("id") Integer id) throws Throwable {
		studentService.delete(id);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
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
