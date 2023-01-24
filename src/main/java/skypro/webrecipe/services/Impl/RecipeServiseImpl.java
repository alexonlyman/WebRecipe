package skypro.webrecipe.services.Impl;

import org.springframework.stereotype.Service;
import skypro.webrecipe.model.Recipe;
import skypro.webrecipe.services.RecipeServise;

import java.util.HashMap;
import java.util.Map;
@Service
public class RecipeServiseImpl implements RecipeServise {
    private final Map<Integer, Recipe> recipeMap = new HashMap<>();
    private static Integer id = 0;

    @Override
    public Recipe addRecipe(Recipe recipe) {
       return recipeMap.put(id++, recipe);
    }
    @Override
    public Recipe getRecipe(Integer id) {
           return recipeMap.get(id);
    }

}
