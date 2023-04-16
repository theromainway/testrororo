package fr.dauphine.client.rest;

import fr.dauphine.client.beans.LoanBeans;
import fr.dauphine.client.beans.ReaderBeans;
import fr.dauphine.client.exception.BookNotFoundException;
import fr.dauphine.client.proxies.MicroserviceLoanProxy;
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
public class LoanResource {

    private final MicroserviceLoanProxy loanProxy;

    public LoanResource(MicroserviceLoanProxy loanProxy) {
        this.loanProxy = loanProxy;
    }

    @PostMapping("/loans")
    public ResponseEntity<LoanBeans> createLoan(@RequestBody LoanBeans loanBeans) throws URISyntaxException {
        LoanBeans result = loanProxy.createLoan(loanBeans);
        return ResponseEntity.created(new URI("/api/loans/" + loanBeans.getId()))
                .body(result);
    }

    @GetMapping("/loans")
    public ResponseEntity<List<LoanBeans>> getAllLoans() {
        log.debug("REST request to get all loans");
        final List<LoanBeans> readersDtos = loanProxy.getAllLoans();
        return ResponseEntity.ok().body(readersDtos);
    }

    @PutMapping("/loans")
    public ResponseEntity<LoanBeans> updateBook(@RequestBody LoanBeans loanBeans) {
        log.debug("REST request to create reader: {}", loanBeans);
        final LoanBeans result = loanProxy.updateLoan(loanBeans);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/loans/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        log.debug("REST request to delete loan by id: {}", id);
        loanProxy.deleteLoanById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/loans/{id}")
    public ResponseEntity<LoanBeans> findById(@PathVariable Long id) {
        log.debug("REST request to get loan by id: {}", id);
        LoanBeans loanBeans = loanProxy.getLoanById(id);
        if (loanBeans == null) {
            throw new BookNotFoundException("Reader is not found by id" + id);
        }
        return ResponseEntity.ok().body(loanBeans);
    }
}
