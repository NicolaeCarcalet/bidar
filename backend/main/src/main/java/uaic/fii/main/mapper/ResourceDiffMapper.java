package uaic.fii.main.mapper;

import org.springframework.stereotype.Component;
import uaic.fii.main.model.ResourceDiffDto;

import java.util.List;

@Component
public class ResourceDiffMapper {

    public ResourceDiffDto mapDiffToDto(List<String> uniqueKeys, List<String> uniqueValues) {
       return ResourceDiffDto
               .builder()
               .uniqueKeys(uniqueKeys)
               .uniqueValues(uniqueValues)
               .build();
    }
}
