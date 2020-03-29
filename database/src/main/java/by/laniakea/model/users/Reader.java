package by.laniakea.model.users;


import by.laniakea.model.list.RequestFromReader;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Entity

@Table(name = "reader", schema = "worldoflibrary")
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Data
@NoArgsConstructor
@EqualsAndHashCode(of = "ticketNumber", callSuper = false)
@ToString(of = {"ticketNumber", "blackList"}, callSuper = true)
public class Reader extends User {

    @Column(name = "black_list", nullable = false)
    private boolean blackList = false;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long ticketNumber;

    @Version
    @Column(name = "version", nullable = false)
    private Long version;

    @OneToOne(mappedBy = "reader", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private RequestFromReader requestFromReader;

    public Reader(@NotBlank String firstName, @NotBlank String lastName, LocalDate birthday, @Email String email,
                  @NotBlank String phoneNumber, @NotBlank String login, @NotBlank String password, Address address) {
        super(firstName, lastName, birthday, email, phoneNumber, login, password, address);
    }


}
