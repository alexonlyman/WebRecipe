package skypro.webrecipe.services;

import skypro.webrecipe.model.Recipe;

public interface RecipeServise {
    Recipe addRecipe(Recipe recipe);

    Recipe getRecipe(Integer id);

    void getAllRecipes();

    Recipe editRecipe(Integer id, Recipe recipe);

    boolean deleteRecipe(Integer id);
}
