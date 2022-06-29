package uz.abbos.resumebuilder.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;


@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EducationMappingDto {

    private Long id;

    private EducationDto educationDto;

    @NotNull(message = ("educationId must not be null"))
    private Long educationId;

    private ResumeDto resumeDto;

    @NotNull(message = ("resumeId must not be null"))
    private Long resumeId;

    @NotNull(message = ("startDate must not be null"))
    private LocalDate startDate;

    @NotNull(message = ("endDate must not be null"))
    private LocalDate endDate;

}
