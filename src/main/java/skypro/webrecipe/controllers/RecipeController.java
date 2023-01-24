package skypro.webrecipe.controllers;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
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
    Recipe getRecipe(@PathVariable Integer id) {
        return recipeServise.getRecipe(id);
    }

    @PostMapping
    Recipe addRecipe(@Valid @RequestBody Recipe recipe) {
        return recipeServise.addRecipe(recipe);
    }

}
