package fr.dauphine.client.rest;

import fr.dauphine.client.beans.BookBeans;
import fr.dauphine.client.beans.ReaderBeans;
import fr.dauphine.client.exception.BookNotFoundException;
import fr.dauphine.client.proxies.MicroserviceBookProxy;
import fr.dauphine.client.proxies.MicroserviceReaderProxy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(path = "/api")
public class ReaderResource {

    private final MicroserviceReaderProxy readerProxy;

    public ReaderResource(MicroserviceReaderProxy readerProxy) {
        this.readerProxy = readerProxy;
    }

    @PostMapping("/readers")
    public ResponseEntity<ReaderBeans> createReader(@RequestBody ReaderBeans readerBeans) throws URISyntaxException {
        ReaderBeans result = readerProxy.createReader(readerBeans);
        return ResponseEntity.created(new URI("/api/readers/" + readerBeans.getId()))
                .body(result);
    }

    @GetMapping("/readers")
    public ResponseEntity<List<ReaderBeans>> getAllReaders() {
        log.debug("REST request to get all readers");
        final List<ReaderBeans> readersDtos = readerProxy.getAllReaders();
        return ResponseEntity.ok().body(readersDtos);
    }

    @PutMapping("/readers")
    public ResponseEntity<ReaderBeans> updateBook(@RequestBody ReaderBeans readerBeans) {
        log.debug("REST request to create reader: {}", readerBeans);
        final ReaderBeans result = readerProxy.updateReader(readerBeans);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/readers/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        log.debug("REST request to delete book by isbn: {}", id);
        readerProxy.deleteReaderById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/readers/{id}")
    public ResponseEntity<ReaderBeans> findById(@PathVariable Long id) {
        log.debug("REST request to get reader by id: {}", id);
        ReaderBeans readerBeans = readerProxy.getReaderById(id);
        if (readerBeans == null) {
            throw new BookNotFoundException("Reader is not found by id" + id);
        }
        return ResponseEntity.ok().body(readerBeans);
    }
}
