package by.laniakea.repository;

import by.laniakea.model.users.Reader;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ForFindBlackListRepository {

    @Query("select r from Reader r where r.blackList = true")
    List<Reader> findReaderFromBlackList();
}
