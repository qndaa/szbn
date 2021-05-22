package sbnz.integracija.example.events;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.kie.api.definition.type.Role;
import org.kie.api.definition.type.Timestamp;

import java.util.Date;
import java.util.UUID;

@Role(Role.Type.EVENT)
@Timestamp("timestamp")
@Getter
@Setter
@NoArgsConstructor
public class CourseSearchEvent {
    private UUID userId;
    private Date timestamp;

    public CourseSearchEvent(UUID userId) {
        this.userId = userId;
        this.timestamp = new Date();
    }
}
