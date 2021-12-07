package uaic.fii.profile.mapper;

import org.springframework.stereotype.Component;
import uaic.fii.profile.entity.UserInterestEntity;
import uaic.fii.profile.model.UserInterestDto;

@Component
public class UserInterestMapper {

    public UserInterestDto mapUserInterestEntityToDto(UserInterestEntity userInterestEntity) {
        UserInterestDto userInterestDto = new UserInterestDto();
        userInterestDto.setInterestId(userInterestEntity.getInterestId());
        userInterestDto.setInterestData(userInterestEntity.getInterest());
        return userInterestDto;
    }

    public UserInterestEntity mapUserInterestDtoToEntity(Long userId, UserInterestDto userInterestDto) {
        UserInterestEntity userInterestEntity = new UserInterestEntity();
        userInterestEntity.setUserId(userId);
        userInterestEntity.setInterestId(userInterestEntity.getInterestId());
        userInterestEntity.setInterest(userInterestDto.getInterestData());
        return userInterestEntity;
    }
}
