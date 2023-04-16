package fr.dauphine.reader.service;






import fr.dauphine.data.domain.Reader;
import fr.dauphine.reader.service.dto.ReaderDto;

import java.util.List;
import java.util.Optional;

public interface ReaderService {

    List<ReaderDto> findAll();

    ReaderDto save(ReaderDto readerDto);

    ReaderDto update(ReaderDto readerDto);

    void deleteById(Long id);

    Optional<ReaderDto> findById(Long id);
}
