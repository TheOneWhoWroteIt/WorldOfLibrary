package by.laniakea.model.users;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Entity
@Table(name = "employee", schema = "worldoflibrary")
@Data
@NoArgsConstructor
@EqualsAndHashCode(of = "personnelNumber", callSuper = false)
@ToString(of = {"personnelNumber", "position"}, callSuper = true)
public class Employee extends User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long personnelNumber;

    @Version
    @Column(name = "version", nullable = false)
    private Long version;

    @Column(name = "position", nullable = false, length = 200)
    private String position;

    @Column(name = "start_work", nullable = false)
    private LocalDate startWork;

    @Column(name = "end_work")
    private LocalDate endWork;

    @Column(name = "photo_path", nullable = false)
    private String photoPath;

    public Employee(@NotBlank String firstName, @NotBlank String lastName, LocalDate birthday,
                    @Email String email, @NotBlank String phoneNumber, Role role,
                    @NotBlank String login, @NotBlank String password, Address address,
                    String position, LocalDate startWork, String photoPath) {
        super(firstName, lastName, birthday, email, phoneNumber, role, login, password, address);
        this.position = position;
        this.startWork = startWork;
        this.photoPath = photoPath;
    }
}
