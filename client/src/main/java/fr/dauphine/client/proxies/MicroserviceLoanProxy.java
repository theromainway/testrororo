package fr.dauphine.client.proxies;

import fr.dauphine.client.beans.LoanBeans;
import fr.dauphine.client.beans.ReaderBeans;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@FeignClient(name = "loans", dismiss404 = true
       // , url = "host.docker.internal:9191"
)
public interface MicroserviceLoanProxy {

    @GetMapping(value="/loans/loans")
    List<LoanBeans> getAllLoans();

    @GetMapping(value = "/loans/loans/{id}")
    LoanBeans getLoanById(@PathVariable Long id);

    @PostMapping(value ="/loans/loans")
    LoanBeans createLoan(@RequestBody LoanBeans loanBeans);

    @PutMapping(value ="/loans/loans")
    LoanBeans updateLoan(@RequestBody LoanBeans loanBeans);

    @DeleteMapping("/loans/loans/{id}")
    void deleteLoanById(@PathVariable Long id);

}