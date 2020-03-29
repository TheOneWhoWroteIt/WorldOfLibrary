package by.laniakea.model.books;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

import javax.persistence.MappedSuperclass;



@NoArgsConstructor
@Data
@MappedSuperclass
public abstract class PrintEdition {

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "published_name", nullable = false)
    private String publishedName;

    @Column(name = "year", nullable = false)
    private Integer yearOfPublishing;

    @Column(name = "print_edition_count", nullable = false)
    private Integer printEditionCount;

    public PrintEdition(String title, String publishedName, Integer yearOfPublishing, Integer printEditionCount) {
        this.title = title;
        this.publishedName = publishedName;
        this.yearOfPublishing = yearOfPublishing;
        this.printEditionCount = printEditionCount;
    }
}
