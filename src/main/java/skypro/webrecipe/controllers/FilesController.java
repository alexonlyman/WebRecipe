package skypro.webrecipe.controllers;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import skypro.webrecipe.services.Impl.IngredientDataService;
import skypro.webrecipe.services.Impl.RecipeDataService;

import java.io.*;

@RestController
@RequestMapping("/files")
public class FilesController {
    private final IngredientDataService ingredientDataService;
    private final RecipeDataService recipeDataService;

    public FilesController(IngredientDataService ingredientDataService, RecipeDataService recipeDataService) {
        this.ingredientDataService = ingredientDataService;
        this.recipeDataService = recipeDataService;
    }

    @GetMapping("/download/recipe")
    public ResponseEntity<InputStreamResource> downloadData() {
        File file = ingredientDataService.getDataFile();
        if (file.exists()) {
            try {
                InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
                return ResponseEntity.ok()
                        .contentLength(file.length())
                        .contentType(MediaType.APPLICATION_JSON)
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename = \"Recipe.json\"")
                        .body(resource);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping(value = "/importrecipe", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> uploadRecipeFile(@RequestParam MultipartFile file) {

        recipeDataService.cleanDataFIle();
        File dataFile = recipeDataService.getDataFile();
        try (FileOutputStream fos = new FileOutputStream(dataFile)) {
            IOUtils.copy(file.getInputStream(), fos);
            return ResponseEntity.ok().build();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @PostMapping(value = "/importingredient", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> uploadIngredientFile(@RequestParam MultipartFile file) {

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
