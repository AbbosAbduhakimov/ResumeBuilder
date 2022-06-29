package uz.abbos.resumebuilder.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EducationDto {
    private Long id;

    @NotNull(message = ("educationName must not be null"))
    private String educationName;

    @NotNull(message = ("startDate must not be null"))
    private LocalDate startDate;

    @NotNull(message = ("endDate must not be null"))
    private LocalDate endDate;
}
