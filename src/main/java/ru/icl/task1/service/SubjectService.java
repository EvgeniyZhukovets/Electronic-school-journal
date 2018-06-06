package ru.icl.task1.service;

import ru.icl.task1.dto.AssessmentDto;
import ru.icl.task1.dto.IdDto;
import ru.icl.task1.dto.SubjectDto;
import ru.icl.task1.model.Assessment;
import ru.icl.task1.model.Student;
import ru.icl.task1.model.Subject;
import java.util.List;
import java.util.Set;

public interface SubjectService {

    List<Subject> findAll();

    Subject createSubject(SubjectDto subjectDto);

    List<Subject> findAllByStudentIdAndTeacherId(Integer student_id, Integer teacher_id);

    List<Subject> findSubjectsByTeacherId(Integer id);

    List<Subject> findAllSubjectsByStudentId(Integer id);

    Assessment changeAssessment(Integer student_id, Integer teacher_id, Integer subject_id, AssessmentDto assessmentDto);

    List<Assessment> getAssessmentsByStudentIdAndTeacherIdAndSubjectId(Integer student_id, Integer teacher_id, Integer subject_id);

}
