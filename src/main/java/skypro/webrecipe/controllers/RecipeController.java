package skypro.webrecipe.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import skypro.webrecipe.model.Recipe;
import skypro.webrecipe.services.FileService;
import skypro.webrecipe.services.RecipeServise;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/recipe")
@Tag(name = "Рецепты", description = "CRUD операции для работы с рецептами")
public class RecipeController {
    private final RecipeServise recipeServise;
    private final FileService fileService;

    @GetMapping("{id}")
    @Operation(
            description = "Поиск рецепта по ID"
    )
    @Parameters(value = {@Parameter(name = "ID", example = "1")})
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

    public ResponseEntity<Recipe> addRecipe(@RequestBody Recipe recipe) {
        Recipe reciepe = recipeServise.addRecipe(recipe);
        return ResponseEntity.ok().body(reciepe);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Recipe> editIngredient(@Valid @PathVariable Integer id, @RequestBody Recipe recipe) {
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
