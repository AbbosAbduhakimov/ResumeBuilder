package uz.abbos.resumebuilder.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto {

    private Long id;

    @NotNull(message = ("firstname must not be null"))
    private String firstname;

    @NotNull(message = ("lastname must not be null"))
    private String lastname;

    @NotNull(message = ("age must not be null"))
    private Integer age;

    @NotNull(message = ("contact must not be null"))
    private String contact;

    @NotNull(message = ("address must not be null"))
    private String address;

    @NotNull(message = ("email must not be null"))
    @Email
    private String email;

    @NotNull
    @Length(max = 100,message = ("this place must be more than 100 characters"))
    private String aboutMe;



}
