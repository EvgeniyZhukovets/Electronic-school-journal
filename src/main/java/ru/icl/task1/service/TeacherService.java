package ru.icl.task1.service;

import ru.icl.task1.dto.IdDto;
import ru.icl.task1.dto.TeacherDto;
import ru.icl.task1.model.Teacher;
import java.util.List;

public interface TeacherService {

    List<Teacher> findAll();

    Teacher createTeacher(TeacherDto teacherDto);

    Teacher addSubjectForTeacher(Integer teacherId, IdDto idDto);

    Teacher addGroupForTeacher(Integer teacherId, IdDto idDto);
}
