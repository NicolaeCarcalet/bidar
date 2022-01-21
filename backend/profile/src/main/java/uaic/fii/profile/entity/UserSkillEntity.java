package uaic.fii.profile.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "skills")
@Data
public class UserSkillEntity {

    @Id
    @Column(name = "skill_id", nullable = false)
    private Long skillId;

    @Column(name = "user_id")
    private Long userId;
    private String skill;
}
