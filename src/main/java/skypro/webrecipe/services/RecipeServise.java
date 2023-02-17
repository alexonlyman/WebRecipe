package skypro.webrecipe.services;

import skypro.webrecipe.model.Recipe;

import java.util.Map;

public interface RecipeServise {


    Recipe addRecipe(Recipe recipe);

    Recipe getRecipe(Integer id);


    Map<Integer, Recipe> getRecipeMap();

    Recipe editRecipe(Integer id, Recipe recipe);

    boolean deleteRecipe(Integer id);
}
