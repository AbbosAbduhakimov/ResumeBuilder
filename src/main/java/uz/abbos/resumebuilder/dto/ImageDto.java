package uz.abbos.resumebuilder.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ImageDto {
    private Long imageId;

    private String fileName;

    private String fileType;

    private byte[] data;

}
