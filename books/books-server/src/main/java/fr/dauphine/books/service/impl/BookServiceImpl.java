package fr.dauphine.books.service.impl;



import fr.dauphine.books.service.dto.BookDto;
import fr.dauphine.books.service.mapper.BookMapper;
import fr.dauphine.data.dao.BookRepository;
import fr.dauphine.data.domain.Book;
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
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    private final BookMapper bookMapper;

    public BookServiceImpl(BookRepository bookRepository, BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
    }


    @Override
    @Transactional(readOnly = true)
    public List<BookDto> findAll() {
        return bookRepository.findAll().stream()
                .map(bookMapper::toDto)
                .collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    public BookDto save(BookDto bookDto) {
        Book book = bookMapper.toEntity(bookDto);
        book = bookRepository.save(book);
        return bookMapper.toDto(book);
    }

    @Override
    public BookDto update(BookDto bookDto) {
        Book book = bookMapper.toEntity(bookDto);
        book = bookRepository.saveAndFlush(book);
        return bookMapper.toDto(book);
    }

    @Override
    public void deleteById(String isbn) {
        bookRepository.deleteById(isbn);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<BookDto> findByIsbn(String isbn) {
        return bookRepository.findById(isbn).map(bookMapper::toDto);
    }
}
