package ee.farmx.farmxtry.controller;

import ee.farmx.farmxtry.model.Lot;
import ee.farmx.farmxtry.model.Order;
import ee.farmx.farmxtry.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

//    //    @RequestMapping(method= RequestMethod.POST, value = "/users/{username}/bids/{amount}")
//    @PostMapping("/users/{username}/orders/{amount}")
//    public ResponseEntity<String> addOrders(@RequestBody Lot lot, @PathVariable String username, @PathVariable Integer amount) {
//        orderService.placeOrder(lot, username, amount);
//        return new ResponseEntity<>("Order has been placed", HttpStatus.CREATED);
//    }

    @GetMapping("/{username}")
    public ResponseEntity<List<Order>> getOrdersByUsername (@PathVariable String username) {
        try{
            List<Order> orders = orderService.findOrdersByBuyer(username);
            if(orders.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(orders, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //    @GetMapping("/orders/seller/{username}")


}
