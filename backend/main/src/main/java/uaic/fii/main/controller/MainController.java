package uaic.fii.main.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uaic.fii.main.model.RequestInputDto;
import uaic.fii.main.model.ResourceDiffDto;
import uaic.fii.main.model.ResourceDto;
import uaic.fii.main.service.ResourceDiffService;
import uaic.fii.main.service.ResourceService;

import java.util.List;

@CrossOrigin
@RestController
public class MainController {

    @Autowired
    private ResourceService resourceService;

    @Autowired
    private ResourceDiffService resourceDiffService;

    @PostMapping("/resources")
    @Operation(description = "This will return all the resources for the given request")
    @Parameter(in = ParameterIn.HEADER,
                description = "The Authorization header is mandatory and its valid must be provide JWT token in the form: Authorization: Bearer + <token>",
                name = "Authorization",
                required = true,
                content = @Content(schema = @Schema(type = "string")))
    public List<ResourceDto> getResources(@RequestBody RequestInputDto requestInputDto) {
        return resourceService.getResources(requestInputDto);
    }

    @PostMapping("/diff")
    @Operation(description = "This will compare the given DTOs and return all the differences between them")
    @Parameter(in = ParameterIn.HEADER,
            description = "The Authorization header is mandatory and its valid must be provide JWT token in the form: Authorization: Bearer + <token>",
            name = "Authorization",
            required = true,
            content = @Content(schema = @Schema(type = "string")))
    public ResourceDiffDto diffResources(@RequestBody List<ResourceDto> resourceDtos) {
        return resourceDiffService.getResourcesDiff(resourceDtos);
    }
}
