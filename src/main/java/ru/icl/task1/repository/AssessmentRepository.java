package ru.icl.task1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.icl.task1.ResultEntity.AvgResultEntity;
import ru.icl.task1.model.Assessment;
import ru.icl.task1.model.Student;
import ru.icl.task1.model.Subject;
import ru.icl.task1.model.Teacher;

import java.util.List;

public interface AssessmentRepository extends JpaRepository<Assessment, Integer> {

    List<Assessment> getAssessmentsByTeacherAndStudentAndSubject(Teacher teacher, Student student, Subject subject);

    Assessment getAssessmentByTeacherAndStudentAndSubject(Teacher teacher, Student student, Subject subject);

    List<Assessment> getAssessmentsByStudent(Student student);

    @Query(value = "SELECT stud.surname,  stud.name, stud.patronymic, ROUND(avg(a.assessment),2) AS av FROM assessment a INNER JOIN assessment_student a_s on a.id = a_s.assessment_id INNER JOIN student stud on a_s.student_id = stud.id GROUP BY stud.surname, stud.name, stud.patronymic ORDER BY av ASC FETCH FIRST 3 ROWS ONLY",
            nativeQuery=true)
    List assessmentAverageASC();

    @Query(value = "SELECT stud.surname,  stud.name, stud.patronymic, ROUND(avg(a.assessment),2) AS av FROM assessment a INNER JOIN assessment_student a_s on a.id = a_s.assessment_id INNER JOIN student stud on a_s.student_id = stud.id GROUP BY stud.surname, stud.name, stud.patronymic ORDER BY av DESC FETCH FIRST 3 ROWS ONLY",
            nativeQuery=true)
    List assessmentAverageDESC();

}
