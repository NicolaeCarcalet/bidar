package uaic.fii.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uaic.fii.auth.model.UserCredentials;
import uaic.fii.auth.model.UserEntity;
import uaic.fii.auth.repository.UserRepository;

import javax.validation.ValidationException;
import java.util.Optional;

@Service
public class JwtService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    JwtGenerator jwtGenerator;

    public String generateJwt(UserCredentials userCredentials) {
        if (!isValidUser(userCredentials)) {
            throw new ValidationException("User not found");
        }
        return jwtGenerator.generateToken();
    }

    private boolean isValidUser(UserCredentials userCredentials) {
        Optional<UserEntity> userEntityOptional = userRepository.findUserByUsername(userCredentials.getUsername());
        if (!userEntityOptional.isPresent()) {
            return false;
        }
        UserEntity userEntity = userEntityOptional.get();
        return userEntity.getUsername().equals(userCredentials.getUsername()) &&
                userEntity.getPassword().equals(userCredentials.getPassword());
    }
}
