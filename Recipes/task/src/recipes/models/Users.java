package recipes.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name = "users")
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"user_email"}))
public class Users {
    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private long id;
    @Column(name = "user_email")
    @NotBlank
    @Email(regexp = ".+[@].+[\\.].+")
    private String email;
    @Column(name = "user_password")
    @NotBlank
    @Size(min = 8)
    private String password;
    @Column(name = "user_role")
    private String role;
}
