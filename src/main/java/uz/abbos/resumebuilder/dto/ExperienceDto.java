package uz.abbos.resumebuilder.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import uz.abbos.resumebuilder.model.Company;


import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExperienceDto {

    private Long id;

    private CompanyDto companyDto;

    @NotNull(message = ("companyId must not be null"))
    private Long companyId;

    private ResumeDto resumeDto;

    @NotNull(message = ("resumeId must not be null"))
    private Long resumeId;

    @Null
    private Long experienceYear;

    @Null
    private Long experienceMonth;

    @NotNull(message = ("startDate must not be null"))
    private LocalDate startDate;

    @NotNull(message = ("endDate must not be null"))
    private LocalDate endDate;
}
