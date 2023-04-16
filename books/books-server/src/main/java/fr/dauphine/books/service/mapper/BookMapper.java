package fr.dauphine.books.service.mapper;

import fr.dauphine.books.service.dto.BookDto;
import fr.dauphine.data.domain.Book;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;


@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BookMapper {

    @Mapping(source = "isbn", target = "isbn")
    @Mapping(source = "author", target = "author")
    @Mapping(source = "title", target = "title")
    @Mapping(source = "editor", target = "editor")
    @Mapping(source = "editing", target = "editing")
    BookDto toDto(Book book);

    @InheritInverseConfiguration
    Book toEntity(BookDto bookDto);
}