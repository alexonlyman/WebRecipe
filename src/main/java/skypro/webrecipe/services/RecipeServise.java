package skypro.webrecipe.services;

import skypro.webrecipe.model.Recipe;

public interface RecipeServise {
    Recipe addRecipe(Recipe recipe);

    Recipe getRecipe(Integer id);
}
