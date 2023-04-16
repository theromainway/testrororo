package fr.dauphine.books;

import fr.dauphine.books.service.dto.BookDto;
import fr.dauphine.books.service.impl.BookServiceImpl;
import fr.dauphine.books.service.mapper.BookMapper;
import fr.dauphine.data.dao.BookRepository;
import fr.dauphine.data.domain.Book;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @Mock
    private BookMapper bookMapper;

    @InjectMocks
    private BookServiceImpl bookService;

    private Book book;

    private BookDto bookDto;


    @BeforeEach
    void setUp() {
        book = new Book();
        book.setTitle("Harry Potter");
        book.setAuthor("");
        book.setEditor("");
        book.setIsbn("dde");

        bookDto = new BookDto();
        bookDto.setTitle("Harry Potter");
        bookDto.setAuthor("");
        bookDto.setEditor("");
        bookDto.setIsbn("dde");
    }

    @Test
    void should_find_all() {
        // Given
        final List<BookDto> books = Collections.singletonList(bookDto);

        // When
        when(bookRepository.findAll()).thenReturn(Collections.singletonList(book));
        when(bookMapper.toDto(any(Book.class))).thenReturn(bookDto);

        // Then
        assertThat(bookService.findAll()).containsExactlyInAnyOrder(bookDto);
        verify(bookRepository, times(1)).findAll();
        verify(bookMapper, times(1)).toDto(any(Book.class));
        verifyNoMoreInteractions(bookRepository);
        verifyNoMoreInteractions(bookMapper);
    }

    @Test
    void should_empty_list_when_trying_to_find_all() {
        // Given
        final List<Book> books = Collections.emptyList();

        // When
        when(bookRepository.findAll()).thenReturn(books);

        // Then
        assertThat(bookService.findAll()).isEmpty();
        verify(bookRepository, times(1)).findAll();
        verifyNoMoreInteractions(bookRepository);
        verifyNoInteractions(bookMapper);
    }

    @Test
    void should_save_book() {
        // Given
        Book bookToSave = book;

        // When
        when(bookRepository.save(any(Book.class))).thenReturn(bookToSave);
        when(bookMapper.toEntity(any(BookDto.class))).thenReturn(bookToSave);
        when(bookMapper.toDto(any(Book.class))).thenReturn(bookDto);

        // Then
        assertThat(bookService.save(bookDto)).isEqualTo(bookDto);
        verify(bookRepository, times(1)).save(any(Book.class));
        verify(bookMapper, times(1)).toDto(any(Book.class));
        verifyNoMoreInteractions(bookRepository);
        verifyNoMoreInteractions(bookMapper);
    }

    @Test
    void should_update_book() {
        // Given
        Book bookToUpdate = book;

        // When
        when(bookRepository.saveAndFlush(any(Book.class))).thenReturn(bookToUpdate);
        when(bookMapper.toEntity(any(BookDto.class))).thenReturn(bookToUpdate);
        when(bookMapper.toDto(any(Book.class))).thenReturn(bookDto);

        assertThat(bookService.update(bookDto)).isEqualTo(bookDto);
        verify(bookRepository, times(1)).saveAndFlush(any(Book.class));
        verify(bookMapper, times(1)).toDto(any(Book.class));
        verifyNoMoreInteractions(bookRepository);
        verifyNoMoreInteractions(bookMapper);
    }

    @Test
    void should_delete_book() {
        // Given
        String isbn = "test";

        // When
        doNothing().when(bookRepository).deleteById(eq(isbn));

        // Then
        bookRepository.deleteById(isbn);
        verify(bookRepository, times(1)).deleteById(eq(isbn));
        verifyNoMoreInteractions(bookRepository);
        verifyNoInteractions(bookMapper);
    }

    @Test
    void should_find_book_by_isbn() {
        // Given
        String isbn = "test";

        // When
        when(bookRepository.findById(eq(isbn))).thenReturn(Optional.of(book));
        when(bookMapper.toDto(any(Book.class))).thenReturn(bookDto);

        // Then
        assertThat(bookService.findByIsbn(isbn)).hasValue(bookDto);
        verify(bookRepository, times(1)).findById(eq(isbn));
        verify(bookMapper, times(1)).toDto(any(Book.class));
        verifyNoMoreInteractions(bookRepository);
        verifyNoMoreInteractions(bookMapper);
    }

}
