package uaic.fii.main.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uaic.fii.main.mapper.ResourceDiffMapper;
import uaic.fii.main.model.ResourceDiffDto;
import uaic.fii.main.model.ResourceDto;

import java.util.*;
import java.util.function.Function;

@Service
public class ResourceDiffService {

    @Autowired
    private ResourceDiffMapper resourceDiffMapper;

    public ResourceDiffDto getResourcesDiff(List<ResourceDto> resourceDtos) {
        Map<String, Integer> resourcesKeysLookup = getResourcesKeysLookup(resourceDtos, resourceDto -> resourceDto.getResourceMetadata().keySet());
        Map<String, Integer> resourcesValuesLookup = getResourcesValuesLookup(resourceDtos);
        List<String> uniqueKeys = getUniqueKeys(resourcesKeysLookup);
        List<String> uniqueValues = getUniqueKeys(resourcesValuesLookup);
        return resourceDiffMapper.mapDiffToDto(uniqueKeys, uniqueValues);
    }

    private List<String> getUniqueKeys(Map<String, Integer> lookup) {
        List<String> uniqueValues = new ArrayList<>();
        for (Map.Entry<String, Integer> resourceEntry : lookup.entrySet()) {
            if (resourceEntry.getValue() == 1) {
                uniqueValues.add(resourceEntry.getKey());
            }
        }
        return uniqueValues;
    }

    private Map<String, Integer> getResourcesValuesLookup(List<ResourceDto> resourceDtos) {
        Map<String, Integer> resourceDiffKeyLookup = new HashMap<>();

        for (ResourceDto resourceDto : resourceDtos) {
            Set<String> resourceKeys = resourceDto.getResourceMetadata().keySet();
            for (String resourceKey : resourceKeys) {
                String resourceValue = resourceDto.getResourceMetadata().get(resourceKey);
                if (resourceValue != null) {
                    if (!resourceDiffKeyLookup.containsKey(resourceValue)) {
                        resourceDiffKeyLookup.put(resourceValue, 0);
                    }
                    resourceDiffKeyLookup.put(resourceValue, resourceDiffKeyLookup.get(resourceValue) + 1);
                }
            }
        }

        return resourceDiffKeyLookup;
    }

    private Map<String, Integer> getResourcesKeysLookup(List<ResourceDto> resourceDtos, Function<ResourceDto, Set<String>> resourceFunction) {
        Map<String, Integer> resourceDiffKeyLookup = new HashMap<>();

        for (ResourceDto resourceDto : resourceDtos) {
            Set<String> resourceKeys = resourceFunction.apply(resourceDto);
            for (String resourceKey : resourceKeys) {
                if (!resourceDiffKeyLookup.containsKey(resourceKey)) {
                    resourceDiffKeyLookup.put(resourceKey, 0);
                }
                resourceDiffKeyLookup.put(resourceKey, resourceDiffKeyLookup.get(resourceKey) + 1);
            }
        }

        return resourceDiffKeyLookup;
    }
}
