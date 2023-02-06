package skypro.webrecipe.services;

import java.io.File;

public interface FileSirvice {
    void saveToFile(String json);

    String readFromFile();

    void cleanDataFIle();

    File getDataFile();
}
