package fr.dauphine.books.rest;


import fr.dauphine.books.exception.BookBadRequestException;
import fr.dauphine.books.exception.BookNotFoundException;
import fr.dauphine.books.service.dto.BookDto;
import fr.dauphine.books.service.impl.BookService;
import fr.dauphine.data.domain.Book;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping(path = "/books")
public class BookResource {

    private final BookService bookService;

    public BookResource(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/books")
    public ResponseEntity<List<BookDto>> getAllBooks() {
        log.debug("REST request to get all books");
        final List<BookDto> bookDtos = bookService.findAll();
        return ResponseEntity.ok().body(bookDtos);
    }

    @PostMapping("/books")
    public ResponseEntity<BookDto> createBook(@RequestBody BookDto bookDto) {
        log.debug("REST request to create book: {}", bookDto);
        if (bookDto.getIsbn() != null) {
           throw new BookBadRequestException("Isbn is forbiden");
        }
        final BookDto result = bookService.save(bookDto);
        return ResponseEntity.ok().body(result);
    }

    @PutMapping("/books")
    public ResponseEntity<BookDto> updateBook(@RequestBody BookDto bookDto) {
        log.debug("REST request to create book: {}", bookDto);
        if (bookDto.getIsbn() == null) {
            throw new BookBadRequestException("Isbn is mandatory");
        }
        final BookDto result = bookService.update(bookDto);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/books/{isbn}")
    public ResponseEntity<Void> deleteById(@PathVariable String isbn) {
        log.debug("REST request to delete book by isbn: {}", isbn);
        bookService.deleteById(isbn);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/books/{isbn}")
    public ResponseEntity<BookDto> findByIsbn(@PathVariable String isbn) {
        log.debug("REST request to get book by isbn: {}", isbn);
        return bookService.findByIsbn(isbn)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new BookNotFoundException("Book is not found by isbn" + isbn));
    }
}
