package ru.icl.task1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.icl.task1.dto.AssessmentDto;
import ru.icl.task1.dto.IdDto;
import ru.icl.task1.dto.TeacherDto;
import ru.icl.task1.service.GroupService;
import ru.icl.task1.service.StudentService;
import ru.icl.task1.service.SubjectService;
import ru.icl.task1.service.TeacherService;

@Controller
public class TeacherController {
    @Autowired
    TeacherService teacherService;

    @Autowired
    SubjectService subjectService;

    @Autowired
    GroupService groupService;

    @Autowired
    StudentService studentService;

    @GetMapping("teacher")
    public String getAllTeachers(Model model) {
        model.addAttribute("teachers", teacherService.findAll());

        return "teachers";
    }

    @PostMapping("teacher")
    public String getTeacherCreated(@ModelAttribute("teacherForm") TeacherDto teacherDto) {
        teacherService.createTeacher(teacherDto);

        return "redirect:/teacher";
    }

    @GetMapping("teacher/{teacher_id}/subject")
    public String getAllSubjectsByTeacher(Model model, @PathVariable("teacher_id") Integer teacher_id) {
        model.addAttribute("subjects", subjectService.findSubjectsByTeacherId(teacher_id));
        model.addAttribute("allSubjects", subjectService.findAll());
        model.addAttribute("teacher_id", teacher_id);

        return "teachers_subjects";
    }

    @PostMapping("teacher/{teacher_id}/subject")
    public String getSubjectAdded(@ModelAttribute("subjectForm") IdDto idDto, @PathVariable("teacher_id") Integer id) {
        teacherService.addSubjectForTeacher(id, idDto);

        return "redirect:/teacher/" + id + "/subject";
    }

    @GetMapping("teacher/{teacher_id}/subject/{subject_id}/student")
    public String getAllStudentsBySubject(Model model, @PathVariable("teacher_id") Integer teacher_id, @PathVariable("subject_id") Integer subject_id) {
        model.addAttribute("students", studentService.findAllStudentsBySubjectId(subject_id));
        model.addAttribute("teacher_id", teacher_id);

        return "teacher_subject_students";
    }

    @GetMapping("teacher/{teacher_id}/subject/{subject_id}/student/{student_id}")
    public String getAllStudentsBySubject(Model model, @PathVariable("teacher_id") Integer teacher_id, @PathVariable("subject_id") Integer subject_id, @PathVariable("student_id") Integer student_id) {
        model.addAttribute("assessment", subjectService.getAssessmentsByStudentIdAndTeacherIdAndSubjectId(student_id,teacher_id,subject_id));
        model.addAttribute("teacher_id" ,teacher_id);
        model.addAttribute("subject_id" , subject_id);
        model.addAttribute("student_id", student_id);
        return "teacher_subject_student_assessment";
    }
    @PostMapping("teacher/{teacher_id}/subject/{subject_id}/student/{student_id}")
    public String getAssessmentAdded(@ModelAttribute("assessForm")AssessmentDto assessmentDto, @PathVariable("teacher_id") Integer teacher_id, @PathVariable("subject_id") Integer subject_id, @PathVariable("student_id") Integer student_id) {
        subjectService.changeAssessment(student_id,teacher_id,subject_id,assessmentDto);

        return "redirect:/teacher/" + teacher_id + "/subject/" + subject_id + "/student/" + student_id;
    }

    @GetMapping("teacher/{teacher_id}/group")
    public String getAllGroupsByTeacher(Model model, @PathVariable("teacher_id") Integer id) {
        model.addAttribute("groups", groupService.findGroupsByTeachersId(id));
        model.addAttribute("allGroups", groupService.findAll());

        return "teachers_groups";
    }

    @PostMapping("teacher/{teacher_id}/group")
    public String getGroupAdded(@ModelAttribute("subjectForm") IdDto idDto, @PathVariable("teacher_id") Integer id) {
        teacherService.addGroupForTeacher(id, idDto);

        return "redirect:/teacher/" + id + "/group";
    }

}
