package com.example.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "MARKS")
public class Mark {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "mark_id", nullable = false, unique = true)
    private UUID markId;

    @Column(name = "value")
    private int value;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Subscriber subscriber;

}
