package fr.dauphine.loan.service.impl;






import fr.dauphine.data.dao.LoanRepository;
import fr.dauphine.data.domain.Loan;
import fr.dauphine.loan.service.LoanService;
import fr.dauphine.loan.service.dto.LoanDto;
import fr.dauphine.loan.service.mapper.LoanMapper;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
public class LoanServiceImpl implements LoanService {

    private final LoanRepository loanRepository;

    private final LoanMapper loanMapper;

    public LoanServiceImpl(LoanRepository loanRepository, LoanMapper loanMapper) {
        this.loanRepository = loanRepository;
        this.loanMapper = loanMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<LoanDto> findAll() {
        log.debug("Request to find All loans");
        return loanRepository.findAll().stream()
                .map(loanMapper::toDto)
                .collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    public LoanDto save(LoanDto loanDto) {
        log.debug("Request to save loan: {}", loanDto);
        Loan loan = loanMapper.toEntity(loanDto);
        loan = loanRepository.save(loan);
        return loanMapper.toDto(loan);
    }

    @Override
    public LoanDto update(LoanDto loanDto) {
        log.debug("Request to update loan: {}", loanDto);
        Loan loan = loanMapper.toEntity(loanDto);
        loan = loanRepository.saveAndFlush(loan);
        return loanMapper.toDto(loan);
    }

    @Override
    public void deleteById(Long id) {
        log.debug("Request to delete loan by id: {}", id);
        loanRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<LoanDto> findById(Long id) {
        log.debug("Request to find loan by id: {}", id);
        return loanRepository.findById(id).map(loanMapper::toDto);
    }
}
