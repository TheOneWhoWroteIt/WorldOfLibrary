package by.laniakea.repository;

import by.laniakea.dto.BookDTO;
import by.laniakea.model.books.Author;
import by.laniakea.model.books.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
@RequiredArgsConstructor(onConstructor = @_(@Autowired))
public class BookServiceRepository {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public void save(BookDTO object) {

        List<Author> authors = findAuthors(object.getAuthorsID());

        Integer bookCount = Integer.valueOf(object.getPrintEditionCount());
        Integer year = Integer.valueOf(object.getYearOfPublishing());
        String img = "/resources/book_cover/" + object.getImagePath();
        String genre = genreConvert(object.getGenre());


        Book book = new Book(object.getISBN(), object.getBookDescription(), img, object.getPageCount(),
                authors, genre, object.getTitle(), object.getPublishedName(), year, bookCount);
        bookRepository.save(book);

    }

    public void save(Book book) {
        bookRepository.save(book);
    }


    public Page<Book> findAll(Pageable pageable) {
        Page<Book> booksPage = bookRepository.findAll(pageable);

        return booksPage;
    }

    public Book findByID(Long id) {
        Book book = bookRepository.findById(id).get();
        return book;

    }

    public void update(BookDTO object, Book book) {

        if (object.getAuthorsID() != null && !object.getAuthorsID().isEmpty()) {
            List<Author> authors = findAuthors(object.getAuthorsID());
            book.setAuthors(authors);
        }
        if (object.getISBN() != null && !object.getISBN().isEmpty()) {
            book.setISBN(object.getISBN());
        }
        if (object.getBookDescription() != null && !object.getBookDescription().isEmpty()) {
            book.setBookDescription(object.getBookDescription());
        }
        if (object.getPageCount() != null && !object.getPageCount().isEmpty()) {
            book.setPageCount(object.getPageCount());
        }
        if (object.getGenre() != null && !object.getGenre().isEmpty() && !object.getGenre().equals("Выберите жанр")) {
            book.setGenre(genreConvert(object.getGenre()));
        }
        if (object.getTitle() != null && !object.getTitle().isEmpty()) {
            book.setTitle(object.getTitle());
        }
        if (object.getPublishedName() != null && !object.getPublishedName().isEmpty()) {
            book.setPublishedName(object.getPublishedName());
        }
        if (object.getYearOfPublishing() != null && !object.getYearOfPublishing().isEmpty()) {
            book.setYearOfPublishing(Integer.valueOf(object.getYearOfPublishing()));
        }
        if (object.getPrintEditionCount() != null && !object.getPrintEditionCount().isEmpty()) {
            book.setPrintEditionCount(Integer.valueOf(object.getPrintEditionCount()));
        }

        bookRepository.save(book);

    }

    List<Author> findAuthors(String str) {
        String[] authorsID = str.split("#");
        List<Author> authors = new ArrayList<>();
        for (int i = 0; i < authorsID.length; i++) {
            Long id = Long.valueOf(authorsID[i]);
            Author author = authorRepository.findById(id).get();
            if (author != null) {
                authors.add(author);
            } else {

            }
        }

        return authors;
    }

    String genreConvert(String str) {
        String genre = null;

        switch (str) {
            case "DETECTIVE":
                genre = "Детектив";
                break;
            case "LOVE_STORY":
                genre = "Любовный роман";
                break;
            case "HISTORICAL_NOVEL":
                genre = "Исторический роман";
                break;
            case "FANTASY":
                genre = "Фэнтези";
                break;
            case "SCIENTIFIC_LITERATURE":
                genre = "Научная литература";
                break;
            case "COUNTERCULTURE":
                genre = "Контркультура";
                break;
            case "MUSIC":
                genre = "Музыка";
                break;
            case "EDUCATION_LITERATURE":
                genre = "Обучающая литература";
                break;
            case "MYSTIC":
                genre = "Мистика";
                break;
            case "ADVENTURES":
                genre = "Приключение";
                break;
            case "THRILLER":
                genre = "Триллер";
                break;
            case "JOURNALISM":
                genre = "Публицистика";
                break;
            case "SCIENCE_FICTION":
                genre = "Научная фантастика";
                break;
        }

        return genre;
    }

    public Set<Book> addBookInRequest(Set<Book> books, Book book) {

        if (books == null || books.isEmpty()) {
            books = new HashSet<>(6);
        }

        if (books.size() < 6) {
            books.add(book);
        }


        return books;
    }

    public Page<Book> findByGenre(String genreName, Pageable pageable) {
        return bookRepository.findByGenre(genreName, pageable);
    }

    public Page<Book> findByAlfavit(String letter, Pageable pageable) {
        return bookRepository.findByAlfavit(letter, pageable);
    }

    public Page<Book> findByTitle(String title, Pageable pageable) {
        return bookRepository.findByTitle(title, pageable);
    }

    public Page<Book> findByAuthor(String title, Pageable pageable) {
        return bookRepository.findByAuthor(title, pageable);
    }

    public void saveAll(Set<Book> books) {
        bookRepository.saveAll(books);

    }

    public List<Book> findByAuthorId(Long id) {
        return bookRepository.findByAuthorId(id);
    }
}
