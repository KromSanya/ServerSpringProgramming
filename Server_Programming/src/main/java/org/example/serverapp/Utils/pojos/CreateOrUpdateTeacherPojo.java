package org.example.serverapp.Utils.pojos;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrUpdateTeacherPojo {
    private String firstName;
    private String surname;
    private String patronymic;
}
