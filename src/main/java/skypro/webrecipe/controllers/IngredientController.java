package skypro.webrecipe.controllers;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import skypro.webrecipe.model.Ingredient;
import skypro.webrecipe.services.IngredientService;


@RestController
@RequestMapping("/ingredient")
@RequiredArgsConstructor
public class IngredientController {
    private final IngredientService ingredientService;
@GetMapping("/id")
    Ingredient getIngredietn(@PathVariable Integer id) {
    return ingredientService.getIngredietn(id);
    }

    @PostMapping
    Ingredient addIngredietn(@Valid @RequestBody Ingredient ingredient) {
        return ingredientService.addIngredient(ingredient);
    }
}
