package skypro.webrecipe.services.Impl;

import org.springframework.stereotype.Service;
import skypro.webrecipe.model.Ingredient;
import skypro.webrecipe.services.IngredientService;

import java.util.HashMap;
import java.util.Map;

@Service
public class IngredientServiceImpl implements IngredientService {
    private final Map<Integer, Ingredient> ingredientMap = new HashMap<>();
    private static Integer id = 0;

    @Override
    public Ingredient addIngredient(Ingredient ingredient) {
        return ingredientMap.put(id++, ingredient);
    }

    @Override
    public Ingredient getIngredietn(Integer id) {
        return ingredientMap.get(id);
    }
}
