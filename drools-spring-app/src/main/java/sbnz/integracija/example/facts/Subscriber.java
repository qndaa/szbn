package sbnz.integracija.example.facts;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sbnz.integracija.example.enums.CategoryOfUser;

import javax.persistence.*;
import java.time.LocalDateTime;
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

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "SUBSCRIBER_COMPLETED_COURSES",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id", referencedColumnName = "course_id"))
    private Set<Course> completedCourses = new HashSet<>();


    @Enumerated(EnumType.ORDINAL)
    @Column(name = "category")
    private CategoryOfUser categoryOfUser;

    @Column(name="discount")
    private Double discount;

    @Column(name = "dateOfRegistration")
    private LocalDateTime dateOfRegistration;

    @Column
    private boolean blocked;

    public void incrementDiscount(double d) {
        this.discount += d;
    }

    public void subscribeOnCourse(Course c) {
        subscribedCourses.add(c);
    }

    public LocalDateTime getDate(int m) {
        LocalDateTime l = LocalDateTime.now();
        l.minusMonths(m);
        return l;
    }

    @Transient
    public boolean canSubscribe;

    @Override
    public String toString() {
        return "Subscriber{" +
                "subscribedCourses=" + subscribedCourses +
                ", completedCourses=" + completedCourses +
                ", categoryOfUser=" + categoryOfUser +
                ", discount=" + discount +
                ", dateOfRegistration=" + dateOfRegistration +
                ", blocked=" + blocked +
                ", canSubscribe=" + canSubscribe +
                '}';
    }
}
