package fr.dauphine.reader.rest;

import fr.dauphine.data.domain.Reader;
import fr.dauphine.reader.exception.ReaderBadRequestException;
import fr.dauphine.reader.exception.ReaderNotFoundException;
import fr.dauphine.reader.service.ReaderService;
import fr.dauphine.reader.service.dto.ReaderDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;


@Slf4j
@RestController
@RequestMapping(path = "/readers")
public class ReaderResource {

    private final ReaderService readerService;

    public ReaderResource(ReaderService readerService) {
        this.readerService = readerService;
    }

    @GetMapping("/readers")
    public List<ReaderDto> getAllReaders() {
        log.debug("Request to get all readers");
       return readerService.findAll();
    }

    @PostMapping("/readers")
    public ResponseEntity<ReaderDto> createReader(@RequestBody ReaderDto readerDto) throws URISyntaxException {
        log.debug("Request to create reader: {}", readerDto);
        if (readerDto.getId() != null) {
            throw new ReaderBadRequestException("Id is forbiden");
        }
        final ReaderDto result = readerService.save(readerDto);
        return ResponseEntity.created(new URI("/readers/" + readerDto.getId()))
                .body(result);
    }

    @PutMapping("/readers")
    public ResponseEntity<ReaderDto> update(@RequestBody ReaderDto readerDto) {
        log.debug("Request to update reader: {}", readerDto);
        if (readerDto.getId() == null) {
            throw new ReaderBadRequestException("Id is mandatory");
        }
        final ReaderDto result = readerService.update(readerDto);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/readers/{id}")
    public void deleteById(@PathVariable Long id) {
        log.debug("Request to delete reader by id: {}", id);
        readerService.deleteById(id);
    }

    @GetMapping("/readers/{id}")
    public ResponseEntity<ReaderDto> findById(@PathVariable Long id) {
        log.debug("Request to find reader by id: {}", id);
        return readerService.findById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ReaderNotFoundException("Reader is not found by id : " + id));
    }
}
