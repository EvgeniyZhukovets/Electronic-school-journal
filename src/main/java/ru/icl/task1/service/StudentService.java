package ru.icl.task1.service;

import ru.icl.task1.dto.IdDto;
import ru.icl.task1.dto.StudentDto;
import ru.icl.task1.model.Student;
import ru.icl.task1.model.Teacher;

import java.util.List;

public interface StudentService {

    List<Student> findAll();

    Student createStudent(StudentDto studentDto);

    List<Student> findAllStudentsBySubjectId(Integer id);

    Student addSubjectById(Integer student_id, IdDto idDto);

}
