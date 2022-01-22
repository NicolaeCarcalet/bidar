package uaic.fii.recommendation.mapper;

import org.springframework.stereotype.Component;
import uaic.fii.recommendation.model.RecommendationOutputDto;
import uaic.fii.recommendation.model.ResourceDto;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class RecommendationResourceMapper {

    private static final String RESOURCE_OBJECT_KEY = "s";
    private static final String RESOURCE_ABSTRACT_KEY = "abstract";
    private static final String RESOURCE_LABEL_KEY = "label";


    public Set<RecommendationOutputDto> flattenRecommendations(List<List<RecommendationOutputDto>> currentResourcesSeeAlsoRecommendations) {
        List<RecommendationOutputDto> flattenCurrentResourceSeeAlsoRecommendations = currentResourcesSeeAlsoRecommendations.stream().flatMap(List::stream).collect(Collectors.toList());
        return new HashSet<>(flattenCurrentResourceSeeAlsoRecommendations);
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
        recommendationOutputDto.setRecommendationLabel(resourceDto.getResourceMetadata().get(RESOURCE_LABEL_KEY));
        recommendationOutputDto.setRecommendationAbstract(resourceDto.getResourceMetadata().get(RESOURCE_ABSTRACT_KEY));
        return recommendationOutputDto;
    }
}
