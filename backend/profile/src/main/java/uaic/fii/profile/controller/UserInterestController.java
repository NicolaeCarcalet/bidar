package uaic.fii.profile.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uaic.fii.profile.model.UserInterestDto;
import uaic.fii.profile.service.UserInterestService;

import java.util.List;

@CrossOrigin
@RestController
public class UserInterestController {

    @Autowired
    private UserInterestService userInterestService;

    @GetMapping("/profile/interest/{userId}")
    @Operation(description = "This will return all the interest for an user")
    @Parameter(in = ParameterIn.HEADER,
            description = "The Authorization header is mandatory and its valid must be provide JWT token in the form: Authorization: Bearer + <token>",
            name = "Authorization",
            required = true,
            content = @Content(schema = @Schema(type = "string")))
    public List<UserInterestDto> getUserInterests(@PathVariable Long userId) {
        return userInterestService.getUserInterest(userId);
    }

    @GetMapping("/profile/interest")
    @Operation(description = "This will return all the available interest")
    @Parameter(in = ParameterIn.HEADER,
            description = "The Authorization header is mandatory and its valid must be provide JWT token in the form: Authorization: Bearer + <token>",
            name = "Authorization",
            required = true,
            content = @Content(schema = @Schema(type = "string")))
    public List<UserInterestDto> getAllInterests() {
        return userInterestService.getAllInterests();
    }

    @PostMapping("/profile/interest/{userId}")
    @Operation(description = "Add an interest for an user",
            parameters = {@Parameter(name = "interestId", in = ParameterIn.PATH, required = true, description = "The interest id that will be added")})
    @Parameter(in = ParameterIn.HEADER,
            description = "The Authorization header is mandatory and its valid must be provide JWT token in the form: Authorization: Bearer + <token>",
            name = "Authorization",
            required = true,
            content = @Content(schema = @Schema(type = "string")))
    public UserInterestDto addUserInterests(@PathVariable Long userId, @RequestBody UserInterestDto userInterestDto) {
        return userInterestService.createUserInterest(userId, userInterestDto);
    }

    @DeleteMapping("/profile/interest/{userId}/{interestId}")
    @Operation(description = "Deletes an interest assigned to an user",
            parameters = {@Parameter(name = "interestId", in = ParameterIn.PATH, required = true, description = "The interest id that will be deleted"),
                    @Parameter(name = "userId", in = ParameterIn.PATH, required = true, description = "The id of the user")})
    @Parameter(in = ParameterIn.HEADER,
            description = "The Authorization header is mandatory and its valid must be provide JWT token in the form: Authorization: Bearer + <token>",
            name = "Authorization",
            required = true,
            content = @Content(schema = @Schema(type = "string")))
    public void deleteUserInterests(@PathVariable Long userId, @PathVariable Long interestId) {
        userInterestService.deleteUserInterest(userId, interestId);
    }
}
