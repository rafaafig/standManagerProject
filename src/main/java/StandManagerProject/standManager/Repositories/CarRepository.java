package StandManagerProject.standManager.Repositories;

import StandManagerProject.standManager.Models.Car;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

   boolean existsByModelId(Long modelID);

    @Query("select c from Car c where c.status = :status")
    Page<Car> findCarsByStatus(String status, Pageable page);

}
