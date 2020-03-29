package by.laniakea.model.books;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "author", schema = "worldoflibrary")
@Data
@NoArgsConstructor
@ToString(of = {"id", "firstName", "lastName"})
@EqualsAndHashCode(of = "id")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Version
    @Column(name = "version", nullable = false)
    private Long version;

    @Column(name = "first_name", nullable = false, length = 150)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 150)
    private String lastName;


    @ManyToMany(mappedBy = "authors", fetch = FetchType.LAZY)
    private List<Book> books;

    @Column(name = "author_description", nullable = false)
    private String authorDescription;

    public Author(String firstName, String lastName, String authorDescription) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.authorDescription = authorDescription;
    }
}
