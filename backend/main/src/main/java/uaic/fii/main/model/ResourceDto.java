package uaic.fii.main.model;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class ResourceDto {
    private Map<String, String> resourceMetadata;

    public ResourceDto() {
        resourceMetadata = new HashMap<>();
    }

    public void addResourceMetadata(String key, String value) {
        resourceMetadata.put(key, value);
    }
}
