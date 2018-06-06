package ru.icl.task1.service;

import ru.icl.task1.dto.GroupDto;
import ru.icl.task1.dto.StudentDto;
import ru.icl.task1.model.Group;
import ru.icl.task1.model.Student;

import java.util.List;

public interface GroupService {

    List<Group> findAll();

    Group createGroup(GroupDto groupDto);

    List<Group> findGroupsByTeachersId(Integer id);

}
