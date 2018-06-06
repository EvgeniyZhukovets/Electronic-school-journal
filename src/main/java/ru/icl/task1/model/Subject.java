package ru.icl.task1.model;

import lombok.*;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString

@Entity
@Table(name = "subject")
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "title")
    private String title;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "subject_teacher", joinColumns = @JoinColumn(name = "id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "teacher_id", referencedColumnName = "id"))
    private Set<Teacher> teacher = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "subject_student", joinColumns = @JoinColumn(name = "subject_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "student_id", referencedColumnName = "id"))
    private Set<Student> student = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "assessment_subject", joinColumns = @JoinColumn(name = "subject_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "assessment_id", referencedColumnName = "id"))
    private Set<Assessment> assessments = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Subject that = (Subject) o;

        if (id != that.id) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        Integer result = id;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        return result;
    }
}
