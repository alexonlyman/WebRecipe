package skypro.webrecipe.controllers;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import skypro.webrecipe.model.Ingredient;
import skypro.webrecipe.model.Recipe;
import skypro.webrecipe.services.RecipeServise;

@RestController
@RequiredArgsConstructor
@RequestMapping("/recipe")
public class RecipeController {
    private final RecipeServise recipeServise;

    @GetMapping("{id}")
    public ResponseEntity<Recipe> getRecipe(@PathVariable Integer id) {
        Recipe recipe = recipeServise.getRecipe(id);
        if (recipe == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(recipe);
    }
    @GetMapping
    public ResponseEntity<Recipe> getAllRecipes() {
        recipeServise.getAllRecipes();
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<Recipe> addRecipe(@Valid @RequestBody Recipe recipe) {

        Recipe reciepe = recipeServise.addRecipe(recipe);
        return ResponseEntity.ok().body(reciepe);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Recipe> editIngredient(@PathVariable Integer id, @RequestBody Recipe recipe) {
        Recipe reciepe = recipeServise.editRecipe(id, recipe);
        if (reciepe == null) {
            ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecipe(@PathVariable Integer id) {
        boolean reciepe = recipeServise.deleteRecipe(id);
        return ResponseEntity.ok().build();
    }

}
