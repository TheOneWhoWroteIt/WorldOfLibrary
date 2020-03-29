package by.laniakea.model.list;

import by.laniakea.model.books.Book;
import by.laniakea.model.users.Reader;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "request_from_reader_list", schema = "worldoflibrary")
@Data
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString(of = {"id", "books"})
public class RequestFromReader {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Version
    @Column(name = "version", nullable = false)
    private Long version;

    @Column(name = "start_request", nullable = false)
    private LocalDate startRequest;

    @Column(name = "valid_date_request", nullable = false)
    private LocalDate validUntilDateRequest;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "request_books", schema = "worldoflibrary",
            joinColumns = {@JoinColumn(name = "request_id")},
            inverseJoinColumns = {@JoinColumn(name = "book_id")}
    )
    private Set<Book> books;

    @OneToOne
    @JoinColumn(name = "reader_id")
    private Reader reader;

    @OneToOne(mappedBy = "requestFromReader", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private OrderReader order;

    public RequestFromReader(LocalDate startRequest, LocalDate validUntilDateRequest, Set<Book> books, Reader reader) {
        this.startRequest = startRequest;
        this.validUntilDateRequest = validUntilDateRequest;
        this.books = books;
        this.reader = reader;
    }
}
