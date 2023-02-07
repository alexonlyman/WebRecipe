package skypro.webrecipe.services.Impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import skypro.webrecipe.model.Recipe;
import skypro.webrecipe.services.FileService;
import skypro.webrecipe.services.RecipeServise;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class RecipeServiseImpl implements RecipeServise {
    private final FileService fileService;
    private Map<Integer, Recipe> recipeMap = new HashMap<>();
    private static Integer id = 0;

//    @PostConstruct
//    private void init() {
//        readFromFile();
//    }

    @Override
    public Recipe addRecipe(Recipe recipe) {
        saveToFile();
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
            saveToFile();
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
    private void saveToFile() {
        try {
            String json = new ObjectMapper().writeValueAsString(recipeMap);
            fileService.saveToFile(json);
        } catch (JsonProcessingException e) {
            e.getStackTrace();
        }

    }

    private void readFromFile() {
        String json = fileService.readFromFile();
        try {
            recipeMap = new ObjectMapper().readValue(json, new TypeReference<HashMap<Integer, Recipe>>() {

            });
        } catch (JsonProcessingException e) {
            e.getStackTrace();
        }
    }

}
