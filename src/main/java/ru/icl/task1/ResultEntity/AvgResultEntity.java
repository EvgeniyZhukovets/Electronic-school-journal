package ru.icl.task1.ResultEntity;

import lombok.*;

import javax.persistence.Entity;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
public class AvgResultEntity {

    private String surname;
    private  String name;
    private String patronymic;
    private Integer avg;

}
