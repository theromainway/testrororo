package fr.dauphine.data.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.dauphine.data.domain.Loan;

public interface LoanRepository extends JpaRepository<Loan, Long> {	

}
