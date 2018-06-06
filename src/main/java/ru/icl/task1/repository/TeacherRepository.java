package ru.icl.task1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.icl.task1.model.Teacher;
import java.util.Set;

public interface TeacherRepository extends JpaRepository<Teacher, Integer> {
    Teacher findTeacherById(Integer id);
    void removeTeacherById(Integer id);
}
