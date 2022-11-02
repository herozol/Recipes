package recipes.models;

import com.fasterxml.jackson.annotation.*;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "directions")
@Table
public class Directions {
    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "directions_id")
    private long id;
    @NotBlank
    @Column(name = "directions")
    private String directions;

    @JsonValue
    public String getDirections() {
        return directions;
    }

    public Directions(String directions) {
        this.directions = directions;
    }
}
