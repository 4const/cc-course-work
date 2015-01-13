package ru.nstu.cs.cconst.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.nstu.cs.cconst.GroupHasStudents;
import ru.nstu.cs.cconst.model.Group;
import ru.nstu.cs.cconst.repository.GroupRepository;
import ru.nstu.cs.cconst.repository.StudentRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class GroupService {

	@Autowired
	private GroupRepository groupRepository;

    @Autowired
	private StudentRepository studentRepository;

    public Group save(Group group) {
        return groupRepository.save(group);
    }

    public Group get(Integer id) {
        return groupRepository.findOne(id);
    }

	public List<Group> all() {
		return groupRepository.findAll();
	}

	public void delete(Integer id) {
		Group group = groupRepository.findOne(id);

		if (group != null) {
            if (studentRepository.findByGroup(id).isEmpty()) {
                groupRepository.delete(group);
            } else {
                throw new GroupHasStudents(group.getId());
            }
		}
	}
}
