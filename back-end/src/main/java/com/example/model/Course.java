package com.example.model;


import com.example.enums.LevelOfCourse;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

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
    private Set<Area> courseAres = new HashSet<>();

    @Column(name = "duration", nullable = false)
    private double duration;

    @Column(name = "price", nullable = false)
    private double price;

    @OneToMany
    private Set<Mark> marks = new HashSet<>();

    @Column(name = "year", nullable = false)
    private LocalDateTime year;

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

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Teacher teacher;
}
