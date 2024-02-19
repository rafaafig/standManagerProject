package StandManagerProject.standManager.Controllers;


import StandManagerProject.standManager.Models.Buyer;
import StandManagerProject.standManager.Models.Car;
import StandManagerProject.standManager.Services.BuyerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/buyers")
public class BuyerController {

    private final BuyerService buyerService;

    @Autowired
    public BuyerController(BuyerService buyerService) {
        this.buyerService = buyerService;
    }

    @GetMapping
    public ResponseEntity<List<EntityModel<Buyer>>> getAllBuyers() {
        List<Buyer> buyers = buyerService.getAllBuyers();
        List<EntityModel<Buyer>> buyerModels = buyers.stream()
                .map(buyer -> EntityModel.of(buyer,
                        Link.of("/buyers/" + buyer.getId()).withSelfRel(),
                        Link.of("/buyers/" + buyer.getId()).withRel("update"),
                        Link.of("/buyers/" + buyer.getId()).withRel("delete")))
                .toList();
        return ResponseEntity.ok(buyerModels);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Buyer>> getBuyerById(@PathVariable Long id) {
        Buyer buyer = buyerService.getBuyerById(id)
                .orElseThrow(() -> new RuntimeException("Buyer not found with id: " + id));
        return ResponseEntity.ok(EntityModel.of(buyer,
                Link.of("/buyers/" + id).withSelfRel(),
                Link.of("/buyers/" + id).withRel("update"),
                Link.of("/buyers/" + id).withRel("delete")));
    }

    @PostMapping
    public ResponseEntity<EntityModel<Buyer>> addBuyer(@RequestBody Buyer buyer) {
        Buyer addedBuyer = buyerService.addBuyer(buyer);
        return ResponseEntity.ok(EntityModel.of(addedBuyer,
                Link.of("/buyers/" + addedBuyer.getId()).withSelfRel(),
                Link.of("/buyers/" + addedBuyer.getId()).withRel("update"),
                Link.of("/buyers/" + addedBuyer.getId()).withRel("delete")));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<Buyer>> updateBuyer(@PathVariable Long id, @RequestBody Buyer buyer) {
        Buyer updatedBuyer = buyerService.updateBuyer(id, buyer);
        return ResponseEntity.ok(EntityModel.of(updatedBuyer,
                Link.of("/buyers/" + id).withSelfRel(),
                Link.of("/buyers/" + id).withRel("update"),
                Link.of("/buyers/" + id).withRel("delete")));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBuyer(@PathVariable Long id) {
        buyerService.deleteBuyer(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{buyerId}/buy-car")
    public ResponseEntity<EntityModel<Buyer>> buyCar(@PathVariable Long buyerId, @RequestBody Car car) {
        Buyer buyer = buyerService.buyCar(buyerId, car);
        return ResponseEntity.ok(EntityModel.of(buyer,
                Link.of("/buyers/" + buyerId).withSelfRel(),
                Link.of("/buyers/" + buyerId).withRel("update"),
                Link.of("/buyers/" + buyerId).withRel("delete")));
    }
}