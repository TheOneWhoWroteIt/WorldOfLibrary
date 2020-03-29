package by.laniakea.repository;

import by.laniakea.model.books.Author;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface AuthorRepository extends CrudRepository<Author, Long>, ForFindAuthorRepository {

    Page<Author> findAll(Pageable pageable);
}
