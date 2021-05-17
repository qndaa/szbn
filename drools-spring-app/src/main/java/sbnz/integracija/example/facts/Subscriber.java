package sbnz.integracija.example.facts;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sbnz.integracija.example.enums.CategoryOfUser;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@PrimaryKeyJoinColumn(name = "user_id")
@Table(name = "SUBSCRIBERS")
public class Subscriber extends User{
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "SUBSCRIBER_ON_COURSES",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id", referencedColumnName = "course_id"))
    private Set<Course> subscribedCourses = new HashSet<>();

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "category")
    private CategoryOfUser categoryOfUser;

    @Column(name="discount")
    private Double discount;
}
