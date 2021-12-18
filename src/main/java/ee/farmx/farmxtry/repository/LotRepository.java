package ee.farmx.farmxtry.repository;

import ee.farmx.farmxtry.model.Lot;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LotRepository extends JpaRepository<Lot, Long> {

      List<Lot> findBySellerUsername(String username);
}
