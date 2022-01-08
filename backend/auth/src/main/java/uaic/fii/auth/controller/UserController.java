package uaic.fii.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uaic.fii.auth.model.UserEntity;
import uaic.fii.auth.service.UserService;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
    @ResponseBody
    private ResponseEntity saveStudent(@RequestBody UserEntity user)
    {
        if(user.getPassword().length()<1){
            return new ResponseEntity("Invalid password",HttpStatus.FORBIDDEN);
        }
        if(!userService.passwordsMatched(user)){
            userService.register(user);
            return new ResponseEntity("User created",HttpStatus.CREATED);
        }
        else{
            return new ResponseEntity("User already exists",HttpStatus.CONFLICT);
        }
    }

    //testing purpose
    @GetMapping("/get/{id}")
    private UserEntity getStudent(@PathVariable("id") int id)
    {
        return userService.getById(id);
    }
}
