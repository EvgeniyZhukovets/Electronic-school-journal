package ru.icl.task1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.icl.task1.dto.IdDto;
import ru.icl.task1.dto.StudentDto;
import ru.icl.task1.dto.SubjectDto;
import ru.icl.task1.service.GroupService;
import ru.icl.task1.service.StudentService;
import ru.icl.task1.service.SubjectService;
import ru.icl.task1.service.TeacherService;

@Controller
public class StudentController {
    @Autowired
    StudentService studentService;

    @Autowired
    TeacherService teacherService;

    @Autowired
    GroupService groupService;

    @Autowired
    SubjectService subjectService;

    @GetMapping("students")
    public String getAllStudents(Model model){
        model.addAttribute("students", studentService.findAll());
        model.addAttribute("teachers",teacherService.findAll());
        model.addAttribute("groups", groupService.findAll());
        return "students";
    }

    @PostMapping("students")
    public String getStudentCreated(@ModelAttribute("studentForm") StudentDto studentDto) {
        studentService.createStudent(studentDto);

        return "redirect:/students";
    }

    @GetMapping("students/{student_id}/subject")
    public String getAllStudents(Model model, @PathVariable("student_id") Integer student_id){
        model.addAttribute("subjects",subjectService.findAllSubjectsByStudentId(student_id));
        model.addAttribute("allSubjects",subjectService.findAll());
        return "student_subject";
    }

    @PostMapping("students/{student_id}/subject")
    public String getSubjectAdded(@ModelAttribute("subjectForm")IdDto idDto, @PathVariable("student_id") Integer student_id) {
        studentService.addSubjectById(student_id, idDto);

        return "redirect:/students/" + student_id + "/subject";
    }

}
