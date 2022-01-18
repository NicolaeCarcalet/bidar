package uaic.fii.recommendation.mapper;

import org.springframework.stereotype.Component;
import uaic.fii.recommendation.model.RecommendationOutputDto;
import uaic.fii.recommendation.model.ResourceDto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class RecommendationResourceMapper {

    public static final String RESOURCE_OBJECT_KEY = "object";


    public Set<RecommendationOutputDto> mergeRecommendations(List<List<RecommendationOutputDto>> currentResourcesSeeAlsoRecommendations,
                                                             List<List<RecommendationOutputDto>> userInterestsSeeAlsoRecommendations,
                                                             List<List<RecommendationOutputDto>> userSkillsSeeAlsoRecommendations) {
        Set<RecommendationOutputDto> seeAlsoRecommendations = new HashSet<>();
        List<RecommendationOutputDto> flattenCurrentResourceSeeAlsoRecommendations = currentResourcesSeeAlsoRecommendations.stream().flatMap(List::stream).collect(Collectors.toList());
        List<RecommendationOutputDto> flattenUserInterestsSeeAlsoRecommendations = userInterestsSeeAlsoRecommendations.stream().flatMap(List::stream).collect(Collectors.toList());
        List<RecommendationOutputDto> flattenUserSkillsSeeAlsoRecommendations = userSkillsSeeAlsoRecommendations.stream().flatMap(List::stream).collect(Collectors.toList());
        seeAlsoRecommendations.addAll(flattenCurrentResourceSeeAlsoRecommendations);
        seeAlsoRecommendations.addAll(flattenUserInterestsSeeAlsoRecommendations);
        seeAlsoRecommendations.addAll(flattenUserSkillsSeeAlsoRecommendations);
        return seeAlsoRecommendations;
    }

    public List<RecommendationOutputDto> mapToRecommendationOutputDtos(List<ResourceDto> resourceDto) {
        return resourceDto
                .stream()
                .map(this::mapToRecommendationOutputDto)
                .collect(Collectors.toList());
    }

    public RecommendationOutputDto mapToRecommendationOutputDto(ResourceDto resourceDto) {
        RecommendationOutputDto recommendationOutputDto = new RecommendationOutputDto();
        recommendationOutputDto.setRecommendationResource(resourceDto.getResourceMetadata().get(RESOURCE_OBJECT_KEY));
        return recommendationOutputDto;
    }
}
