package ru.icl.task1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.icl.task1.model.Group;
import ru.icl.task1.model.Teacher;

import java.util.List;

public interface GroupRepository extends JpaRepository<Group, Long> {

    Group getGroupById(Integer id);

    List<Group> findGroupsByTeacher(Teacher teacher);

}
