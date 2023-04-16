package fr.dauphine.client.rest;

import fr.dauphine.client.beans.BookBeans;
import fr.dauphine.client.exception.BookNotFoundException;
import fr.dauphine.client.proxies.MicroserviceBookProxy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;


import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(path = "/api")
public class BookResource {

    private final MicroserviceBookProxy bookProxy;

    public BookResource(MicroserviceBookProxy bookProxy) {
        this.bookProxy = bookProxy;
    }

    @PostMapping("/books")
    public ResponseEntity<BookBeans> createBook(@RequestBody BookBeans bookBeans) throws URISyntaxException {
        BookBeans result = bookProxy.createBook(bookBeans);
        return ResponseEntity.created(new URI("/api/books/" + bookBeans.getIsbn()))
                .body(result);
    }

    @GetMapping("/books")
    public ResponseEntity<List<BookBeans>> getAllBooks() {
        log.debug("REST request to get all books");
        final List<BookBeans> bookDtos = bookProxy.getAllBooks();
        return ResponseEntity.ok().body(bookDtos);
    }

    @PutMapping("/books")
    public ResponseEntity<BookBeans> updateBook(@RequestBody BookBeans bookBeans) {
        log.debug("REST request to create book: {}", bookBeans);
        final BookBeans result = bookProxy.updateBook(bookBeans);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/books/{isbn}")
    public ResponseEntity<Void> deleteById(@PathVariable String isbn) {
        log.debug("REST request to delete book by isbn: {}", isbn);
        bookProxy.deleteBookByIsbn(isbn);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/books/{isbn}")
    public ResponseEntity<BookBeans> findByIsbn(@PathVariable String isbn) {
        log.debug("REST request to get book by isbn: {}", isbn);
        BookBeans bookBeans = bookProxy.getBookByIsbn(isbn);
        if (bookBeans == null) {
            throw new BookNotFoundException("Book is not found by isbn" + isbn);
        }
        return ResponseEntity.ok().body(bookBeans);
    }
}
