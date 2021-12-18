package ee.farmx.farmxtry.repository;

import ee.farmx.farmxtry.model.EOrderStatus;
import ee.farmx.farmxtry.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository  extends JpaRepository<Order, Long> {

    List<Order> findAllByBuyerUsername(String username);

    List<Order> findOrderByStatus(EOrderStatus status);

}
