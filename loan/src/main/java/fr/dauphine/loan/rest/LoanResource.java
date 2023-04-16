package fr.dauphine.loan.rest;

import fr.dauphine.loan.exception.LoanBadRequestException;
import fr.dauphine.loan.exception.LoanNotFoundException;
import fr.dauphine.loan.service.LoanService;
import fr.dauphine.loan.service.dto.LoanDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(path = "/loans")
public class LoanResource {

    private final LoanService loanService;

    public LoanResource(LoanService loanService) {
        this.loanService = loanService;
    }

    @GetMapping("/loans")
    public List<LoanDto> getAllLoans() {
        log.debug("Request to get all loans");
        return loanService.findAll();
    }

    @PostMapping("/loans")
    public ResponseEntity<LoanDto> createLoan(@RequestBody LoanDto loanDto) throws URISyntaxException {
        log.debug("Request to create loan: {}", loanDto);
        if (loanDto.getId() != null) {
            throw new LoanBadRequestException("Id is forbiden");
        }
        final LoanDto result = loanService.save(loanDto);
        return ResponseEntity.created(new URI("/loans/" + loanDto.getId()))
                .body(result);
    }

    @PutMapping("/loans")
    public ResponseEntity<LoanDto> update(@RequestBody LoanDto loanDto) {
        log.debug("Request to update loan: {}", loanDto);
        if (loanDto.getId() != null) {
            throw new LoanBadRequestException("Id is mandatory");
        }
        final LoanDto result = loanService.update(loanDto);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/loans/{id}")
    public void deleteById(@PathVariable Long id) {
        log.debug("Request to delete loan by id: {}", id);
        loanService.deleteById(id);
    }

    @GetMapping("/loans/{id}")
    public ResponseEntity<LoanDto> findById(@PathVariable Long id) {
        log.debug("Request to find loan by id: {}", id);
        return loanService.findById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new LoanNotFoundException("Loan is not found by id : " + id));
    }
}
