package by.laniakea.model.users;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "role", schema = "worldoflibrary")
@Data
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Role {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Version
    @Column(name = "version", nullable = false)
    private Long version;

    @Column(name = "name", nullable = false, unique = true)
    private String roleName;

    public Role(String roleName) {
        this.roleName = roleName;
    }
}
