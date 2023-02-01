package skypro.webrecipe.services.Impl;

import org.springframework.stereotype.Service;
import skypro.webrecipe.model.Recipe;
import skypro.webrecipe.services.RecipeServise;

import java.util.Collection;
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
    @Override
    public void getAllRecipes() {
        Collection<Recipe> values = recipeMap.values();
        for (Recipe recipe : values) {
            System.out.println("Список игредиентов " + recipe);
        }
    }
    @Override
    public Recipe editRecipe(Integer id, Recipe recipe) {
        if (recipeMap.containsKey(id)) {
            recipeMap.put(id, recipe);
            return recipe;
        }
        return null;
    }
    @Override
    public boolean deleteRecipe(Integer id) {
        if (recipeMap.containsKey(id)) {
            recipeMap.remove(id);
            return true;
        }
        return false;

    }

}
