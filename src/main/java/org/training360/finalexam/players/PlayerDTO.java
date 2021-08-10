package org.training360.finalexam.players;

import lombok.*;

import java.time.LocalDate;

@Data
@AllArgsConstructor
 @NoArgsConstructor
public class PlayerDTO {

    private Long id;

    private String name;

    private LocalDate dateOfBirth;

    private PositionType position;

}
