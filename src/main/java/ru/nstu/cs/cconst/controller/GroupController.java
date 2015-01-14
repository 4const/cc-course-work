package ru.nstu.cs.cconst.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.nstu.cs.cconst.controller.json.GroupJson;
import ru.nstu.cs.cconst.model.Group;
import ru.nstu.cs.cconst.model.Student;
import ru.nstu.cs.cconst.service.GroupService;
import ru.nstu.cs.cconst.service.StudentService;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Controller
public class GroupController {

    @Autowired
    private GroupService groupService;

    @Autowired
    private StudentService studentService;

    @RequestMapping(value = "/group", method = RequestMethod.GET)
    @ResponseBody
    public List<GroupJson> getAll() throws Throwable {
        return groupService.all().stream()
            .map(groupToJson)
            .collect(Collectors.toList());
    }

    @RequestMapping(value = "/group", method = RequestMethod.PUT)
    @ResponseBody
    public GroupJson save(@RequestBody GroupJson groupJson) throws Throwable {
        Group saved = groupService.save(jsonToGroup.apply(groupJson));

        return groupToJson.apply(saved);
    }

    @RequestMapping(value = "/group", method = RequestMethod.DELETE)
    @ResponseBody
    public boolean save(@RequestParam Integer id) throws Throwable {
        return groupService.delete(id);
    }

    private Function<Group, GroupJson> groupToJson =
        g -> {
            List<Student> students = studentService.getByGroup(g.getId());
            return new GroupJson(
                g.getId(),
                g.getName(),
                students.stream().collect(Collectors.summingDouble(Student::getGrant)));
        };

    private Function<GroupJson, Group> jsonToGroup = g -> new Group(g.id, g.name);
}
