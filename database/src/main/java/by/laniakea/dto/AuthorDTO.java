package by.laniakea.dto;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;


@Data
@NoArgsConstructor
public class AuthorDTO {


    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    private String authorDescription;

    public AuthorDTO(@NotBlank String firstName, @NotBlank String lastName, @NotBlank String authorDescription) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.authorDescription = authorDescription;
    }
}
