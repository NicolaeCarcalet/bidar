package uaic.fii.recommendation.model;

import lombok.*;

@Data
@Builder
public class ResourceRequestInputDto {

    private String querySubject;
    private String queryPredicate;
    private String queryObject;
    private String countryCode;
}
