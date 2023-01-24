package skypro.webrecipe.services;

import skypro.webrecipe.model.Ingredient;

public interface IngredientService {

    Ingredient addIngredient(Ingredient ingredient);

    Ingredient getIngredietn(Integer id);
}
