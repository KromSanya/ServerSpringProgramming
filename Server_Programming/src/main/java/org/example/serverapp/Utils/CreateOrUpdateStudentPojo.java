package org.example.serverapp.Utils;

import lombok.*;


@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrUpdateStudentPojo {
    private String firstName;
    private String surname;
    private String patronymic;
    private String status;
    private long group_id;
}
