package StandManagerProject.standManager.Repositories;

import StandManagerProject.standManager.Models.Brand;
import StandManagerProject.standManager.Models.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModelRepository extends JpaRepository<Model, Long> {
}
