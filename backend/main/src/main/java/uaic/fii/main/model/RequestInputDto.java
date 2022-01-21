package uaic.fii.main.model;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class RequestInputDto {

    private String querySubject;
    private String queryPredicate;
    private String queryObject;
    private String countryCode;
}
