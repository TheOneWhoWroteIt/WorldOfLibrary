package by.laniakea.repository;

import by.laniakea.model.list.OrderReader;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<OrderReader, Long> {

    Page<OrderReader> findAll(Pageable pageable);

    @Query("select a from OrderReader a where a.orderExpired = false ")
    Page<OrderReader> findOpenOrder(Pageable pageable);

    @Query("select a from OrderReader a where a.validUntilDateOrder < CURRENT_DATE and a.orderExpired = false and a.requestFromReader.reader.blackList = false")
    Page<OrderReader> findOverdueOrder(Pageable pageable);
}
