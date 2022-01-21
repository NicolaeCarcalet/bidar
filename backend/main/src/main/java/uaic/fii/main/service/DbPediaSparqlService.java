package uaic.fii.main.service;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.jena.query.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import uaic.fii.main.exception.ResourceNotFoundException;
import uaic.fii.main.mapper.ResourceMapper;
import uaic.fii.main.model.ResourceDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class DbPediaSparqlService {

    private static final int RETRY_INTERVAL_MILLISECONDS = 1000;
    private static final String DBPEDIA_QUERY_TEMPLATE = "prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
            "PREFIX dbo:     <http://dbpedia.org/ontology/>\n" +
            "PREFIX dbr:     <http://dbpedia.org/resource/>\n" +
            "select * where {%s %s %s}";

    private static final String DBPEDIA_ABSTRACT_QUERY_TEMPLATE = "prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
            "PREFIX dbo:     <http://dbpedia.org/ontology/>\n" +
            "PREFIX dbr:     <http://dbpedia.org/resource/>\n" +
            "select ?abstract where {<%s> dbo:abstract ?abstract . FILTER (lang(?abstract) = \"%s\")}";

    private static final String DBPEDIA_LABEL_QUERY_TEMPLATE = "prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
            "PREFIX dbo:     <http://dbpedia.org/ontology/>\n" +
            "PREFIX dbr:     <http://dbpedia.org/resource/>\n" +
            "select ?label where {<%s> rdfs:label ?label . FILTER (lang(?label) = \"%s\")}";
    private static final String ABSTRACT_LITERAL_KEY = "abstract";
    private static final String LABEL_LITERAL_KEY = "label";

    @Autowired
    private ResourceMapper resourceMapper;

    @Value("${dbpedia.sparql.endpoint}")
    private String dbPedialSparqlEndpoint;

    @Value("${retry.count}")
    private Integer retryCount;

    @Value("${retry.interval}")
    private Integer retryInterval;

    public List<ResourceDto> getResources(String resourceOfInterest, String predicate, String object, String countryCode) {
        String formattedQuery = String.format(DBPEDIA_QUERY_TEMPLATE, resourceOfInterest, predicate, object);
        Query query = QueryFactory.create(formattedQuery);
        return getResourcesFromQuery(query, countryCode);
    }

    private List<ResourceDto> getResourcesFromQuery(Query query, String countryCode) {
        ResultSet queryResultSet = getQueryResultSetWithRetries(query);
        List<ResourceDto> resourceDtos = extractDataFromQueryResultSet(queryResultSet);
        getAbstractForResources(countryCode, resourceDtos);
        getLabelForResources(countryCode, resourceDtos);
        return resourceDtos;
    }

    private List<ResourceDto> extractDataFromQueryResultSet(ResultSet queryResultSet) {
        List<ResourceDto> resources = new ArrayList<>();
        try {
            while (queryResultSet.hasNext()) {
                QuerySolution next = queryResultSet.next();
                Optional<ResourceDto> resourceDto = resourceMapper.mapDbPediaResourceToDto(next);
                resourceDto.ifPresent(resources::add);
            }
        } catch (Exception exception) {
            log.error("Couldn't get data from dbpedia", exception);
        }
        return resources;
    }

    private void getAbstractForResources(String countryCode, List<ResourceDto> resourceDtos) {
        for (ResourceDto resourceDto : resourceDtos) {
            String formattedQuery = String.format(DBPEDIA_ABSTRACT_QUERY_TEMPLATE, resourceDto.getResourceMetadata().get("s"), countryCode);
            Query query = QueryFactory.create(formattedQuery);
            ResultSet queryResultSet = getQueryResultSetWithRetries(query);
            while (queryResultSet.hasNext()) {
                QuerySolution querySolution = queryResultSet.next();
                String anAbstract = querySolution.getLiteral(ABSTRACT_LITERAL_KEY).getString();
                resourceDto.addResourceMetadata(ABSTRACT_LITERAL_KEY, anAbstract);
            }
        }
    }

    private void getLabelForResources(String countryCode, List<ResourceDto> resourceDtos) {
        for (ResourceDto resourceDto : resourceDtos) {
            String formattedQuery = String.format(DBPEDIA_LABEL_QUERY_TEMPLATE, resourceDto.getResourceMetadata().get("s"), countryCode);
            Query query = QueryFactory.create(formattedQuery);
            ResultSet queryResultSet = getQueryResultSetWithRetries(query);
            while (queryResultSet.hasNext()) {
                QuerySolution querySolution = queryResultSet.next();
                String label = querySolution.getLiteral(LABEL_LITERAL_KEY).getString();
                resourceDto.addResourceMetadata(LABEL_LITERAL_KEY, label);
            }
        }
    }

    @SneakyThrows
    private ResultSet getQueryResultSetWithRetries(Query query) {
        for (int i = 0; i < retryCount; i++) {
            Optional<ResultSet> queryResultSet = getQueryResultSet(query);
            if (queryResultSet.isPresent()) {
                return queryResultSet.get();
            }
            log.info("Retry query {} in {} minutes", query, retryInterval);
            Thread.sleep(retryInterval * RETRY_INTERVAL_MILLISECONDS);
        }
        throw new ResourceNotFoundException("Couldn't find any resources or dbpedia has an outage");
    }

    private Optional<ResultSet> getQueryResultSet(Query query) {
        try {
            QueryExecution exec = QueryExecutionFactory.sparqlService(dbPedialSparqlEndpoint, query);
            return Optional.ofNullable(exec.execSelect());
        } catch (Exception exception) {
            log.error("Failed to get data from dbpedia for query {}", query, exception);
        }
        return Optional.empty();
    }
}