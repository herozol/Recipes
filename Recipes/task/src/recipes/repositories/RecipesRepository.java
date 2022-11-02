package recipes.repositories;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import recipes.models.Recipes;

import java.util.List;

@Repository
public interface RecipesRepository extends CrudRepository<Recipes, Long> {
    List<Recipes> findByNameIgnoreCaseContaining(String name, Sort sort);

    List<Recipes> findByCategoryIgnoreCase(String category, Sort sort2);

    boolean existsByEmail(String email);
}
