package sbnz.integracija.example.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AuthorSubscriberReport {
    private UUID id;
    private String name;
    private String surname;
    private String username;

    public AuthorSubscriberReport(String name, String surname, String username) {
        this.name = name;
        this.surname = surname;
        this.username = username;
    }
}
