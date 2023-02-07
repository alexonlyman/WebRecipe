package skypro.webrecipe.services;

import skypro.webrecipe.model.Recipe;

import java.io.File;

public interface RecipeServise {


    Recipe addRecipe(Recipe recipe);

    Recipe getRecipe(Integer id);

    void getAllRecipes();

    Recipe editRecipe(Integer id, Recipe recipe);

    boolean deleteRecipe(Integer id);
}
