package by.laniakea.model.list;

import by.laniakea.model.users.Reader;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "order_reader_list", schema = "worldoflibrary")
@Data
@NoArgsConstructor
@EqualsAndHashCode(of = "orderNumber")
@ToString(of = {"orderNumber", "orderExpired"})
public class OrderReader {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long orderNumber;

    @Version
    @Column(name = "version", nullable = false)
    private Long version;


    @OneToOne
    @JoinColumn(name = "request_from_reader_id")
    private RequestFromReader requestFromReader;

    @Column(name = "start_order", nullable = false)
    private LocalDate startOrder;

    @Column(name = "valid_date_order", nullable = false)
    private LocalDate validUntilDateOrder;

    @Column(name = "end_order")
    private LocalDate endOrder;

    @Column(name = "order_expired", nullable = false)
    private boolean orderExpired = false;

    public OrderReader(RequestFromReader requestFromReader, LocalDate startOrder, LocalDate validUntilDateOrder) {
        this.requestFromReader = requestFromReader;
        this.startOrder = startOrder;
        this.validUntilDateOrder = validUntilDateOrder;
    }
}
