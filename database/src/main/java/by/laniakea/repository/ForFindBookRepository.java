package by.laniakea.repository;

import by.laniakea.model.books.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface ForFindBookRepository extends JpaRepository<Book, Long> {

    @Query("select a.books from Author a where a.lastName like concat(:lastName, '%')")
    Page<Book> findByAuthor(@Param("lastName") String lastName, Pageable pageable);

    @Query("select a.books from Author a where a.id =?1")
    List<Book> findByAuthorId(Long id);

     @Query("select g from Book g where g.genre = :name")
     Page<Book> findByGenre(@Param("name") String genre, Pageable pageable);

    @Query("select b from Book b where b.title like concat(:title, '%')")
    Page<Book> findByTitle(@Param("title") String title, Pageable pageable);

    @Query("select b from Book b where b.title like concat(:letter, '%')")
    Page<Book> findByAlfavit(@Param("letter") String letter, Pageable pageable);
}
