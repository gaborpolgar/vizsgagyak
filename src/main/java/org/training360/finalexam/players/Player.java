package org.training360.finalexam.players;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "players")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Enumerated
    public PositionType position;

    public Player(String name, LocalDate dateOfBirth, PositionType position) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.position = position;
    }
}
