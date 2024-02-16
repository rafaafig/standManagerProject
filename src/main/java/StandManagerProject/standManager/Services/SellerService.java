package StandManagerProject.standManager.Services;

import StandManagerProject.standManager.Models.Seller;
import StandManagerProject.standManager.Repositories.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SellerService {

    @Autowired
    private SellerRepository sellerRepository;


    public List<Seller> getAllSellers() {
        return sellerRepository.findAll();
    }


    public Optional<Seller> getSellerById(Long id) {
        return sellerRepository.findById(id);
    }


    public Seller addSeller(Seller seller) {
        return sellerRepository.save(seller);
    }


    public Seller updateSeller(Long id, Seller updatedSeller) {
        if (sellerRepository.existsById(id)) {
            updatedSeller.setId(id);
            return sellerRepository.save(updatedSeller);
        } else {
            return null;
        }
    }

    public void deleteSeller(Long id) {
        sellerRepository.deleteById(id);
    }
}
