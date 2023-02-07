package skypro.webrecipe.services;

import java.io.File;

public interface FileService {
    void saveToFile(String json);

    String readFromFile();

    void cleanDataFIle();

    File getDataFile();
}
