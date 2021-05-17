package sbnz.integracija.example.facts.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sbnz.integracija.example.enums.LevelOfCourse;
import sbnz.integracija.example.enums.PopularityCategory;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
public class CourseSearchDTO {
    private String title;
    private String area;
    private String author;
    private int grade;
    private double price;
    private LocalDateTime year;
    private LevelOfCourse level;
    private PopularityCategory popularity;
}
