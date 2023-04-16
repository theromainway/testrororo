package fr.dauphine.books.service.dto;

import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class BookDto implements Serializable  {

    private static final long serialVersionUID = 1L;

    private String isbn;

    private String author;

    private String title;

    private String editor;

    private String editing;
}
