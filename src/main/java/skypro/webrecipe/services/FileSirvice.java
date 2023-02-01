package skypro.webrecipe.services;

public interface FileSirvice {
    boolean saveToFile(String json);

    String readFromFile();

    boolean cleanDataFIle();
}
