package ee.farmx.farmxtry.service;

import ee.farmx.farmxtry.model.Lot;
import ee.farmx.farmxtry.model.User;
import ee.farmx.farmxtry.repository.LotRepository;
import ee.farmx.farmxtry.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LotService {

    @Autowired
    private LotRepository lotRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Lot> getAllLots() {

        List<Lot> lots = new ArrayList<>();
        lotRepository.findAll()
                .forEach(lots::add);
        return lots;
    }

    public void addLot(Lot lot, String username) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("Username doesn't exist"));
        lot.setSeller(user);
        lotRepository.save(lot);
    }
}
