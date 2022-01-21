package uaic.fii.profile.mapper;

import org.springframework.stereotype.Component;
import uaic.fii.profile.entity.UserProfileEntity;
import uaic.fii.profile.model.UserInterestDto;
import uaic.fii.profile.model.UserProfileDto;
import uaic.fii.profile.model.UserSkillDto;

import java.util.List;

@Component
public class UserProfileMapper {

    public UserProfileDto mapUserProfileEntityToDto(UserProfileEntity userProfileEntity, List<UserSkillDto> userSkills, List<UserInterestDto> userInterests) {
        UserProfileDto userProfileDto = new UserProfileDto();
        userProfileDto.setUserId(userProfileEntity.getUserId());
        userProfileDto.setFirstName(userProfileEntity.getFirstName());
        userProfileDto.setLastName(userProfileEntity.getLastName());
        userProfileDto.setCountry(userProfileEntity.getCountry());
        userProfileDto.setAge(userProfileEntity.getAge());
        userProfileDto.setSkills(userSkills);
        userProfileDto.setInterests(userInterests);
        return userProfileDto;
    }

    public UserProfileEntity mapUserProfileDtoToEntity(UserProfileDto userProfileDto) {
        UserProfileEntity userProfileEntity = new UserProfileEntity();
        userProfileEntity.setUserId(userProfileDto.getUserId());
        userProfileEntity.setFirstName(userProfileDto.getFirstName());
        userProfileEntity.setLastName(userProfileDto.getLastName());
        userProfileEntity.setAge(userProfileDto.getAge());
        userProfileEntity.setCountry(userProfileDto.getCountry());
        return userProfileEntity;
    }

    public void updateProfile(UserProfileEntity userProfileEntity, UserProfileDto userProfileDto) {
        userProfileEntity.setFirstName(userProfileDto.getFirstName());
        userProfileEntity.setLastName(userProfileDto.getLastName());
        userProfileEntity.setAge(userProfileDto.getAge());
        userProfileEntity.setCountry(userProfileDto.getCountry());
    }
}
