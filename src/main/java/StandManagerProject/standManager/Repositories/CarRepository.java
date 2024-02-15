package StandManagerProject.standManager.Repositories;

import StandManagerProject.standManager.Models.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Long> {
}
