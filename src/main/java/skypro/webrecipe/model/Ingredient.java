package skypro.webrecipe.model;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class Ingredient {
    @NotBlank(message = " fill in the name")
    private String name;
    @Positive
    private Integer count;
    private int unitOfMeasurement;
}
