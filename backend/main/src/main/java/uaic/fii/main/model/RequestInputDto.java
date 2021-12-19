package uaic.fii.main.model;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class RequestInputDto {

    @NotNull
    private String querySubject;
    private String queryPredicate;
    private String queryObject;
}
