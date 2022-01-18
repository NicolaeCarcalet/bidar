package uaic.fii.recommendation.model;

import lombok.*;

import javax.validation.constraints.NotNull;

@Data
@Builder
public class ResourceRequestInputDto {

    @NotNull
    private String querySubject;
    private String queryPredicate;
    private String queryObject;
}
