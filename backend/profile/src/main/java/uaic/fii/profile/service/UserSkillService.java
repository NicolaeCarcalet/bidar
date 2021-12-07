package uaic.fii.profile.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uaic.fii.profile.entity.UserSkillEntity;
import uaic.fii.profile.mapper.UserSkillMapper;
import uaic.fii.profile.model.UserSkillDto;
import uaic.fii.profile.repository.UserSkillRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserSkillService {

    @Autowired
    private UserSkillRepository userSkillRepository;

    @Autowired
    private UserSkillMapper userSkillMapper;

    public List<UserSkillDto> findAll() {
        Iterable<UserSkillEntity> allUserSkillEntities = userSkillRepository.findAll();
        List<UserSkillDto> userSkillDtos = new ArrayList<>();
        for (UserSkillEntity userSkillEntity : allUserSkillEntities) {
            userSkillDtos.add(userSkillMapper.mapSkillEntityToDto(userSkillEntity));
        }
        return userSkillDtos;
    }

    public List<UserSkillDto> getSkillsForUser(Long userId) {
        List<UserSkillEntity> userSkillEntities = userSkillRepository.findAllSkillsByUserId(userId);
        return userSkillEntities.stream().map(userSkillMapper::mapSkillEntityToDto).collect(Collectors.toList());
    }

    public void createSkill(Long userId, UserSkillDto userSkillDto) {
        UserSkillEntity userSkillEntity = userSkillMapper.mapSkillDtoToEntity(userId, userSkillDto);
        userSkillRepository.save(userSkillEntity);
    }

    public void deleteSkill(Long userId, String skillId) {
        userSkillRepository.deleteSkillByUserIdAndSkillId(userId, skillId);
    }
}
