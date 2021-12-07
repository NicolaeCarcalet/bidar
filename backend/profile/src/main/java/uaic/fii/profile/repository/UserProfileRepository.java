package uaic.fii.profile.repository;

import org.springframework.data.repository.CrudRepository;
import uaic.fii.profile.entity.UserProfileEntity;

public interface UserProfileRepository extends CrudRepository<UserProfileEntity, Long> {
}
