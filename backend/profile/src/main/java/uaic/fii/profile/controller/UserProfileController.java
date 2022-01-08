package uaic.fii.profile.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uaic.fii.profile.model.UserProfileDto;
import uaic.fii.profile.service.UserProfileService;

@CrossOrigin
@RestController
public class UserProfileController {

    @Autowired
    private UserProfileService userProfileService;

    @GetMapping("/profile/{userId}")
    @Operation(description = "This will return all the user's profile data")
    @Parameter(in = ParameterIn.HEADER,
            description = "The Authorization header is mandatory and its valid must be provide JWT token in the form: Authorization: Bearer + <token>",
            name = "Authorization",
            required = true,
            content = @Content(schema = @Schema(type = "string")))
    public UserProfileDto getUserProfile(@PathVariable Long userId) {
        return userProfileService.getProfileForUser(userId);
    }

    @PostMapping("/profile")
    public UserProfileDto createProfile(@RequestBody UserProfileDto userProfileDto) {
        return userProfileService.createUserProfile(userProfileDto);
    }

    @PutMapping("/profile/{userId}")
    @Operation(description = "This will update an user's profile")
    @Parameter(in = ParameterIn.HEADER,
            description = "The Authorization header is mandatory and its valid must be provide JWT token in the form: Authorization: Bearer + <token>",
            name = "Authorization",
            required = true,
            content = @Content(schema = @Schema(type = "string")))
    public void updateUserProfile(@PathVariable Long userId, @RequestBody UserProfileDto newProfileData) {
        userProfileService.updateUserProfile(userId, newProfileData);
    }
}
