package ru.icl.task1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.icl.task1.model.Student;
import ru.icl.task1.model.Subject;
import ru.icl.task1.model.Teacher;

import java.util.List;

public interface SubjectRepository extends JpaRepository<Subject, Integer> {

    List<Subject> findAllByTeacher(Teacher teacher);

    Subject getSubjectById(Integer id);

    List<Subject> getSubjectsById(Integer id);

    List<Subject> findAllByStudentAndTeacher(Student student, Teacher teacher);

    List<Subject> findAllByStudent(Student student);
}
