package skypro.webrecipe.services;

import org.springframework.core.io.InputStreamResource;
import skypro.webrecipe.model.Recipe;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;

public interface FileService {


    InputStreamResource createRecipeData(Map<Integer, Recipe> recipeMap) throws IOException;

    Path createFile(String suffix) throws IOException;

    void saveToFile(String json);

    String readFromFile();

    void cleanDataFIle();

    File getDataFile();
}
