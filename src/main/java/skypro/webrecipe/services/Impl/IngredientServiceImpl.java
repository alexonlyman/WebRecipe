package skypro.webrecipe.services.Impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import skypro.webrecipe.model.Ingredient;
import skypro.webrecipe.services.IngredientService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class IngredientServiceImpl implements IngredientService {
    private final IngredientDataService ingredientDataService;
    private Map<Integer, Ingredient> ingredientMap = new HashMap<>();
    private static Integer id = 0;



    @Override
    public Ingredient addIngredient(Ingredient ingredient) {
        saveToFile();
        return ingredientMap.put(id++, ingredient);
    }

    @Override
    public Ingredient getIngredietn(Integer id) {
        return ingredientMap.get(id);
    }

    @Override
    public void getAllIngredients() {
        Collection<Ingredient> values = ingredientMap.values();
        for (Ingredient ingredient : values) {
            System.out.println("Список игредиентов " + ingredient);
        }
    }

    @Override
    public Ingredient editIngredient(Integer id, Ingredient ingredient) {
        if (ingredientMap.containsKey(id)) {
            ingredientMap.put(id, ingredient);
            saveToFile();
            return ingredient;
        }
        return null;
    }

    @Override
    public boolean deleteIngredietn(Integer id) {
        if (ingredientMap.containsKey(id)) {
            ingredientMap.remove(id);
            return true;
        }
        return false;

    }

    private void saveToFile() {
        try {
            String json = new ObjectMapper().writeValueAsString(ingredientMap);
            ingredientDataService.saveToFile(json);
        } catch (JsonProcessingException e) {
            e.getStackTrace();
        }

    }

    private void readFile() {
        String json = ingredientDataService.readFromFile();
        try {
            ingredientMap = new ObjectMapper().readValue(json, new TypeReference<HashMap<Integer, Ingredient>>() {
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
