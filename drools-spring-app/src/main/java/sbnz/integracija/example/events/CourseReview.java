package sbnz.integracija.example.events;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.kie.api.definition.type.Role;
import org.kie.api.definition.type.Timestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Role(Role.Type.EVENT)
@Timestamp("timestamp")
@Getter
@Setter
@NoArgsConstructor
public class CourseReview {

    private UUID courseId;
    private LocalDateTime timestamp;

    public CourseReview(UUID courseId) {
        this.courseId = courseId;
        this.timestamp = LocalDateTime.now();
    }

}
