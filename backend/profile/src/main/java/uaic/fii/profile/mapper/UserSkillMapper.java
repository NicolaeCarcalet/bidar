package uaic.fii.profile.mapper;

import org.springframework.stereotype.Component;
import uaic.fii.profile.entity.UserSkillEntity;
import uaic.fii.profile.model.UserSkillDto;

@Component
public class UserSkillMapper {

    public UserSkillDto mapSkillEntityToDto(UserSkillEntity userSkillEntity) {
        UserSkillDto userSkillDto = new UserSkillDto();
        userSkillDto.setSkillId(String.valueOf(userSkillEntity.getSkillId()));
        userSkillDto.setSkillData(userSkillEntity.getSkill());
        return userSkillDto;
    }

    public UserSkillEntity mapSkillDtoToEntity(Long userId, UserSkillDto userSkillDto) {
        UserSkillEntity userSkillEntity = new UserSkillEntity();
        userSkillEntity.setUserId(userId);
        userSkillEntity.setSkill(userSkillDto.getSkillData());
        return userSkillEntity;
    }
}
