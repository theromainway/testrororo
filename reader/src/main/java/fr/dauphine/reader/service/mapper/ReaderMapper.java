package fr.dauphine.reader.service.mapper;

import fr.dauphine.data.domain.Reader;
import fr.dauphine.reader.service.dto.ReaderDto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;




@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ReaderMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "gender", target = "gender")
    @Mapping(source = "lastName", target = "lastName")
    @Mapping(source = "firstName", target = "firstName")
    @Mapping(source = "dateOfBirth", target = "dateOfBirth")
    @Mapping(source = "address", target = "address")
    ReaderDto toDto(Reader reader);

    @InheritInverseConfiguration
    Reader toEntity(ReaderDto readerDto);
}