package org.example.serverapp.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.sql.Date;
import java.sql.Time;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Lesson {

    @Min(1)
    private long id;

    private Date date;

    private Time time;

    @Min(1)
    private long teacherId;

    @Min(1)
    private long subjectId;

    @Min(1)
    private long groupId;
}
