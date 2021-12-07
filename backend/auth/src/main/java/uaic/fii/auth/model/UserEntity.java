package uaic.fii.auth.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "users")
@Data
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "username")
    private String username;

    // TODO: Don't store passwords in clear
    @Column(name = "password")
    private String password;
}
