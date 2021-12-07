package uaic.fii.profile.repository;

import org.springframework.data.repository.CrudRepository;
import uaic.fii.profile.entity.UserInterestEntity;

import java.util.List;

public interface UserInterestRepository extends CrudRepository<UserInterestEntity, Long> {

    List<UserInterestEntity> findAllInterestByUserId(Long userId);
    void deleteAllByUserIdAndInterestId(Long userId, Long interestId);
}
