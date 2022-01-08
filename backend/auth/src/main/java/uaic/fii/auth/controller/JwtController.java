package uaic.fii.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import uaic.fii.auth.model.UserCredentials;
import uaic.fii.auth.service.JwtService;

@CrossOrigin
@RestController
public class JwtController {

    @Autowired
    private JwtService jwtService;

    @PostMapping(value = "/auth", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
    @ResponseStatus(code = HttpStatus.OK)
    public String getJwtToken(@RequestBody UserCredentials userCredentials) {
        return jwtService.generateJwt(userCredentials);
    }

}

