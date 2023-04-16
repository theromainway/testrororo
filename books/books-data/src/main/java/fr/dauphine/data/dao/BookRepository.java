package fr.dauphine.data.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.dauphine.data.domain.Book;

public interface BookRepository extends JpaRepository<Book, String> {

}
