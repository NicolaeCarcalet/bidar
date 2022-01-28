package uaic.fii.profile.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "profile")
public class UserProfileEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", unique = true)
    private Long userId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;
    private String country;
    private Integer age;
}
