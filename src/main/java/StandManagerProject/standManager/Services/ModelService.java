package StandManagerProject.standManager.Services;

import StandManagerProject.standManager.Models.Brand;
import StandManagerProject.standManager.Models.Model;
import StandManagerProject.standManager.Repositories.ModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ModelService {

    @Autowired
    private ModelRepository modelRepository;

    public List<Model> getAllModels(){
        return modelRepository.findAll();
    }

    public Optional<Model> getModelById(Long id){
        return modelRepository.findById(id);
    }

    public Model addModel(Model model){
        return modelRepository.save(model);
    }

    public Model updateModel(Long id, Model updatedModel){
        if (modelRepository.existsById(id)) {
            updatedModel.setId(id);
            return modelRepository.save(updatedModel);
        } else {
            return null;
        }
    }

    public void deleteModel(Long id){
        modelRepository.deleteById(id);
    }

}
