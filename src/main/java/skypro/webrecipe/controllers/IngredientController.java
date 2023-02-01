package skypro.webrecipe.controllers;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import skypro.webrecipe.model.Ingredient;
import skypro.webrecipe.services.IngredientService;


@RestController
@RequestMapping("/ingredient")
@RequiredArgsConstructor
@Tag(name = "Ингредиенты", description = "CRUD операции для работы с ингредиентами")
public class IngredientController {
    private final IngredientService ingredientService;

    @GetMapping("/{id}")
    @Operation(
            description = "Поиск ингридиента по ID"
    )
    @Parameters(value = {@Parameter(name = "ID", example = "1")})
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
    public ResponseEntity<Ingredient> addIngredietn( @RequestBody Ingredient ingredient) {

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
