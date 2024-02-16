package StandManagerProject.standManager.Controllers;

import StandManagerProject.standManager.Models.Car;
import StandManagerProject.standManager.Models.Seller;
import StandManagerProject.standManager.Services.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("stand/sellers")
public class SellerController {

    public SellerService sellerService;

    @Autowired
    public SellerController(SellerService sellerService){
        this.sellerService = sellerService;
    }

    @GetMapping
    public ResponseEntity<List<EntityModel<Seller>>> getAllSellers() {
        List<Seller> sellers = sellerService.getAllSellers();
        List<EntityModel<Seller>> sellerModels = sellers.stream()
                .map(seller -> EntityModel.of(seller,
                        linkTo(methodOn(SellerController.class).getSellerById(seller.getId())).withSelfRel(),
                        linkTo(methodOn(SellerController.class).updateSeller(seller.getId(), seller)).withRel("update"),
                        linkTo(methodOn(SellerController.class).deleteSeller(seller.getId())).withRel("delete")))
                .toList();
        return ResponseEntity.ok(sellerModels);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Seller>> getSellerById(@PathVariable Long id) {
        return sellerService.getSellerById(id)
                .map(seller -> {
                    EntityModel<Seller> model = EntityModel.of(seller,
                            linkTo(methodOn(SellerController.class).getSellerById(id)).withSelfRel(),
                            linkTo(methodOn(SellerController.class).updateSeller(id, seller)).withRel("update"),
                            linkTo(methodOn(SellerController.class).deleteSeller(id)).withRel("delete"));
                    return ResponseEntity.ok(model);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<EntityModel<Seller>> addSeller(@RequestBody Seller seller) {
        Seller savedSeller = sellerService.addSeller(seller);
        EntityModel<Seller> model = EntityModel.of(savedSeller,
                linkTo(methodOn(SellerController.class).getSellerById(savedSeller.getId())).withSelfRel());
        return ResponseEntity.status(HttpStatus.CREATED).body(model);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<Seller>> updateSeller(@PathVariable Long id, @RequestBody Seller seller) {
        Seller updatedSeller = sellerService.updateSeller(id, seller);
        EntityModel<Seller> model = EntityModel.of(updatedSeller,
                linkTo(methodOn(SellerController.class).getSellerById(id)).withSelfRel(),
                linkTo(methodOn(SellerController.class).updateSeller(id, seller)).withRel("update"),
                linkTo(methodOn(SellerController.class).deleteSeller(id)).withRel("delete"));
        return ResponseEntity.ok(model);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSeller(@PathVariable Long id) {
        sellerService.deleteSeller(id);
        return ResponseEntity.noContent().build();
    }
}