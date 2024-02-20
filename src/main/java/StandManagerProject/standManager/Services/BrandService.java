package StandManagerProject.standManager.Services;

import StandManagerProject.standManager.Models.Brand;
import StandManagerProject.standManager.Models.Car;
import StandManagerProject.standManager.Models.Model;
import StandManagerProject.standManager.Repositories.BrandRepository;
import StandManagerProject.standManager.Repositories.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class BrandService {

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private CarRepository carRepository;

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



//    public List<Brand> getBrandsWithCars() {
//
//        List<Car> allCars = carRepository.findAll();
//
//
//        List<String> modelsNames = allCars.stream()
//                    .map(car -> car.getModel().getBrand())
//                    .collect(Collectors.toList());
//
//        return brands;
//    }


    public List<Brand> getBrandsWithCars() {

        List<Car> allCars = carRepository.findAll();

        List<Brand> allBrands = brandRepository.findAll();


        List<Car> modelsOfTheCars = allCars.stream()
                                        .filter(model-> carRepository.existsByModelId(model.getModel().getId()))
                                        .collect(Collectors.toList());



        List<Brand> brands = modelsOfTheCars.stream()
                .map(car -> car.getModel().getBrand())
                .distinct()
                .collect(Collectors.toList());


        return brands;
    }
}