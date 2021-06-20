package sbnz.integracija.example.facts;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.kie.api.definition.type.Position;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class PrerequisiteCourses {
    @Position(0)
    private UUID isPrerequisiteId;
    @Position(1)
    private UUID toCourseId;
}
