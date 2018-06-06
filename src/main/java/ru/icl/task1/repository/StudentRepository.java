package ru.icl.task1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.icl.task1.model.Student;
import ru.icl.task1.model.Subject;
import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findAllBySubject(Subject subject);

    Student getStudentById(Integer id);

}
