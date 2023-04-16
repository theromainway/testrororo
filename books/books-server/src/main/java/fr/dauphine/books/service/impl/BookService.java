package fr.dauphine.books.service.impl;




import fr.dauphine.books.service.dto.BookDto;


import java.util.List;
import java.util.Optional;

public interface BookService {

    List<BookDto> findAll();

    BookDto save(BookDto bookDto);

    BookDto update(BookDto bookDto);

    void deleteById(String isbn);

    Optional<BookDto> findByIsbn(String isbn);

}
