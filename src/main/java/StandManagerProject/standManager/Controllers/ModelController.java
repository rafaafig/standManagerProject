package StandManagerProject.standManager.Controllers;

import StandManagerProject.standManager.Models.Model;
import StandManagerProject.standManager.Services.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("stand/brand/model")
public class ModelController {

    public ModelService modelService;


    @Autowired
    public ModelController(ModelService modelService){
        this.modelService = modelService;
    }

    @GetMapping
    public ResponseEntity<List<EntityModel<Model>>> getAllModels(){
        List<Model> models = modelService.getAllModels();
        List<EntityModel<Model>> modelModels = models.stream()
                .map(model -> EntityModel.of(model,
                        linkTo(methodOn(ModelController.class).getModelById(model.getId())).withSelfRel(),
                        linkTo(methodOn(ModelController.class).updateModel(model.getId(), model)).withRel("update"),
                        linkTo(methodOn(ModelController.class).deleteModel(model.getId())).withRel("delete")))
                .toList();
        return ResponseEntity.ok(modelModels);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Model>> getModelById(@PathVariable Long id){
        return modelService.getModelById(id)
                .map(model -> {
                    EntityModel<Model> modelx = EntityModel.of(model,
                            linkTo(methodOn(ModelController.class).getModelById(model.getId())).withSelfRel(),
                            linkTo(methodOn(ModelController.class).updateModel(model.getId(), model)).withRel("update"),
                            linkTo(methodOn(ModelController.class).deleteModel(model.getId())).withRel("delete"));

                    return ResponseEntity.ok(modelx);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<EntityModel<Model>> addModel(@RequestBody Model model){
        Model savedModel = modelService.addModel(model);
        EntityModel<Model> modelx = EntityModel.of(savedModel,
                linkTo(methodOn(ModelController.class).getModelById(savedModel.getId())).withSelfRel());
        return ResponseEntity.status(HttpStatus.CREATED).body(modelx);
    }


    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<Model>> updateModel(@PathVariable Long id, @RequestBody Model model){
        Model updatedModel = modelService.updateModel(id, model);
        EntityModel<Model> modelx = EntityModel.of(updatedModel,
                linkTo(methodOn(ModelController.class).getModelById(id)).withSelfRel(),
                linkTo(methodOn(ModelController.class).updateModel(id, model)).withRel("update"),
                linkTo(methodOn(ModelController.class).getModelById(id)).withRel("delete"));

        return ResponseEntity.ok(modelx);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteModel(@PathVariable Long id){
        modelService.deleteModel(id);
        return ResponseEntity.noContent().build();
    }
}
