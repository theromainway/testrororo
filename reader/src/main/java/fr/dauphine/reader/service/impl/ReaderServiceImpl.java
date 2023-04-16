package fr.dauphine.reader.service.impl;





import fr.dauphine.data.dao.ReaderRepository;
import fr.dauphine.data.domain.Reader;
import fr.dauphine.reader.service.ReaderService;
import fr.dauphine.reader.service.dto.ReaderDto;
import fr.dauphine.reader.service.mapper.ReaderMapper;
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
public class ReaderServiceImpl implements ReaderService {

    private final ReaderRepository readerRepository;

    private final ReaderMapper readerMapper;

    public ReaderServiceImpl(ReaderRepository readerRepository, ReaderMapper readerMapper) {
        this.readerRepository = readerRepository;
        this.readerMapper = readerMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ReaderDto> findAll() {
        log.debug("Request to find readers");
        return readerRepository.findAll().stream()
                .map(readerMapper::toDto)
                .collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    public ReaderDto save(ReaderDto readerDto) {
        log.debug("Request to save reader: {}", readerDto);
        Reader reader = readerMapper.toEntity(readerDto);
        reader = readerRepository.save(reader);
        return readerMapper.toDto(reader);
    }

    @Override
    public ReaderDto update(ReaderDto readerDto) {
        log.debug("Request to update reader: {}", readerDto);
        Reader reader = readerMapper.toEntity(readerDto);
        reader = readerRepository.saveAndFlush(reader);
        return readerMapper.toDto(reader);
    }

    @Override
    public void deleteById(Long id) {
        log.debug("Request to delete reader by id: {}", id);
        readerRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ReaderDto> findById(Long id) {
        log.debug("Request to find reader by id: {}", id);
        return readerRepository.findById(id).map(readerMapper::toDto);
    }
}
