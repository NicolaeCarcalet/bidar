package uaic.fii.recommendation.model;

import lombok.Data;

@Data
public class RecommendationOutputDto {
    private String recommendationResource;
    private String recommendationAbstract;
    private String recommendationLabel;
}
