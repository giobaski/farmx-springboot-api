package ee.farmx.farmxtry.repository;

import java.util.Optional;

import ee.farmx.farmxtry.model.ERole;
import ee.farmx.farmxtry.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}