package skypro.webrecipe.services.Impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import skypro.webrecipe.services.FileSirvice;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class FileServiceImpl implements FileSirvice {
    @Value("${pathToDataFile}")
    private String dataFIlePath;

    @Value("${nameOfDataFile}")
    private String dataFIleName;

    @Override
    public boolean saveToFile(String json) {

        try {
            cleanDataFIle();
            Files.writeString(Path.of(dataFIlePath, dataFIleName), json);
            return true;
        } catch (IOException e) {
            e.getStackTrace();
            return false;
        }
    }

    @Override
    public String readFromFile() {
        try {
            return Files.readString(Path.of(dataFIlePath, dataFIleName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean cleanDataFIle() {
        try {
            Files.deleteIfExists(Path.of(dataFIlePath, dataFIleName));
            Files.createFile(Path.of(dataFIlePath, dataFIleName));
            return true;
        } catch (IOException e) {
            e.getStackTrace();
            return false;
        }
    }


}
