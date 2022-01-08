package uaic.fii.recommendation.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import uaic.fii.recommendation.model.RecommendationInputDto;
import uaic.fii.recommendation.model.ResourceDto;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
public class RecommendationController {

    @GetMapping("/recommandation")
    @Operation(description = "This will return a list of recommended resources for a given set of resources")
    @Parameter(in = ParameterIn.HEADER,
            description = "The Authorization header is mandatory and its valid must be provide JWT token in the form: Authorization: Bearer + <token>",
            name = "Authorization",
            required = true,
            content = @Content(schema = @Schema(type = "string")))
    public List<ResourceDto> getRecommandations(@RequestBody RecommendationInputDto recommendationInputDto) {
        return new ArrayList<>();
    }
}
