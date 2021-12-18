package ee.farmx.farmxtry.service;

import com.sun.xml.bind.v2.runtime.output.SAXOutput;
import ee.farmx.farmxtry.model.Lot;
import ee.farmx.farmxtry.model.Order;
import ee.farmx.farmxtry.model.User;
import ee.farmx.farmxtry.repository.LotRepository;
import ee.farmx.farmxtry.repository.OrderRepository;
import ee.farmx.farmxtry.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private LotRepository lotRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    public void placeOrder(Long lotId, String username, Integer amount) {

        Lot tempLot = lotRepository.findById(lotId).orElseThrow();
        User user = userRepository.findByUsername(username).orElseThrow();
        if(amount <= tempLot.getCurrentAmount() ) {
            Order order = new Order();
            order.setAmount(amount);
            order.setBuyer(user);
            order.setLot(tempLot);

            tempLot.updateCurrentAmount(amount);
            tempLot.addOrder(order);

            System.out.print(tempLot);
            System.out.print("####################################");
            lotRepository.save(tempLot);
        }else {
            throw new RuntimeException("Such amount is not available right now!, try less then " + tempLot.getCurrentAmount());
        }

    }

    public List<Order> findOrdersByBuyer(String username) {
        return orderRepository.findAllByBuyerUsername(username);
    }
}
