package uaic.fii.profile.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "profile")
public class UserProfileEntity {

    @Id
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;
    private String country;
    private Integer age;
}
