package by.laniakea.model.users;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Data
@ToString(exclude = {"login", "password"})
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public abstract class User {

    @NotBlank
    @Column(name = "first_name", nullable = false, length = 150)
    private String firstName;

    @NotBlank
    @Column(name = "last_name", nullable = false, length = 150)
    private String lastName;


    /*@Past*/
    @Column(name = "birthday", nullable = false)
    private LocalDate birthday;

    @Email
    @Column(name = "email", nullable = false, length = 150, unique = true)
    private String email;

    @NotBlank
    @Column(name = "phone_number", nullable = false, length = 100, unique = true)
    private String phoneNumber;

    @OneToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @NotBlank
    @Column(name = "login", nullable = false, unique = true, length = 200)
    private String login;

    @NotBlank
    @Column(name = "password", nullable = false)
    private String password;


    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "city", column = @Column(name = "city", nullable = false, length = 150)),
            @AttributeOverride(name = "street", column = @Column(name = "street", nullable = false, length = 150)),
            /*@AttributeOverride(name = "zipCode", column = @Column(name = "zip_code", nullable = false, length = 150)),*/
            @AttributeOverride(name = "homeNumber", column = @Column(name = "home_number", nullable = false, length = 150)),
            @AttributeOverride(name = "roomNumber", column = @Column(name = "room_number", nullable = false, length = 150))

    })
    private Address address;

    public User(@NotBlank String firstName, @NotBlank String lastName, LocalDate birthday, @Email String email,
                @NotBlank String phoneNumber, @NotBlank String login,
                @NotBlank String password, Address address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.login = login;
        this.password = password;
        this.address = address;
    }
}
