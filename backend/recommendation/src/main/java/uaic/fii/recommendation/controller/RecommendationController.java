package uaic.fii.recommendation.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uaic.fii.recommendation.model.RecommendationInputDto;
import uaic.fii.recommendation.model.RecommendationOutputDto;
import uaic.fii.recommendation.service.RecommendationService;

import java.util.List;
import java.util.Set;

@CrossOrigin
@RestController
public class RecommendationController {

    @Autowired
    private RecommendationService recommendationService;

    @PostMapping("/recommendation")
    @Operation(description = "This will return a list of recommended resources for a given set of resources")
    @Parameter(in = ParameterIn.HEADER,
            description = "The Authorization header is mandatory and its valid must be provide JWT token in the form: Authorization: Bearer + <token>",
            name = "Authorization",
            required = true,
            content = @Content(schema = @Schema(type = "string")))
    public Set<RecommendationOutputDto> getRecommendations(@RequestBody RecommendationInputDto recommendationInputDto) {
        return recommendationService.getRecommendations(recommendationInputDto);
    }
}
