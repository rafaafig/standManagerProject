package StandManagerProject.standManager.Controllers;

import StandManagerProject.standManager.Models.Stand;
import StandManagerProject.standManager.Services.StandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stands")
public class StandController {
    private final StandService standService;

    @Autowired
    public StandController(StandService standService) {
        this.standService = standService;
    }

    @GetMapping
    public List<Stand> getAllStands() {
        return standService.getAllStands();
    }

    @GetMapping("/{id}")
    public Stand getStandById(@PathVariable Long id) {
        return standService.getStandById(id)
                .orElseThrow(() -> new RuntimeException("Stand not found with id: " + id));
    }

    @PostMapping
    public Stand addStand(@RequestBody Stand stand) {
        return standService.addStand(stand);
    }

    @PutMapping("/{id}")
    public Stand updateStand(@PathVariable Long id, @RequestBody Stand stand) {
        return standService.updateStand(id, stand);
    }

    @DeleteMapping("/{id}")
    public void deleteStand(@PathVariable Long id) {
        standService.deleteStand(id);
    }
}