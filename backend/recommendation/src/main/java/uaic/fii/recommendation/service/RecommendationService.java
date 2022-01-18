package uaic.fii.recommendation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import uaic.fii.recommendation.mapper.RecommendationResourceMapper;
import uaic.fii.recommendation.model.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RecommendationService {

    private static final String RECOMMENDATION_SEE_ALSO_QUERY_PREDICATE = "rdfs:seeAlso";

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private RecommendationResourceMapper resourceMapper;

    @Value("${main.service.endpoint}")
    private String mainServiceSparqlEndpoint;

    public Set<RecommendationOutputDto> getRecommendations(RecommendationInputDto recommendationInputDto) {
        List<ResourceDto> currentResources = recommendationInputDto.getCurrentResources();
        List<UserSkillDto> userSkills = recommendationInputDto.getUserSkills();
        List<UserInterestDto> userInterests = recommendationInputDto.getUserInterests();
        List<List<RecommendationOutputDto>> currentResourcesSeeAlsoRecommendations = currentResources
                .stream()
                .map(currentResource -> getSeeAlsoRecommendations(currentResource.getResourceMetadata().get("object")))
                .collect(Collectors.toList());
        List<List<RecommendationOutputDto>> userInterestsSeeAlsoRecommendations = userInterests
                .stream()
                .map(userInterest -> getSeeAlsoRecommendations(userInterest.getInterestData()))
                .collect(Collectors.toList());
        List<List<RecommendationOutputDto>> userSkillsSeeAlsoRecommendations = userSkills
                .stream()
                .map(userSkill -> getSeeAlsoRecommendations(userSkill.getSkillData()))
                .collect(Collectors.toList());
        return resourceMapper.mergeRecommendations(currentResourcesSeeAlsoRecommendations, userInterestsSeeAlsoRecommendations, userSkillsSeeAlsoRecommendations);
    }

    public List<RecommendationOutputDto> getSeeAlsoRecommendations(String searchSubject) {
        ResourceRequestInputDto resourceRequestInputDto = ResourceRequestInputDto
                .builder()
                .querySubject(searchSubject)
                .queryPredicate(RECOMMENDATION_SEE_ALSO_QUERY_PREDICATE)
                .build();
        ResourceDto[] seeAlsoRecommendations = restTemplate.postForObject(mainServiceSparqlEndpoint, resourceRequestInputDto, ResourceDto[].class);
        return resourceMapper.mapToRecommendationOutputDtos(Arrays.asList(seeAlsoRecommendations));
    }
}
