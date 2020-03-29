package by.laniakea.dto;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.UniqueElements;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;


@Data
@NoArgsConstructor
public class ReaderDTO {

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    private String birthday;

    @Email
    private String email;

    @NotBlank
    private String phoneNumber;

    @NotBlank
    @Length(min = 3, max = 20)
    private String login;

    @NotBlank
    private String password;

    @NotBlank
    private String repitPassword;

    @NotBlank
    private String city;

    @NotBlank
    private String street;

    @NotBlank
    private String homeNumber;

    @NotBlank
    private String roomNumber;

    private boolean agree;

    public ReaderDTO(@NotBlank String firstName, @NotBlank String lastName,
                     @NotBlank String birthday, @Email String email, @NotBlank String phoneNumber,
                     @NotBlank @Length(min = 3, max = 20) String login, @NotBlank String password,
                     @NotBlank String repitPassword, @NotBlank String city, @NotBlank String street,
                     @NotBlank String homeNumber, @NotBlank String roomNumber, @NotBlank boolean agree) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.login = login;
        this.password = password;
        this.repitPassword = repitPassword;
        this.city = city;
        this.street = street;
        this.homeNumber = homeNumber;
        this.roomNumber = roomNumber;
        this.agree = agree;
    }
}
