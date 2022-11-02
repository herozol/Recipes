package recipes.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import recipes.models.Recipes;
import recipes.services.RecipeService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/recipe")
public class RecipesController {
    @Autowired
    RecipeService recipeService;

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Recipes getRecipes(@PathVariable int id) {
        return recipeService.get(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping(path = "/search/", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Recipes> getRecipesByName(@RequestParam(required = false) String name,
                                          @RequestParam(required = false) String category) {
        if (name != null || category != null) {
            return recipeService.getBy(name, category);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(path = "/new", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> postRecipes(@Valid @RequestBody Recipes inputRecipes) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        recipeService.save(inputRecipes, email);
        return new ResponseEntity<>(inputRecipes.outputId(), HttpStatus.OK);
    }

    @PutMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> updateRecipes(@PathVariable int id,
                                                @Valid @RequestBody Recipes inputRecipes) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        if (recipeService.checkOwner(email)) {
            if (recipeService.checkById(id)) {
                recipeService.update(inputRecipes, id, email);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }
        } else {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
    }

    @DeleteMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> removeRecipes(@PathVariable int id) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        if (recipeService.checkOwner(email)) {
            if (recipeService.checkById(id)) {
                recipeService.remove(id);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } else {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
    }
}
