package uaic.fii.profile.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uaic.fii.profile.entity.UserProfileEntity;
import uaic.fii.profile.mapper.UserProfileMapper;
import uaic.fii.profile.model.UserInterestDto;
import uaic.fii.profile.model.UserProfileDto;
import uaic.fii.profile.model.UserSkillDto;
import uaic.fii.profile.repository.UserProfileRepository;

import javax.validation.ValidationException;
import java.util.List;
import java.util.Optional;

@Service
public class UserProfileService {

    @Autowired
    private UserProfileMapper userProfileMapper;

    @Autowired
    private UserSkillService userSkillService;

    @Autowired
    private UserInterestService userInterestService;

    @Autowired
    private UserProfileRepository userProfileRepository;

    public UserProfileDto getProfileForUser(Long userId) {
        Optional<UserProfileEntity> userProfileEntityOptional = userProfileRepository.findById(userId);
        if (userProfileEntityOptional.isEmpty()) {
            throw new ValidationException("Couldn't find user");
        }
        List<UserSkillDto> userSkills = userSkillService.getSkillsForUser(userId);
        List<UserInterestDto> userInterest = userInterestService.getUserInterest(userId);
        UserProfileEntity userProfileEntity = userProfileEntityOptional.get();
        return userProfileMapper.mapUserProfileEntityToDto(userProfileEntity, userSkills, userInterest);
    }

    public UserProfileDto createUserProfile(UserProfileDto userProfileDto) {
        UserProfileEntity userProfileEntity = userProfileMapper.mapUserProfileDtoToEntity(userProfileDto);
        userProfileRepository.save(userProfileEntity);
        return userProfileDto;
    }

    public void updateUserProfile(Long userId, UserProfileDto newProfileData) {
        Optional<UserProfileEntity> userProfile = userProfileRepository.findById(userId);
        if (userProfile.isEmpty()) {
            throw new ValidationException("Couldn't find user by id");
        }
        userProfileMapper.updateProfile(userProfile.get(), newProfileData);
    }
}
