package by.laniakea.model.books;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "book", schema = "worldoflibrary")
@Data
@NoArgsConstructor
@ToString(callSuper = true, of = {"id", "ISBN"})
@EqualsAndHashCode(of = "id", callSuper = false)
public class Book extends PrintEdition{

    private static final PrintEditionType PRINT_EDITION_TYPE = PrintEditionType.BOOK;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Version
    @Column(name = "version", nullable = false)
    private Long version;

    @Column(name = "isbn", nullable = false, length = 200, unique = true)
    private String ISBN;

    @Column(name = "book_description", nullable = false)
    private String bookDescription;

    @Column(name = "image_path", nullable = false)
    private String imagePath;

    @Column(name = "page_count", nullable = false, length = 20)
    private String pageCount;

    @Column(name = "genre", nullable = false)
    private String genre;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "book_author", schema = "worldoflibrary",
            joinColumns = {@JoinColumn(name = "book_id")},
            inverseJoinColumns = {@JoinColumn(name = "author_id")}
    )
    private List<Author> authors;

    public Book(String ISBN, String bookDescription, String imagePath, String pageCount, List<Author> authors, String genre,
                String title, String published, Integer yearOfPublishing, Integer printEditionCount) {
        super(title, published, yearOfPublishing, printEditionCount);
        this.ISBN = ISBN;
        this.bookDescription = bookDescription;
        this.imagePath = imagePath;
        this.pageCount = pageCount;
        this.authors = authors;
        this.genre = genre;
    }

}
