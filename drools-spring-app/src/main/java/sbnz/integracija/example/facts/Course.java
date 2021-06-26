package sbnz.integracija.example.facts;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.kie.api.definition.type.Position;
import sbnz.integracija.example.enums.LevelOfCourse;
import sbnz.integracija.example.enums.PopularityCategory;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "COURSES")
public class Course {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "course_id", nullable = false, unique = true)
    private UUID courseId;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "COURSE_AREA",
            joinColumns = @JoinColumn(name = "course_id", referencedColumnName = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "area_id", referencedColumnName = "area_id"))
    private Set<Area> courseAreas = new HashSet<>();

    @Column(name = "duration", nullable = false)
    private double duration;

//    @Position(0)
    @Column(name = "price", nullable = false)
    private double price;

//    @Position(2)
    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", nullable = false)
    private String description;

//    @OneToMany
//    private Set<Mark> marks = new HashSet<>();

//    @Position(3)
    @Column(name = "year", nullable = false)
    private LocalDate year;

//    @Position(4)
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "level", nullable = false)
    private LevelOfCourse levelOfCourse;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "PRECONDITIONS",
            joinColumns = @JoinColumn(name = "course_id", referencedColumnName = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "precondition_id", referencedColumnName = "course_id"))
    private Set<Course> preconditions = new HashSet<>();


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "SKILL_BY_COURSE",
            joinColumns = @JoinColumn(name = "course_id", referencedColumnName = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "skill_id", referencedColumnName = "skill_id"))
    private Set<Skill> skills = new HashSet<>();

//    @Position(5)
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Teacher teacher;

//    @Position(6)
    @Enumerated(EnumType.ORDINAL)
    @Column
    private PopularityCategory popularity;


    @Column(name = "discountByEnrollment")
    private double discountByEnrollment;

    @Column(name = "discountByReview")
    private double discountByReview;

    private boolean precondition;
}
