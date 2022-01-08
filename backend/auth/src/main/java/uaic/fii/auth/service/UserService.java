package uaic.fii.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uaic.fii.auth.model.UserEntity;
import uaic.fii.auth.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void register(UserEntity user)
    {
        userRepository.save(user);
    }

    public Boolean passwordsMatched(UserEntity user){
        if(userRepository.findUserByUsername(user.getUsername()).isPresent()){
            UserEntity oldUser = userRepository.findUserByUsername(user.getUsername()).get();
            if(oldUser.getPassword().equals(user.getPassword())){
                return true;
            }
            else{
                return false;
            }
        }
        else{
            return false;
        }
    }
}
