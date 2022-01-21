package uaic.fii.main.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import uaic.fii.main.model.RequestInputDto;
import uaic.fii.main.model.ResourceDto;

import java.util.List;
import java.util.Optional;

@Service
public class ResourceService {

    private static final String DEFAULT_SUBJECTE_QUERY = "?s";
    private static final String DEFAULT_PREDICATE_QUERY = "?predicate";
    private static final String DEFAULT_OBJECT_QUERY = "?object";
    private static final String DEFAULT_COUNTRY_CODE = "en";

    @Autowired
    private DbPediaSparqlService dbPediaSparqlService;

    public List<ResourceDto> getResources(@Validated RequestInputDto requestInputDto) {
        String querySubject = Optional.ofNullable(requestInputDto.getQuerySubject()).orElse(DEFAULT_SUBJECTE_QUERY);
        String queryPredicate = Optional.ofNullable(requestInputDto.getQueryPredicate()).orElse(DEFAULT_PREDICATE_QUERY);
        String queryObject = Optional.ofNullable(requestInputDto.getQueryObject()).orElse(DEFAULT_OBJECT_QUERY);
        String countryCode = Optional.ofNullable(requestInputDto.getCountryCode()).orElse(DEFAULT_COUNTRY_CODE);
        return dbPediaSparqlService.getResources(querySubject, queryPredicate, queryObject, countryCode);
    }
}
