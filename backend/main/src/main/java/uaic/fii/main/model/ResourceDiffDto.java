package uaic.fii.main.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ResourceDiffDto {
    private List<String> uniqueKeys;
    private List<String> uniqueValues;
}