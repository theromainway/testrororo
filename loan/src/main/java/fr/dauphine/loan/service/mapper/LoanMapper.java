package fr.dauphine.loan.service.mapper;


import fr.dauphine.data.domain.Loan;
import fr.dauphine.loan.service.dto.LoanDto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;




@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface LoanMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "isbn", target = "isbn")
    @Mapping(source = "loanDate", target = "loanDate")
    @Mapping(source = "returnDate", target = "returnDate")
    @Mapping(source = "readerId", target = "readerId")
    LoanDto toDto(Loan loan);

    @InheritInverseConfiguration
    Loan toEntity(LoanDto loanDto);
}