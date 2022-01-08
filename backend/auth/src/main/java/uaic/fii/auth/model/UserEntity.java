package uaic.fii.auth.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "users")
@Data
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "username")
    @NotEmpty
    @NotBlank(message = "Username is mandatory")
    @Size(min=1, max=20)
    private String username;

    @Column(name = "password")
    @NotEmpty
    @NotBlank(message = "Password is mandatory")
    @Size(min=1, max=20)
    private String password;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

}
