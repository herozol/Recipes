package recipes.services;

import recipes.models.Recipes;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface RecipeService {

    @Transactional
    Optional<Recipes> get(long id);

    @Transactional
    List<Recipes> getBy(String param1, String param2);

    @Transactional
    void save(Recipes recipes, String email);

    @Transactional
    void update(Recipes recipes, long id, String email);

    @Transactional
    void remove(long id);

    @Transactional
    boolean checkById(long id);

    @Transactional
    boolean checkOwner(String email);

}
