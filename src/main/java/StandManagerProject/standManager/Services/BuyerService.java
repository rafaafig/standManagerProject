package StandManagerProject.standManager.Services;

import StandManagerProject.standManager.Models.Buyer;
import StandManagerProject.standManager.Models.Car;
import StandManagerProject.standManager.Repositories.BuyerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BuyerService {

    private final BuyerRepository buyerRepository;

    @Autowired
    public BuyerService(BuyerRepository buyerRepository) {
        this.buyerRepository = buyerRepository;
    }

    public List<Buyer> getAllBuyers() {
        return buyerRepository.findAll();
    }

    public Optional<Buyer> getBuyerById(Long id) {
        return buyerRepository.findById(id);
    }

    public Buyer addBuyer(Buyer buyer) {
        return buyerRepository.save(buyer);
    }

    public Buyer updateBuyer(Long id, Buyer updatedBuyer) {
        return buyerRepository.findById(id)
                .map(buyer -> {
                    buyer.setName(updatedBuyer.getName());
                    return buyerRepository.save(buyer);
                })
                .orElseGet(() -> {
                    updatedBuyer.setId(id);
                    return buyerRepository.save(updatedBuyer);
                });
    }

    public void deleteBuyer(Long id) {
        buyerRepository.deleteById(id);
    }


    public Buyer buyCar(Long buyerId, Car car) {

        Optional<Buyer> optionalBuyer = buyerRepository.findById(buyerId);

        if (optionalBuyer.isPresent()) {
            Buyer buyer = optionalBuyer.get();
            List<Car> carsBought = buyer.getCarsBought();
            carsBought.add(car);
            buyer.setCarsBought(carsBought);
            return buyerRepository.save(buyer);
        } else {

            throw new RuntimeException("Buyer not found with id: " + buyerId);
        }
    }
}
