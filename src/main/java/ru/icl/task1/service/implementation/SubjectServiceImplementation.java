package ru.icl.task1.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.icl.task1.dto.AssessmentDto;
import ru.icl.task1.dto.IdDto;
import ru.icl.task1.dto.SubjectDto;
import ru.icl.task1.model.Assessment;
import ru.icl.task1.model.Subject;
import ru.icl.task1.repository.AssessmentRepository;
import ru.icl.task1.repository.StudentRepository;
import ru.icl.task1.repository.SubjectRepository;
import ru.icl.task1.repository.TeacherRepository;
import ru.icl.task1.service.SubjectService;

import java.util.Iterator;
import java.util.List;
@Service
public class SubjectServiceImplementation implements SubjectService {

    @Autowired
    SubjectRepository subjectRepository;

    @Autowired
    TeacherRepository teacherRepository;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    AssessmentRepository assessmentRepository;

    @Override
    public List<Subject> findAll() {
        return subjectRepository.findAll();
    }

    @Override
    public Subject createSubject(SubjectDto subjectDto) {
        Subject newSubject = Subject.builder()
                .title(subjectDto.getTitle())
                .build();
        subjectRepository.save(newSubject);
        return newSubject;
    }

    @Override
    public List<Subject> findAllByStudentIdAndTeacherId(Integer student_id, Integer teacher_id) {
        return subjectRepository.findAllByStudentAndTeacher(studentRepository.getStudentById(student_id), teacherRepository.findTeacherById(teacher_id));
    }

    @Override
    public List<Subject> findSubjectsByTeacherId(Integer id) {
        return subjectRepository.findAllByTeacher(teacherRepository.findTeacherById(id));
    }

    @Override
    public List<Subject> findAllSubjectsByStudentId(Integer id) {
        return subjectRepository.findAllByStudent(studentRepository.getStudentById(id));
    }

    @Override
    public Assessment changeAssessment(Integer student_id, Integer teacher_id, Integer subject_id, AssessmentDto assessmentDto) {
        Assessment assessment = assessmentRepository.getAssessmentByTeacherAndStudentAndSubject(teacherRepository.findTeacherById(teacher_id), studentRepository.getStudentById(student_id), subjectRepository.getSubjectById(subject_id));
        if(assessment == null){
            assessment = new Assessment();
            assessment.getStudent().add(studentRepository.getStudentById(student_id));
            assessment.getTeacher().add(teacherRepository.findTeacherById(teacher_id));
            assessment.getSubject().add(subjectRepository.getSubjectById(subject_id));
        }
        assessment.setAssessment(assessmentDto.getAssessment());
        assessmentRepository.save(assessment);
        return assessment;
    }

    @Override
    public List<Assessment> getAssessmentsByStudentIdAndTeacherIdAndSubjectId(Integer student_id, Integer teacher_id, Integer subject_id) {
        return assessmentRepository.getAssessmentsByTeacherAndStudentAndSubject(teacherRepository.findTeacherById(teacher_id), studentRepository.getStudentById(student_id), subjectRepository.getSubjectById(subject_id));
    }
}
