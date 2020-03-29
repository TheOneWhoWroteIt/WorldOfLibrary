package by.laniakea.repository;

import by.laniakea.model.books.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Set;

public interface BookRepository extends CrudRepository<Book, Long>, ForFindBookRepository, PagingAndSortingRepository<Book, Long>{

    @EntityGraph(attributePaths = {"authors.firstName", "authors.lastName"})
    Page<Book> findAll(Pageable pageable);




}
