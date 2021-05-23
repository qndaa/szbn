package sbnz.integracija.example.facts.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sbnz.integracija.example.enums.LevelOfCourse;
import sbnz.integracija.example.enums.PopularityCategory;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
public class CourseSearchDTO {
    private UUID userId;
    private String title;
    private String area;
    private String author;
    private int grade;
    private double price;
    private LocalDate year;
    private LevelOfCourse level;
    private PopularityCategory popularity;
}
