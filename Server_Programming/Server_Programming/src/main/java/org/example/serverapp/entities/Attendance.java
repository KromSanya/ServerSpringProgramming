package org.example.serverapp.entities;

import jakarta.validation.constraints.Min;
import lombok.*;

import java.util.Map;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Attendance {

    @Min(1)
    private long id;

    @Min(1)
    private long studentId;

    @Min(1)
    private long lessonId;
}
