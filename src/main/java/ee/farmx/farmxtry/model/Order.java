package ee.farmx.farmxtry.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    private Integer amount;

    private LocalDateTime orderDateTime;

    @ManyToOne
    private User buyer;

    @ManyToOne
    @JoinColumn(name = "lot_id", referencedColumnName = "ID")
    private Lot lot;


    @Enumerated(EnumType.STRING)
    @Column(length = 10)
    // Create Enums for order status (PAID, PENDING, NOT PAID)
    private EOrderStatus status;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Order order = (Order) o;

        return Objects.equals(id, order.id);
    }

    @Override
    public int hashCode() {
        return 737800560;
    }
}
