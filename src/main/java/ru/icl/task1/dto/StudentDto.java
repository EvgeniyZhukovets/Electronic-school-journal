package ru.icl.task1.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.icl.task1.model.Group;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentDto {
    private String name;
    private String surname;
    private String patronymic;
    private Date birthDate;
    private Integer group_id;
}
