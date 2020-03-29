package by.laniakea.repository;

import by.laniakea.model.list.OrderReader;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor(onConstructor = @_(@Autowired))
public class OrderReaderServiceRepository  {

    private final OrderRepository orderRepository;

    public void save(OrderReader object) {
        orderRepository.save(object);

    }

    public List<OrderReader> findAll(){
        List<OrderReader> orderReaders = new ArrayList<>();
        Iterable<OrderReader> iterable = orderRepository.findAll();
        iterable.forEach(orderReaders::add);
        return orderReaders;
    }



    public Page<OrderReader> findAll(Pageable pageable){
        return orderRepository.findAll(pageable);
    }

    public Page<OrderReader>findOpenOrder(Pageable pageable){
        return orderRepository.findOpenOrder(pageable);
    }

    public OrderReader findByID(Long id){
        return orderRepository.findById(id).get();
    }

    public Page<OrderReader> findOverdueOrderReader(Pageable pageable){
        return orderRepository.findOverdueOrder(pageable);
    }

    public void deleteOrder(OrderReader orderReader){
        orderRepository.delete(orderReader);
    }


}
