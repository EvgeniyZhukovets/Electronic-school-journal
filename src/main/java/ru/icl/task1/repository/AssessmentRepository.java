package ru.icl.task1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.icl.task1.model.Assessment;
import ru.icl.task1.model.Student;
import ru.icl.task1.model.Subject;
import ru.icl.task1.model.Teacher;

import java.util.List;

public interface AssessmentRepository extends JpaRepository<Assessment, Integer> {
    List<Assessment> getAssessmentsByTeacherAndStudentAndSubject(Teacher teacher, Student student, Subject subject);
    Assessment getAssessmentByTeacherAndStudentAndSubject(Teacher teacher, Student student, Subject subject);
}
