package ru.icl.task1.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.icl.task1.dto.IdDto;
import ru.icl.task1.dto.StudentDto;
import ru.icl.task1.model.Student;
import ru.icl.task1.model.Teacher;
import ru.icl.task1.repository.GroupRepository;
import ru.icl.task1.repository.StudentRepository;
import ru.icl.task1.repository.SubjectRepository;
import ru.icl.task1.repository.TeacherRepository;
import ru.icl.task1.service.StudentService;
import java.util.List;

@Service
public class StudentServiceImplementation implements StudentService {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    TeacherRepository teacherRepository;

    @Autowired
    SubjectRepository subjectRepository;

    @Autowired
    GroupRepository groupRepository;

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


}
