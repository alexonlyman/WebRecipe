package skypro.webrecipe.services;

import skypro.webrecipe.model.Ingredient;

public interface IngredientService {

    Ingredient addIngredient(Ingredient ingredient);

    Ingredient getIngredietn(Integer id);

    void getAllIngredients();

    Ingredient editIngredient(Integer id, Ingredient ingredient);

    boolean deleteIngredietn(Integer id);
}
