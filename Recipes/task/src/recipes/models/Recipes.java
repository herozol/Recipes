package recipes.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "recipes")
@Table
public class Recipes {
    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recipes_id")
    private long id;
    @Column(name = "name")
    @NotBlank
    private String name;
    @Column(name = "category")
    @NotBlank
    private String category;
    @Column(name = "date")
    @UpdateTimestamp
    private LocalDateTime date;
    @Column(name = "description")
    @NotBlank
    private String description;
    @Size(min = 1)
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "recipes_id")
    private List<Ingredients> ingredients = new LinkedList<>();
    @Size(min = 1)
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "recipes_id")
    private List<Directions> directions = new LinkedList<>();
    @JsonIgnore
    @Column(name = "email")
    private String email;

    public String outputId() {
        return " { \"id\": " + this.getId() + "}";
    }
}
