package fr.dauphine.loan;


import fr.dauphine.data.dao.LoanRepository;
import fr.dauphine.data.domain.Loan;

import fr.dauphine.loan.service.dto.LoanDto;
import fr.dauphine.loan.service.impl.LoanServiceImpl;
import fr.dauphine.loan.service.mapper.LoanMapper;

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
class LoanServiceTest {

    @Mock
    private LoanRepository loanRepository;

    @Mock
    private LoanMapper loanMapper;

    @InjectMocks
    private LoanServiceImpl loanService;

    private Loan loan;

    private LoanDto loanDto;


    @BeforeEach
    void setUp() {
        loan = new Loan();
        loan.setId(1L);

        loanDto = new LoanDto();
        loanDto.setId(1L);
    }

    @Test
    void should_find_all() {
        // Given
        final List<LoanDto> loanDtos = Collections.singletonList(loanDto);

        // When
        when(loanRepository.findAll()).thenReturn(Collections.singletonList(loan));
        when(loanMapper.toDto(any(Loan.class))).thenReturn(loanDto);

        // Then
        assertThat(loanService.findAll()).containsExactlyInAnyOrder(loanDto);
        verify(loanRepository, times(1)).findAll();
        verify(loanMapper, times(1)).toDto(any(Loan.class));
        verifyNoMoreInteractions(loanRepository);
        verifyNoMoreInteractions(loanMapper);
    }

    @Test
    void should_empty_list_when_trying_to_find_all() {
        // Given
        final List<Loan> loans = Collections.emptyList();

        // When
        when(loanRepository.findAll()).thenReturn(loans);

        // Then
        assertThat(loanService.findAll()).isEmpty();
        verify(loanRepository, times(1)).findAll();
        verifyNoMoreInteractions(loanRepository);
        verifyNoInteractions(loanMapper);
    }

    @Test
    void should_save_loan() {
        // Given
        Loan loanToSave = loan;

        // When
        when(loanRepository.save(any(Loan.class))).thenReturn(loanToSave);
        when(loanMapper.toEntity(any(LoanDto.class))).thenReturn(loanToSave);
        when(loanMapper.toDto(any(Loan.class))).thenReturn(loanDto);

        // Then
        assertThat(loanService.save(loanDto)).isEqualTo(loanDto);
        verify(loanRepository, times(1)).save(any(Loan.class));
        verify(loanMapper, times(1)).toDto(any(Loan.class));
        verifyNoMoreInteractions(loanRepository);
        verifyNoMoreInteractions(loanMapper);
    }

    @Test
    void should_update_loan() {
        // Given
        Loan loanToUpdate = loan;

        // When
        when(loanRepository.saveAndFlush(any(Loan.class))).thenReturn(loanToUpdate);
        when(loanMapper.toEntity(any(LoanDto.class))).thenReturn(loanToUpdate);
        when(loanMapper.toDto(any(Loan.class))).thenReturn(loanDto);

        assertThat(loanService.update(loanDto)).isEqualTo(loanDto);
        verify(loanRepository, times(1)).saveAndFlush(any(Loan.class));
        verify(loanMapper, times(1)).toDto(any(Loan.class));
        verifyNoMoreInteractions(loanRepository);
        verifyNoMoreInteractions(loanMapper);
    }

    @Test
    void should_delete_loan() {
        // Given
        Long id = 1L;

        // When
        doNothing().when(loanRepository).deleteById(eq(id));

        // Then
        loanRepository.deleteById(id);
        verify(loanRepository, times(1)).deleteById(eq(id));
        verifyNoMoreInteractions(loanRepository);
        verifyNoInteractions(loanMapper);
    }

    @Test
    void should_find_loan_by_id() {
        // Given
        Long id = 1L;

        // When
        when(loanRepository.findById(eq(id))).thenReturn(Optional.of(loan));
        when(loanMapper.toDto(any(Loan.class))).thenReturn(loanDto);

        // Then
        assertThat(loanService.findById(id)).hasValue(loanDto);
        verify(loanRepository, times(1)).findById(eq(id));
        verify(loanMapper, times(1)).toDto(any(Loan.class));
        verifyNoMoreInteractions(loanRepository);
        verifyNoMoreInteractions(loanMapper);
    }

}
