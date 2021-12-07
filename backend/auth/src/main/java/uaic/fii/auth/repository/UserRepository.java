package uaic.fii.auth.repository;

import org.springframework.data.repository.CrudRepository;
import uaic.fii.auth.model.UserEntity;

import java.util.Optional;

public interface UserRepository extends CrudRepository<UserEntity, Long> {

    Optional<UserEntity> findUserByUsername(String username);
}
