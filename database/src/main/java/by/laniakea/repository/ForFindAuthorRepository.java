package by.laniakea.repository;

import by.laniakea.model.books.Author;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ForFindAuthorRepository  {


    @Query("select a from Author a where a.lastName like concat(:lastName, '%')")
    Page<Author> findAuthorByLastName(@Param("lastName") String name, Pageable pageable);
}
