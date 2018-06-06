package ru.icl.task1.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.icl.task1.dto.GroupDto;
import ru.icl.task1.model.Group;
import ru.icl.task1.repository.GroupRepository;
import ru.icl.task1.repository.TeacherRepository;
import ru.icl.task1.service.GroupService;

import java.util.List;

@Service
public class GroupServiceImplementation implements GroupService {

    @Autowired
    GroupRepository groupRepository;

    @Autowired
    TeacherRepository teacherRepository;

    @Override
    public List<Group> findAll() {
        return groupRepository.findAll();
    }

    @Override
    public Group createGroup(GroupDto groupDto) {
        Group newGroup = Group.builder()
                .title(groupDto.getTitle())
                .build();
        groupRepository.save(newGroup);

        return newGroup;
    }

    @Override
    public List<Group> findGroupsByTeachersId(Integer id) {
        return groupRepository.findGroupsByTeacher(teacherRepository.findTeacherById(id));
    }

}
