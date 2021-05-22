package sbnz.integracija.example.facts;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.kie.api.definition.type.Position;

import javax.persistence.*;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "AREAS")
public class Area {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "area_id", nullable = false, unique = true)
    private UUID areaId;

    @Position(0)
    @Column(name = "name", nullable = false, unique = true)
    private String name;
}
