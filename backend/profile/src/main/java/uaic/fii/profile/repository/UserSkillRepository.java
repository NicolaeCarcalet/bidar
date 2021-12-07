package uaic.fii.profile.repository;

import org.springframework.data.repository.CrudRepository;
import uaic.fii.profile.entity.UserSkillEntity;

import java.util.List;

public interface UserSkillRepository extends CrudRepository<UserSkillEntity, Long> {

    List<UserSkillEntity> findAllSkillsByUserId(Long userId);
    void deleteSkillByUserIdAndSkillId(Long userId, String skillId);
}
