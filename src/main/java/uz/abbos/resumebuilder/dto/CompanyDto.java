package uz.abbos.resumebuilder.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;


@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CompanyDto {
    private Long id;

    @NotNull(message = ("companyName must not be null"))
    private String companyName;

    @NotNull(message = ("companyAddress must not be null"))
    private String companyAddress;

    @NotNull(message = ("email must not be null"))
    private String email;

    @NotNull(message = ("jobPosition must not be null"))
    private String jobPosition;

    @NotNull(message = ("jobTitle must not be null"))
    private String jobTitle;
}
