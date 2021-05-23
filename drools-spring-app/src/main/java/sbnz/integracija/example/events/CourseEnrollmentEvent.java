package sbnz.integracija.example.events;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.kie.api.definition.type.Expires;
import org.kie.api.definition.type.Role;
import org.kie.api.definition.type.Timestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Role(Role.Type.EVENT)
@Expires("1h")
@Timestamp("timestamp")
@Getter
@Setter
@NoArgsConstructor
public class CourseEnrollmentEvent {
    private UUID userId;
    private UUID courseId;
    private LocalDateTime timestamp;

    public CourseEnrollmentEvent(UUID userId, UUID courseId) {
        this.userId = userId;
        this.courseId = courseId;
        this.timestamp = LocalDateTime.now();
    }
}
