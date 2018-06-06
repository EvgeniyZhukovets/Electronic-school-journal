package ru.icl.task1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.icl.task1.dto.GroupDto;
import ru.icl.task1.dto.StudentDto;
import ru.icl.task1.service.GroupService;
import ru.icl.task1.service.TeacherService;

@Controller
public class GroupContoller {
    @Autowired
    GroupService groupService;

    @Autowired
    TeacherService teacherService;

    @GetMapping("groups")
    public String getAllGroups(Model model) {
        model.addAttribute("groups", groupService.findAll());
        model.addAttribute("teachers",teacherService.findAll());

        return "groups";
    }

    @PostMapping("groups")
    public String getGroupCreated(@ModelAttribute("studentForm") GroupDto groupDto) {
        groupService.createGroup(groupDto);

        return "redirect:/groups";
    }

}
