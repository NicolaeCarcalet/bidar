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
    private static final String DBPEDIA_QUERY_TEMPLATE = "prefix rdfs:    <http://www.w3.org/2000/01/rdf-schema#>\n" +
            "PREFIX dbo:     <http://dbpedia.org/ontology/>" +
            "PREFIX dbr:     <http://dbpedia.org/resource/>" +
            "select * where {dbr:%s %s %s}";

    @Autowired
    private ResourceMapper resourceMapper;

    @Value("${dbpedia.sparql.endpoint}")
    private String dbPedialSparqlEndpoint;

    @Value("${retry.count}")
    private Integer retryCount;

    @Value("${retry.interval}")
    private Integer retryInterval;

    public List<ResourceDto> getResources(String resourceOfInterest, String predicate, String object) {
        Query query = QueryFactory.create(String.format(DBPEDIA_QUERY_TEMPLATE, resourceOfInterest, predicate, object));
        return getResourcesFromQuery(query);
    }

    private List<ResourceDto> getResourcesFromQuery(Query query) {
        ResultSet queryResultSet = getQueryResultSetWithRetries(query);
        return extractDataFromQueryResultSet(queryResultSet);
    }

    private List<ResourceDto> extractDataFromQueryResultSet(ResultSet queryResultSet) {
        List<ResourceDto> resources = new ArrayList<>();
        while (queryResultSet.hasNext()) {
            QuerySolution next = queryResultSet.next();
            Optional<ResourceDto> resourceDto = resourceMapper.mapDbPediaResourceToDto(next);
            resourceDto.ifPresent(resources::add);
        }
        return resources;
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