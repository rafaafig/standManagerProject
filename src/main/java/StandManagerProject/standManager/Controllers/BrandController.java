package StandManagerProject.standManager.Controllers;

import StandManagerProject.standManager.Models.Brand;
import StandManagerProject.standManager.Models.Seller;
import StandManagerProject.standManager.Services.BrandService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("stand/brand")
public class BrandController {

    public BrandService brandService;


    @Autowired
    public BrandController(BrandService brandService){
        this.brandService = brandService;
    }

    @GetMapping
    public ResponseEntity<List<EntityModel<Brand>>> getAllBrands(){
        List<Brand> brands = brandService.getAllBrands();
        List<EntityModel<Brand>> brandModels = brands.stream()
                .map(brand -> EntityModel.of(brand,
                        linkTo(methodOn(BrandController.class).getBrandById(brand.getId())).withSelfRel(),
                        linkTo(methodOn(BrandController.class).updateBrand(brand.getId(), brand)).withRel("update"),
                        linkTo(methodOn(BrandController.class).deleteBrand(brand.getId())).withRel("delete")))
                .toList();
        return ResponseEntity.ok(brandModels);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Brand>> getBrandById(@PathVariable Long id){
        return brandService.getBrandById(id)
                .map(brand -> {
                    EntityModel<Brand> model = EntityModel.of(brand,
                        linkTo(methodOn(BrandController.class).getBrandById(brand.getId())).withSelfRel(),
                        linkTo(methodOn(BrandController.class).updateBrand(brand.getId(), brand)).withRel("update"),
                        linkTo(methodOn(BrandController.class).deleteBrand(brand.getId())).withRel("delete"));

                return ResponseEntity.ok(model);
                })
                .orElse(ResponseEntity.notFound().build());
    }


    @PostMapping
    public ResponseEntity<EntityModel<Brand>> addBrand(@RequestBody Brand brand){
        Brand savedBrand = brandService.addBrand(brand);
        EntityModel<Brand> model = EntityModel.of(savedBrand,
                linkTo(methodOn(BrandController.class).getBrandById(savedBrand.getId())).withSelfRel());
        return ResponseEntity.status(HttpStatus.CREATED).body(model);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<Brand>> updateBrand(@PathVariable Long id, @RequestBody Brand brand){
        Brand updatedBrand = brandService.updateBrand(id, brand);
        EntityModel<Brand> model = EntityModel.of(updatedBrand,
                linkTo(methodOn(BrandController.class).getBrandById(id)).withSelfRel(),
                linkTo(methodOn(BrandController.class).updateBrand(id, brand)).withRel("update"),
                linkTo(methodOn(BrandController.class).getBrandById(id)).withRel("delete"));

        return ResponseEntity.ok(model);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBrand(@PathVariable Long id){
        brandService.deleteBrand(id);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/withcars")
    public List<Brand> getBrandsWithCars() {
        return brandService.getBrandsWithCars();
    }
}
