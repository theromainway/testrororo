package fr.dauphine.client.beans;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ReaderBeans implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    private String gender;

    private String lastName;

    private String firstName;

    private LocalDate dateOfBirth;

    private String address;
}
