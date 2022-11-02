package recipes.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "ingredients")
@Table
public class Ingredients {
    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ingredients_id")
    private long id;
    @NotBlank
    @Column(name = "ingredients")
    private String ingredients;

    @JsonValue
    public String getIngredients() {
        return ingredients;
    }

    public Ingredients(String ingredients) {
        this.ingredients = ingredients;
    }
}
