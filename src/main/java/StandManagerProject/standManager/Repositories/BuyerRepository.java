package StandManagerProject.standManager.Repositories;

import StandManagerProject.standManager.Models.Buyer;
import StandManagerProject.standManager.Models.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BuyerRepository extends JpaRepository<Buyer, Long> {
}
