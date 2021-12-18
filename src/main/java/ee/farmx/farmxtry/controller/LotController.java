package ee.farmx.farmxtry.controller;

import ee.farmx.farmxtry.model.Lot;
import ee.farmx.farmxtry.model.PlaceOrderRequest;
import ee.farmx.farmxtry.model.User;
import ee.farmx.farmxtry.service.LotService;
import ee.farmx.farmxtry.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/lots")
public class LotController {

    @Autowired
    private LotService lotService;

    @Autowired
    private OrderService orderService;

    @GetMapping("")
    public ResponseEntity<List<Lot>> getAllLots() {
        List<Lot> lots = lotService.getAllLots();
        return ResponseEntity.ok(lots);
    }

    //only Admin Role
    @PostMapping("")
//    @PreAuthorize("hasRole('ADMIN')")
    public void addLot(@RequestBody Lot lot){
//        String username = principal.getName();
        lotService.addLot(lot, lot.getSeller().getUsername());
    }

    @PostMapping("/{lotId}/orders/{amount}")
    public ResponseEntity<String> placeOrders(@PathVariable String lotId,
//                                              @RequestBody PlaceOrderRequest placeOrderRequest,
                                              @PathVariable String amount,
                                              Principal principal) {
        try{
            String username = principal.getName(); // if username is null throw error
            orderService.placeOrder(Long.parseLong(lotId), username,Integer.parseInt(amount));
            return new ResponseEntity<>("Order has been placed", HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        //Todo: does not save in order table
    }
}
