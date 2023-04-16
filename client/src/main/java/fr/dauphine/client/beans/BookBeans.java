package fr.dauphine.client.beans;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookBeans {
    private String isbn;
    private String author;
    private String title;
    private String editor;
    private String editing;
}