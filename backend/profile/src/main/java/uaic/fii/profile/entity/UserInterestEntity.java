package uaic.fii.profile.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "interests")
public class UserInterestEntity {

    @Id
    @GeneratedValue(generator="interestSequence")
    @SequenceGenerator(name="interestSequence",sequenceName="INTEREST_SEQUENCE", allocationSize=1)
    @Column(name = "interest_id", unique = true)
    private Long interestId;

    @Column(name = "user_id")
    private Long userId;
    private String interest;
}
