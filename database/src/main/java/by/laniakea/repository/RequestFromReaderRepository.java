package by.laniakea.repository;

import by.laniakea.model.list.RequestFromReader;
import by.laniakea.model.users.Reader;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface RequestFromReaderRepository extends CrudRepository<RequestFromReader, Long> {

    Page<RequestFromReader> findAll(Pageable pageable);

    @Query("select a from RequestFromReader a where a.order is empty and a.validUntilDateRequest > CURRENT_DATE")
    @EntityGraph(attributePaths = {"reader.firstName", "reader.lastName"})
    Page<RequestFromReader> findAllRequest(Pageable pageable);

    @Query("select a from RequestFromReader a where a.reader = :reader and a.order is empty ")
    RequestFromReader findByReader(@Param("reader")Reader reader);

    @Query("select a from RequestFromReader a where reader_id =?1 ")
    RequestFromReader findByReaderId(Long id);

    @Query("select a.id from RequestFromReader a where reader_id =?1")
    String findRequestByReaderId(Long id);
}
