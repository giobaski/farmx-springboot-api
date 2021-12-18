package ee.farmx.farmxtry.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "lots")
public class Lot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String productName;

    //create Farm class with location and official documents
    private String description;

    @ManyToOne
    private User seller;

    @OneToMany(mappedBy = "lot", cascade = CascadeType.ALL)
    @ToString.Exclude
    @JsonIgnore
    private List<Order> orders = new ArrayList<>();

    //create LotDetail?
    private Integer initAmount;

    private Integer currentAmount;

    private Double openingPrice;

    private Double closingPrice;

    private LocalDate openingDate;

    private LocalDate closingDate;

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public List<Order> getOrders() {
        return orders;
    }


    public Lot(Long id, String productName, String description, User seller, Integer initAmount, Integer currentAmount) {
        this.id = id;
        this.productName = productName;
        this.description = description;
        this.seller = seller;
        this.initAmount = initAmount;
        //check this
        this.currentAmount = initAmount;
    }


    public void updateCurrentAmount(Integer purchaseAmount){
        this.currentAmount -= purchaseAmount;
    }

    public void addOrder(Order order){ this.orders.add(order); }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Lot lot = (Lot) o;

        return Objects.equals(id, lot.id);
    }

    @Override
    public int hashCode() {
        return 115649169;
    }
}
