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
                String resourceUri = querySolution.getResource(variableName).getURI();
                resourceDto.addResourceMetadata(variableName, resourceUri);
            }
            return Optional.of(resourceDto);
        } catch (Exception exception) {
            log.error("Failed to map resource {}", querySolution, exception);
        }
        return Optional.empty();
    }
}
