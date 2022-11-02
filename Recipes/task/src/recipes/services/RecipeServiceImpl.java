package recipes.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import recipes.models.Recipes;
import recipes.repositories.RecipesRepository;

import java.util.List;
import java.util.Optional;

@Service
public class RecipeServiceImpl implements RecipeService {
    @Autowired
    RecipesRepository recipesRepository;

    @Override
    public Optional<Recipes> get(long id) {
        return recipesRepository.findById(id);
    }

    @Override
    public List<Recipes> getBy(String name, String category) {
        return name != null ?
                recipesRepository.findByNameIgnoreCaseContaining(
                        name, Sort.by("date").descending()) :
                recipesRepository.findByCategoryIgnoreCase(
                        category, Sort.by("date").descending());
    }

    @Override
    public void save(Recipes recipes, String email) {
        recipes.setEmail(email);
        recipesRepository.save(recipes);
    }

    @Override
    public void update(Recipes recipes, long id, String email) {
        Recipes oldRecipe = recipesRepository.findById(id).orElse(new Recipes());
        recipes.setId(oldRecipe.getId());
        recipes.setEmail(email);
        recipesRepository.save(recipes);
    }

    @Override
    public void remove(long id) {
        recipesRepository.deleteById(id);
    }

    @Override
    public boolean checkById(long id) {
        return recipesRepository.existsById(id);
    }

    @Override
    public boolean checkOwner(String email) {
        return recipesRepository.existsByEmail(email);
    }
}
