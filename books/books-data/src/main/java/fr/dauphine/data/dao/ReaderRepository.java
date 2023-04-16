package fr.dauphine.data.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.dauphine.data.domain.Reader;

public interface ReaderRepository  extends JpaRepository<Reader, Long> {

}
