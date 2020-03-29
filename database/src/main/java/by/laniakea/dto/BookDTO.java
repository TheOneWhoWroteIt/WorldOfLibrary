package by.laniakea.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class BookDTO {

    @NotBlank
    private String title;

    @NotBlank
    private String ISBN;

    @NotBlank
    private String authorsID;

    @NotBlank
    private String bookDescription;

    @NotBlank
    private String imagePath;

    @NotBlank
    private String pageCount;

    /*@NotBlank*/
    private String genre;

    @NotBlank
    private String publishedName;

    @NotBlank
    private String yearOfPublishing;

    @NotBlank
    private String printEditionCount;

    public BookDTO(@NotBlank String title, @NotBlank String ISBN, @NotBlank String authorsID,
                   @NotBlank String bookDescription, /*@NotBlank String imagePath,*/
                   @NotBlank String pageCount, /*@NotBlank*/ String genre, @NotBlank String publishedName,
                   @NotBlank String yearOfPublishing, @NotBlank String printEditionCount) {
        this.title = title;
        this.ISBN = ISBN;
        this.authorsID = authorsID;
        this.bookDescription = bookDescription;
        /*this.imagePath = imagePath;*/
        this.pageCount = pageCount;
        this.genre = genre;
        this.publishedName = publishedName;
        this.yearOfPublishing = yearOfPublishing;
        this.printEditionCount = printEditionCount;
    }
}
