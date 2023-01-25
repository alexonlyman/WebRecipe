package skypro.webrecipe.controllers;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import skypro.webrecipe.model.Ingredient;
import skypro.webrecipe.services.IngredientService;


@RestController
@RequestMapping("/ingredient")
@RequiredArgsConstructor
public class IngredientController {
    private final IngredientService ingredientService;

    @GetMapping("/{id}")
    public ResponseEntity<Ingredient> getIngredietn(@PathVariable Integer id) {
        Ingredient ingredietn = ingredientService.getIngredietn(id);
        if (ingredietn == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ingredietn);
    }

    @GetMapping
    public ResponseEntity<Ingredient> getAllIngredients() {
        ingredientService.getAllIngredients();
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<Ingredient> addIngredietn(@Valid @RequestBody Ingredient ingredient) {

        Ingredient ingredietn = ingredientService.addIngredient(ingredient);
        return ResponseEntity.ok().body(ingredient);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ingredient> editIngredient(@PathVariable Integer id, @RequestBody Ingredient ingredient) {
        Ingredient ingredietn = ingredientService.editIngredient(id, ingredient);
        if (ingredietn == null) {
            ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIngredietn(@PathVariable Integer id) {
        boolean ingredient = ingredientService.deleteIngredietn(id);
        return ResponseEntity.ok().build();
    }

}
