package skypro.webrecipe.services.Impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;
import skypro.webrecipe.model.Recipe;
import skypro.webrecipe.services.FileService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

@Service
public class IngredientDataService implements FileService {
    @Value("${pathToDataFile}")
    private String dataFilePath;

    @Value("${nameOfDataIngredient}")
    private String dataFileName;


    @Override
    public InputStreamResource createRecipeData(Map<Integer, Recipe> recipeMap) throws IOException {
        return null;
    }

    @Override
    public Path createFile(String suffix) {
        return null;
    }

    @Override
    public void saveToFile(String json) {

        try {
            cleanDataFIle();
            Files.writeString(Path.of(dataFilePath, dataFileName), json);
        } catch (IOException e) {
            e.getStackTrace();
        }
    }

    @Override
    public String readFromFile() {
        try {
            return Files.readString(Path.of(dataFilePath, dataFileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void cleanDataFIle() {
        try {
            Files.deleteIfExists(Path.of(dataFilePath, dataFileName));
            Files.createFile(Path.of(dataFilePath, dataFileName));
        } catch (IOException e) {
            e.getStackTrace();
        }
    }
    @Override
    public File getDataFile() {

        return new File(dataFilePath + "/" + dataFileName);
    }


}
