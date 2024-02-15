package StandManagerProject.standManager.Services;

import StandManagerProject.standManager.Models.Car;
import StandManagerProject.standManager.Models.Stand;
import StandManagerProject.standManager.Repositories.StandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StandService {

    private final StandRepository standRepository;

    @Autowired
    public StandService(StandRepository standRepository) {
        this.standRepository = standRepository;
    }

    public List<Stand> getAllStands() {
        return standRepository.findAll();
    }

    public Optional<Stand> getStandById(Long id) {
        return standRepository.findById(id);
    }

    public Stand addStand(Stand stand) {
        return standRepository.save(stand);
    }

    public Stand updateStand(Long id, Stand updatedStand) {
        return standRepository.findById(id)
                .map(stand -> {
                    stand.setAddress(updatedStand.getAddress());
                    stand.setCapacity(updatedStand.getCapacity());
                    return standRepository.save(stand);
                })
                .orElseGet(() -> {
                    updatedStand.setStandID(id);
                    return standRepository.save(updatedStand);
                });
    }

    public void deleteStand(Long id) {
        standRepository.deleteById(id);
    }
}


