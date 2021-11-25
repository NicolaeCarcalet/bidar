package uaic.fii.profile.model;

import lombok.Data;

import java.util.List;

@Data
public class UserProfileDto {
    private String firstName;
    private String lastName;
    private String country;
    private Integer age;
    private List<UserSkillDto> skills;
    private List<UserInterestDto> interests;
    private List<UserProfileDto> friends;
}
