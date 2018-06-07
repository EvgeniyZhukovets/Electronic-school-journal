package ru.icl.task1.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import ru.icl.task1.ResultEntity.AvgResultEntity;
import ru.icl.task1.dto.IdDto;
import ru.icl.task1.dto.StudentDto;
import ru.icl.task1.model.Assessment;
import ru.icl.task1.model.Student;
import ru.icl.task1.model.Teacher;
import ru.icl.task1.repository.*;
import ru.icl.task1.service.StudentService;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Service
public class StudentServiceImplementation implements StudentService {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    TeacherRepository teacherRepository;

    @Autowired
    SubjectRepository subjectRepository;

    @Autowired
    GroupRepository groupRepository;

    @Autowired
    AssessmentRepository assessmentRepository;

    @Override
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    @Override
    public Student createStudent(StudentDto studentDto) {
        Student newStudent = Student.builder()
                .surname(studentDto.getSurname())
                .name(studentDto.getName())
                .patronymic(studentDto.getPatronymic())
                .birthDate(studentDto.getBirthDate())
                .group(groupRepository.getGroupById(studentDto.getGroup_id()))
                .build();

        studentRepository.save(newStudent);
        return newStudent;
    }

    @Override
    public List<Student> findAllStudentsBySubjectId(Integer id) {
        return studentRepository.findAllBySubject(subjectRepository.getSubjectById(id));
    }

    @Override
    public Student addSubjectById(Integer student_id, IdDto idDto) {
        Student student = studentRepository.getStudentById(student_id);
        student.getSubject().add(subjectRepository.getSubjectById(idDto.getId()));
        studentRepository.save(student);
        return student;
    }

    @Override
    public List getTopStudentsASC() {
        return assessmentRepository.assessmentAverageASC();
    }

    @Override
    public List getTopStudentsDESC() {
        return assessmentRepository.assessmentAverageDESC();
    }
}
