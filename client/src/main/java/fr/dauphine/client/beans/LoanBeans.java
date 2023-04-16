package fr.dauphine.client.beans;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class LoanBeans implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    private String isbn;


    private Long readerId;

    private LocalDate loanDate;

    private LocalDate returnDate;
}