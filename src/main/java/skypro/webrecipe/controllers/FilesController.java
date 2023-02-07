package skypro.webrecipe.controllers;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import skypro.webrecipe.services.FileService;

import java.io.*;

@RestController
@RequestMapping("/files")
public class FilesController {
    private final FileService fileService;

    public FilesController(FileService fileService) {
        this.fileService = fileService;
    }
    @GetMapping("/download")
    public ResponseEntity<InputStreamResource> downloadData() {
        File file = fileService.getDataFile();
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
    @PostMapping(value = "/import", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> uploadDataFile(@RequestParam MultipartFile file) {

        fileService.cleanDataFIle();
        File dataFile = fileService.getDataFile();
        try (FileOutputStream fos = new FileOutputStream(dataFile)) {
            IOUtils.copy(file.getInputStream(), fos);
            return ResponseEntity.ok().build();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
