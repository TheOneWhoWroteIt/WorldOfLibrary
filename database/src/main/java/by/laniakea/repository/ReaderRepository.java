package by.laniakea.repository;

import by.laniakea.model.users.Reader;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ReaderRepository extends CrudRepository<Reader, Long> {

    @Query("select u from Reader u where u.login =?1")
    Reader findByLogin(String login);

    Page<Reader> findAll(Pageable pageable);

    @Query("select a from Reader a where a.blackList = true")
    Page<Reader> findAllReaderFromBlackList(Pageable pageable);

    @Query("select u.firstName, u.ticketNumber, u.address.city, u.email from Reader u where u.login =?1")
    String loadInfoByReader(String name);

    @Query("select a.password, a.role.roleName from Reader a where a.login=?1")
    String findReaderSecurityByLogin(String login);
}
