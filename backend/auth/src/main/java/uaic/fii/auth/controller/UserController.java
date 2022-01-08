package uaic.fii.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uaic.fii.auth.model.UserEntity;
import uaic.fii.auth.service.UserService;
import javax.validation.Valid;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
    @ResponseBody
    private ResponseEntity<String> registerUser(@Valid @RequestBody UserEntity user)
    {
        if(user.getUsername().length() < 1 || user.getUsername().matches(".*\\s+.*")){
            return new ResponseEntity< >("Username is mandatory", HttpStatus.FORBIDDEN);
        }
        if(user.getPassword().length() < 1 || user.getPassword().matches(".*\\s+.*")){
            return new ResponseEntity< >("Password is mandatory", HttpStatus.FORBIDDEN);
        }
        if(!userService.passwordsMatched(user)){
            userService.register(user);
            return new ResponseEntity< >("User created", HttpStatus.CREATED);
        }
        else{
            return new ResponseEntity< >("User already exists", HttpStatus.CONFLICT);
        }
    }
}
