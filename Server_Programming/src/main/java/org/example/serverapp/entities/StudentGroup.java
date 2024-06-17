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
public class StudentGroup {

    @Min(1)
    private long id;

    @Size(min = 3, max = 32)
    private String name;
}

