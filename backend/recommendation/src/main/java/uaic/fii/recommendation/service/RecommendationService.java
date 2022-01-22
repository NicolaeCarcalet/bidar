package uaic.fii.recommendation.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import uaic.fii.recommendation.mapper.RecommendationResourceMapper;
import uaic.fii.recommendation.model.*;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
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
        String countryCode = recommendationInputDto.getCountryCode();
        List<ResourceDto> currentResources = recommendationInputDto.getCurrentResources();
        List<List<RecommendationOutputDto>> currentResourcesSeeAlsoRecommendations = currentResources
                .stream()
                .map(currentResource -> currentResource.getResourceMetadata().get("label"))
                .filter(Objects::nonNull)
                .map(currentResource -> getSeeAlsoRecommendations(currentResource, countryCode))
                .collect(Collectors.toList());
        return resourceMapper.flattenRecommendations(currentResourcesSeeAlsoRecommendations);
    }

    public List<RecommendationOutputDto> getSeeAlsoRecommendations(String searchSubject, String countryCode) {
        ResourceRequestInputDto resourceRequestInputDto = ResourceRequestInputDto
                .builder()
                .queryObject("?s")
                .queryPredicate(RECOMMENDATION_SEE_ALSO_QUERY_PREDICATE)
                .querySubject("dbr:" + searchSubject)
                .countryCode(countryCode)
                .build();
        log.info("Making request for {} {}", searchSubject, resourceRequestInputDto);
        ResourceDto[] seeAlsoRecommendations = restTemplate.postForObject(mainServiceSparqlEndpoint, resourceRequestInputDto, ResourceDto[].class);
        return resourceMapper.mapToRecommendationOutputDtos(Arrays.asList(seeAlsoRecommendations));
    }
}
