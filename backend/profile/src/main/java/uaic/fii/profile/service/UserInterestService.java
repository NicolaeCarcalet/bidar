package uaic.fii.profile.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uaic.fii.profile.entity.UserInterestEntity;
import uaic.fii.profile.entity.UserSkillEntity;
import uaic.fii.profile.mapper.UserInterestMapper;
import uaic.fii.profile.model.UserInterestDto;
import uaic.fii.profile.repository.UserInterestRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserInterestService {

    @Autowired
    private UserInterestRepository userInterestRepository;

    @Autowired
    private UserInterestMapper userInterestMapper;

    public List<UserInterestDto> getUserInterest(Long userId) {
        List<UserInterestEntity> userInterests = userInterestRepository.findAllInterestByUserId(userId);
        return userInterests.stream().map(userInterestMapper::mapUserInterestEntityToDto).collect(Collectors.toList());
    }

    public List<UserInterestDto> getAllInterests() {
        Iterable<UserInterestEntity> userInterests = userInterestRepository.findAll();
        List<UserInterestDto> userInterestDtos = new ArrayList<>();
        for (UserInterestEntity userInterestEntity : userInterests) {
            userInterestDtos.add(userInterestMapper.mapUserInterestEntityToDto(userInterestEntity));
        }
        return userInterestDtos;
    }

    public void createUserInterest(Long userId, UserInterestDto userInterestDto) {
        UserInterestEntity userInterestEntity = userInterestMapper.mapUserInterestDtoToEntity(userId, userInterestDto);
        userInterestRepository.save(userInterestEntity);
    }

    public void deleteUserInterest(Long userId, Long interestId) {
        userInterestRepository.deleteAllByUserIdAndInterestId(userId, interestId);
    }
}
