package ru.icl.task1.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.icl.task1.dto.IdDto;
import ru.icl.task1.dto.TeacherDto;
import ru.icl.task1.model.Teacher;
import ru.icl.task1.repository.GroupRepository;
import ru.icl.task1.repository.SubjectRepository;
import ru.icl.task1.repository.TeacherRepository;
import ru.icl.task1.service.TeacherService;

import java.util.List;

@Service
public class TeacherServiceImplementation implements TeacherService {

    @Autowired
    TeacherRepository teacherRepository;

    @Autowired
    SubjectRepository subjectRepository;

    @Autowired
    GroupRepository groupRepository;

    @Override
    public List<Teacher> findAll() {
        return teacherRepository.findAll();
    }

    @Override
    public Teacher createTeacher(TeacherDto teacherDto) {
        Teacher newTeacher = Teacher.builder()
                .name(teacherDto.getName())
                .surname(teacherDto.getSurname())
                .patronymic(teacherDto.getPatronymic())
                .build();
        teacherRepository.save(newTeacher);
        return newTeacher;
    }

    @Override
    @Transactional
    public Teacher addSubjectForTeacher(Integer teacherId, IdDto idDto) {
        Teacher teacher = teacherRepository.findTeacherById(teacherId);
        teacher.getSubject().add(subjectRepository.getSubjectById(idDto.getId()));
        teacherRepository.save(teacher);
        return teacher;
    }

    @Override
    @Transactional
    public Teacher addGroupForTeacher(Integer teacherId, IdDto idDto) {
        Teacher teacher = teacherRepository.findTeacherById(teacherId);
        teacher.getGroup().add(groupRepository.getGroupById(idDto.getId()));
        teacherRepository.save(teacher);
        return teacher;
    }


}
