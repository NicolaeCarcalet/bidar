package uaic.fii.profile.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "interests")
public class UserInterestEntity {

    @Id
    @Column(name = "interest_id", unique = true)
    private Long interestId;

    @Column(name = "user_id")
    private Long userId;
    private String interest;
}
