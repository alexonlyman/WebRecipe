package skypro.webrecipe.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import skypro.webrecipe.model.Recipe;
import skypro.webrecipe.services.RecipeServise;

import javax.validation.Valid;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@RestController
@RequiredArgsConstructor
@RequestMapping("/recipe")
@Tag(name = "Рецепты", description = "CRUD операции для работы с рецептами")
public class RecipeController {
    private final RecipeServise recipeServise;

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
