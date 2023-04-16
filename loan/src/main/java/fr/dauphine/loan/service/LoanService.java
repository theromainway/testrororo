package fr.dauphine.loan.service;







import fr.dauphine.loan.service.dto.LoanDto;

import java.util.List;
import java.util.Optional;

public interface LoanService {

    List<LoanDto> findAll();

    LoanDto save(LoanDto loanDto);

    LoanDto update(LoanDto loanDto);

    void deleteById(Long id);

    Optional<LoanDto> findById(Long id);

}
