package StandManagerProject.standManager.Repositories;

import StandManagerProject.standManager.Models.Car;
import StandManagerProject.standManager.Models.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SellerRepository extends JpaRepository<Seller, Long> {
}
