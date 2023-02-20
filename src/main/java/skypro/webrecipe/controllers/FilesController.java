package skypro.webrecipe.controllers;

import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import skypro.webrecipe.services.Impl.IngredientDataService;
import skypro.webrecipe.services.Impl.RecipeDataService;
import skypro.webrecipe.services.RecipeServise;

import java.io.*;
import java.nio.file.Files;

@RestController
@RequiredArgsConstructor
@RequestMapping("/files")
public class FilesController {
    private final IngredientDataService ingredientDataService;
    private final RecipeDataService recipeDataService;
    private final RecipeServise recipeServise;


    @GetMapping("/export/recipe")
    public ResponseEntity<InputStreamResource> exportData() throws IOException {
        InputStreamResource inputStreamResource = recipeDataService.createTxtData(recipeServise.getAllRecipes());
        return ResponseEntity.ok()
                .contentType(MediaType.TEXT_PLAIN)
                .contentLength(Files.size(recipeDataService.getPath()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename = \"Recipes.txt)\"")
                .body(inputStreamResource);

    }

            @PostMapping(value = "/import/recipe", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
            public ResponseEntity<Void> uploadRecipeFile (@RequestParam MultipartFile file){

                recipeDataService.cleanDataFIle();
                File dataFile = recipeDataService.getDataFile();
                try (FileOutputStream fos = new FileOutputStream(dataFile)) {
                    IOUtils.copy(file.getInputStream(), fos);
                    return ResponseEntity.ok().build();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            @PostMapping(value = "/import/ingredient", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
            public ResponseEntity<Void> uploadIngredientFile (@RequestParam MultipartFile file){

                ingredientDataService.cleanDataFIle();
                File dataFile = ingredientDataService.getDataFile();
                try (FileOutputStream fos = new FileOutputStream(dataFile)) {
                    IOUtils.copy(file.getInputStream(), fos);
                    return ResponseEntity.ok().build();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

}
