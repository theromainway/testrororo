package fr.dauphine.reader;


import fr.dauphine.data.dao.ReaderRepository;
import fr.dauphine.data.domain.Book;
import fr.dauphine.data.domain.Reader;
import fr.dauphine.reader.service.dto.ReaderDto;
import fr.dauphine.reader.service.impl.ReaderServiceImpl;
import fr.dauphine.reader.service.mapper.ReaderMapper;
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
class ReaderServiceTest {

    @Mock
    private ReaderRepository readerRepository;

    @Mock
    private ReaderMapper readerMapper;

    @InjectMocks
    private ReaderServiceImpl readerService;

    private Reader reader;

    private ReaderDto readerDto;


    @BeforeEach
    void setUp() {
        reader = new Reader();
        reader.setId(1L);
        reader.setGender("test");

        readerDto = new ReaderDto();
        readerDto.setGender("test");
        readerDto.setId(1L);
    }

    @Test
    void should_find_all() {
        // Given
        final List<ReaderDto> readerDtos = Collections.singletonList(readerDto);

        // When
        when(readerRepository.findAll()).thenReturn(Collections.singletonList(reader));
        when(readerMapper.toDto(any(Reader.class))).thenReturn(readerDto);

        // Then
        assertThat(readerService.findAll()).containsExactlyInAnyOrder(readerDto);
        verify(readerRepository, times(1)).findAll();
        verify(readerMapper, times(1)).toDto(any(Reader.class));
        verifyNoMoreInteractions(readerRepository);
        verifyNoMoreInteractions(readerMapper);
    }

    @Test
    void should_empty_list_when_trying_to_find_all() {
        // Given
        final List<Reader> readers = Collections.emptyList();

        // When
        when(readerRepository.findAll()).thenReturn(readers);

        // Then
        assertThat(readerService.findAll()).isEmpty();
        verify(readerRepository, times(1)).findAll();
        verifyNoMoreInteractions(readerRepository);
        verifyNoInteractions(readerMapper);
    }

    @Test
    void should_save_reader() {
        // Given
        Reader readerToSave = reader;

        // When
        when(readerRepository.save(any(Reader.class))).thenReturn(readerToSave);
        when(readerMapper.toEntity(any(ReaderDto.class))).thenReturn(readerToSave);
        when(readerMapper.toDto(any(Reader.class))).thenReturn(readerDto);

        // Then
        assertThat(readerService.save(readerDto)).isEqualTo(readerDto);
        verify(readerRepository, times(1)).save(any(Reader.class));
        verify(readerMapper, times(1)).toDto(any(Reader.class));
        verifyNoMoreInteractions(readerRepository);
        verifyNoMoreInteractions(readerMapper);
    }

    @Test
    void should_update_book() {
        // Given
        Reader readerToUpdate = reader;

        // When
        when(readerRepository.saveAndFlush(any(Reader.class))).thenReturn(readerToUpdate);
        when(readerMapper.toEntity(any(ReaderDto.class))).thenReturn(readerToUpdate);
        when(readerMapper.toDto(any(Reader.class))).thenReturn(readerDto);

        assertThat(readerService.update(readerDto)).isEqualTo(readerDto);
        verify(readerRepository, times(1)).saveAndFlush(any(Reader.class));
        verify(readerMapper, times(1)).toDto(any(Reader.class));
        verifyNoMoreInteractions(readerRepository);
        verifyNoMoreInteractions(readerMapper);
    }

    @Test
    void should_delete_reader() {
        // Given
        Long id = 1L;

        // When
        doNothing().when(readerRepository).deleteById(eq(id));

        // Then
        readerRepository.deleteById(id);
        verify(readerRepository, times(1)).deleteById(eq(id));
        verifyNoMoreInteractions(readerRepository);
        verifyNoInteractions(readerMapper);
    }

    @Test
    void should_find_book_by_isbn() {
        // Given
        Long id = 1L;

        // When
        when(readerRepository.findById(eq(id))).thenReturn(Optional.of(reader));
        when(readerMapper.toDto(any(Reader.class))).thenReturn(readerDto);

        // Then
        assertThat(readerService.findById(id)).hasValue(readerDto);
        verify(readerRepository, times(1)).findById(eq(id));
        verify(readerMapper, times(1)).toDto(any(Reader.class));
        verifyNoMoreInteractions(readerRepository);
        verifyNoMoreInteractions(readerMapper);
    }

}
