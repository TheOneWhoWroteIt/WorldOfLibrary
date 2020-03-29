package by.laniakea.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;


@Data
@NoArgsConstructor
public class EmployeeDTO {

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
    private String city;

    @NotBlank
    private String street;

    @NotBlank
    private String homeNumber;

    @NotBlank
    private String roomNumber;

    @NotBlank
    @Length(min = 3, max = 20)
    private String login;

    @NotBlank
    private String password;

    @NotBlank
    private String position;

    @NotBlank
    private String startWork;

    @NotBlank
    private String endWork;

    @NotBlank
    private String photoPath;

    @NotBlank
    private String role;

    public EmployeeDTO(@NotBlank String firstName, @NotBlank String lastName, @NotBlank String birthday,
                       @Email String email, @NotBlank String phoneNumber, @NotBlank String city,
                       @NotBlank String street, @NotBlank String homeNumber, @NotBlank String roomNumber,
                       @NotBlank @Length(min = 3, max = 20) String login, @NotBlank String password,
                       @NotBlank String position, @NotBlank String startWork, @NotBlank String endWork,
                       @NotBlank String photoPath, @NotBlank String role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.city = city;
        this.street = street;
        this.homeNumber = homeNumber;
        this.roomNumber = roomNumber;
        this.login = login;
        this.password = password;
        this.position = position;
        this.startWork = startWork;
        this.endWork = endWork;
        this.photoPath = photoPath;
        this.role = role;
    }

    public EmployeeDTO(@NotBlank String firstName, @NotBlank String lastName, @NotBlank String birthday,
                       @Email String email, @NotBlank String phoneNumber, @NotBlank String city,
                       @NotBlank String street, @NotBlank String homeNumber, @NotBlank String roomNumber,
                       @NotBlank @Length(min = 3, max = 20) String login, @NotBlank String password,
                       @NotBlank String position, @NotBlank String startWork, @NotBlank String photoPath,
                       @NotBlank String role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.city = city;
        this.street = street;
        this.homeNumber = homeNumber;
        this.roomNumber = roomNumber;
        this.login = login;
        this.password = password;
        this.position = position;
        this.startWork = startWork;
        this.photoPath = photoPath;
        this.role = role;
    }
}
