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
@Table(name = "assessment")
public class Assessment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "assessment")
    private Integer assessment;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "assessment_teacher", joinColumns = @JoinColumn(name = "assessment_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "teacher_id", referencedColumnName = "id"))
    private Set<Teacher> teacher = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "assessment_student", joinColumns = @JoinColumn(name = "assessment_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "student_id", referencedColumnName = "id"))
    private Set<Student> student = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "assessment_subject", joinColumns = @JoinColumn(name = "assessment_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "subject_id", referencedColumnName = "id"))
    private Set<Subject> subject = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Assessment that = (Assessment) o;

        if (id != that.id) return false;
        if (assessment != null ? !assessment.equals(that.assessment) : that.assessment != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        Integer result = id;
        result = 31 * result + (assessment != null ? assessment.hashCode() : 0);
        return result;
    }
}
