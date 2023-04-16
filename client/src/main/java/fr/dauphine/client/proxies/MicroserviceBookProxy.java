package fr.dauphine.client.proxies;

import fr.dauphine.client.beans.BookBeans;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@FeignClient(name = "books", dismiss404 = true
       // , url = "host.docker.internal:9191"
)
public interface MicroserviceBookProxy {

    @GetMapping(value="/books/books")
    List<BookBeans> getAllBooks();

    @GetMapping(value = "/books/books/{isbn}")
    BookBeans getBookByIsbn(@PathVariable String isbn);

    @PostMapping(value ="/books/books")
    BookBeans createBook(@RequestBody BookBeans bookBeans);

    @PutMapping(value ="/books/books")
    BookBeans updateBook(@RequestBody BookBeans bookBeans);

    @DeleteMapping("/books/books/{isbn}")
    void deleteBookByIsbn(@PathVariable String isbn);

}