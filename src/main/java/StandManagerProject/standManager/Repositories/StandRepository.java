package StandManagerProject.standManager.Repositories;


import StandManagerProject.standManager.Models.Stand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StandRepository extends JpaRepository<Stand, Long> {
}
