package skypro.webrecipe.model;

import lombok.Data;

@Data
public class Ingredient {
    private String name;

    private Integer count;
    private int unitOfMeasurement;
}
