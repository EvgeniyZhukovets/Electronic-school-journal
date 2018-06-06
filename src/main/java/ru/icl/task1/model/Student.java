package ru.icl.task1.model;

import lombok.*;
import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString

@Entity
@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "surname")
    private String surname;

    @Column(name = "name")
    private String name;

    @Column(name = "patronymic")
    private String patronymic;

    @Column(name = "birth_date")
    private Date birthDate;

    @ManyToOne
    @JoinColumn(name="group_id", nullable = false)
    private Group group;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "subject_student", joinColumns = @JoinColumn(name = "student_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "subject_id", referencedColumnName = "id"))
    private Set<Subject> subject = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "assessment_student", joinColumns = @JoinColumn(name = "student_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "assessment_id", referencedColumnName = "id"))
    private Set<Assessment> assessment = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Student that = (Student) o;

        if (id != that.id) return false;
        if (surname != null ? !surname.equals(that.surname) : that.surname != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (patronymic != null ? !patronymic.equals(that.patronymic) : that.patronymic != null) return false;
        if (birthDate != null ? !birthDate.equals(that.birthDate) : that.birthDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        Integer result = id;
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (patronymic != null ? patronymic.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (birthDate != null ? birthDate.hashCode() : 0);
        return result;
    }

}
