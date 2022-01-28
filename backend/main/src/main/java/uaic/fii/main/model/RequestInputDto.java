package uaic.fii.main.model;

import lombok.Data;

@Data
public class RequestInputDto {

    private String querySubject;
    private String queryPredicate;
    private String queryObject;
    private String countryCode;
    private Integer limit;
}
