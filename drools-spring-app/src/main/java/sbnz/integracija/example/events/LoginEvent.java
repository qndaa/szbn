package sbnz.integracija.example.events;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.kie.api.definition.type.Expires;
import org.kie.api.definition.type.Role;

import java.util.Date;
import java.util.UUID;

@Role(Role.Type.EVENT)
@Getter
@Setter
@NoArgsConstructor
public class LoginEvent {
    private UUID userId;
    private Date timestamp;

    public LoginEvent(UUID userId) {
        this.userId = userId;
        this.timestamp = new Date();
    }
}
