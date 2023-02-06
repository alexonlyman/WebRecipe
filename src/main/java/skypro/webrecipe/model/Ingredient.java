package skypro.webrecipe.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Ingredient {
    private String name;
    private Integer count;
    private String unitOfMeasurement;
}
