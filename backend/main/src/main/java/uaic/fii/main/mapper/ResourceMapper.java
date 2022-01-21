package uaic.fii.main.mapper;

import lombok.extern.slf4j.Slf4j;
import org.apache.jena.query.QuerySolution;
import org.springframework.stereotype.Component;
import uaic.fii.main.model.ResourceDto;

import java.util.Iterator;
import java.util.Optional;

@Slf4j
@Component
public class ResourceMapper {

    public Optional<ResourceDto> mapDbPediaResourceToDto(QuerySolution querySolution) {
        try {
            ResourceDto resourceDto = new ResourceDto();
            Iterator<String> varNamesIterator = querySolution.varNames();
            while (varNamesIterator.hasNext()) {
                String variableName = varNamesIterator.next();
                String resourceUri = getStringValue(querySolution, variableName);
                resourceDto.addResourceMetadata(variableName, resourceUri);
            }
            return Optional.of(resourceDto);
        } catch (Exception exception) {
            log.error("Failed to map resource {}", querySolution, exception);
        }
        return Optional.empty();
    }

    private String getStringValue(QuerySolution querySolution, String variableName) {
        return getResourceValue(querySolution, variableName).orElse(getLiteralValue(querySolution, variableName).orElse(null));
    }

    private Optional<String> getResourceValue(QuerySolution querySolution, String variableName) {
        try {
            return Optional.of(querySolution.getResource(variableName).getURI());
        } catch (Exception exception) {
            return Optional.empty();
        }
    }

    private Optional<String> getLiteralValue(QuerySolution querySolution, String variableName) {
        try {
            return Optional.of(querySolution.getLiteral(variableName).getString());
        } catch (Exception exception) {
            return Optional.empty();
        }
    }
}
