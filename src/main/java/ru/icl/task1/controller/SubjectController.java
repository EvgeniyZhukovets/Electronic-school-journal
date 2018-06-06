package ru.icl.task1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.icl.task1.dto.GroupDto;
import ru.icl.task1.dto.SubjectDto;
import ru.icl.task1.service.SubjectService;
import ru.icl.task1.service.TeacherService;

@Controller
public class SubjectController {
    @Autowired
    SubjectService subjectService;

    @Autowired
    TeacherService teacherService;

    @GetMapping("subject")
    public String getAllSubjects(Model model) {
        model.addAttribute("subjects", subjectService.findAll());
        model.addAttribute("teachers", teacherService.findAll());

        return "subjects";
    }

    @PostMapping("subject")
    public String getSubjectCreated(@ModelAttribute("subjectForm") SubjectDto subjectDto) {
        subjectService.createSubject(subjectDto);

        return "redirect:/subject";
    }

}
