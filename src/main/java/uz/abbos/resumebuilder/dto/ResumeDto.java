package uz.abbos.resumebuilder.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResumeDto {

    private Long id;

    @NotNull(message = ("userId must not be null"))
    private Long userId;

    @NotNull(message = ("imageId must not be null"))
    private Long image;

    private Set<EducationMappingDto> educations;

    private Set<ExperienceDto> experiences;

    @Null
    private LocalDateTime createdAt;

    @Null
    private LocalDateTime updatedAt;

    @Null
    private LocalDateTime deletedAt;
}
