package skypro.webrecipe.services.Impl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;
import skypro.webrecipe.model.Recipe;
import skypro.webrecipe.services.RecipeFileService;

import javax.annotation.PostConstruct;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class RecipeDataService implements RecipeFileService {
    @Value("${pathToDataFile}")
    private String dataFilePath;

    @Value("${nameOfDataRecipe}")
    private String dataFileName;

    private Path path;

    @PostConstruct
    private void init() {
        path = Path.of(dataFilePath, dataFileName);
    }

    @Override
    public InputStreamResource createTxtData(Map<Integer, Recipe> recipeMap) throws IOException {
        Path path = this.createFile("allRecipeList");
        for (Recipe recipe : recipeMap.values())
            try (BufferedWriter writer = Files.newBufferedWriter(path, StandardOpenOption.APPEND)) {
                writer.append("Название рецепта");
                writer.append(recipe.getName());
                writer.append("\n время приготовления");
                writer.append(String.valueOf(recipe.getCookingTime()));
                writer.append("");
                writer.append("\n ингредиены");
                writer.append(String.valueOf(recipe.getIngredients()));
                writer.append(String.valueOf(recipe.getSteps()));
            }
        File file = path.toFile();
        return new InputStreamResource(new FileInputStream(file));
    }

    @Override
    public Path createFile(String suffix) throws IOException {
        if (Files.exists(Path.of(dataFilePath, suffix))) {
            Files.delete(Path.of(dataFilePath, suffix));
            Files.createFile(Path.of(dataFilePath, suffix));
            return Path.of(dataFilePath, suffix);
        }
        return Files.createFile(Path.of(dataFilePath, suffix));
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


    public Path getPath() {
        return path;
    }
}
