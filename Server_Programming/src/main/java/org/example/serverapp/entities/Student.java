package org.example.serverapp.entities;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    @Min(1)
    private long id;

    @Size(min = 3, max = 32)
    private String firstName;

    @Size(min = 3, max = 32)
    private String surname;

    @Size(min = 3, max = 32)
    private String patronymic;

    @Size(min = 3, max = 32)
    private String status;

    @Min(1)
    private long group_id;
}
