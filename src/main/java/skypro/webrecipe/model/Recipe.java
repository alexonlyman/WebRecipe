package skypro.webrecipe.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import java.util.List;

@Data
@NoArgsConstructor
public class Recipe {
    @NotBlank
    private String name;
    @Positive
    private Integer cookingTime;
    @NotEmpty
    private List<Ingredient> ingridients;
    @NotEmpty
    private List<String> steps;


}
