package StandManagerProject.standManager.Services;

import StandManagerProject.standManager.Models.Brand;
import StandManagerProject.standManager.Models.Car;
import StandManagerProject.standManager.Repositories.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BrandService {

    @Autowired
    private BrandRepository brandRepository;

    public List<Brand> getAllBrands(){
        return brandRepository.findAll();
    }

    public Optional<Brand> getBrandById(Long id){
        return brandRepository.findById(id);
    }

    public Brand addBrand(Brand brand){
        return brandRepository.save(brand);
    }

    public Brand updateBrand(Long id, Brand updatedBrand){
        if (brandRepository.existsById(id)) {
            updatedBrand.setId(id);
            return brandRepository.save(updatedBrand);
        } else {
            return null;
        }
    }

    public void deleteBrand(Long id){
        brandRepository.deleteById(id);
    }

}
