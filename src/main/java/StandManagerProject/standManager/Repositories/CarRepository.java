package StandManagerProject.standManager.Repositories;

import StandManagerProject.standManager.Models.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

   boolean existsByModelId(Long modelID);

}
