package io.github.braalex.fpl.application.controller;

import io.github.braalex.fpl.application.dto.PlayerInsightDto;
import io.github.braalex.fpl.application.service.FplInsightService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/insights")
public class PlayerInsightController {

    private final FplInsightService insightService;

    public PlayerInsightController(FplInsightService insightService) {
        this.insightService = insightService;
    }

    @GetMapping("/recommended")
    public List<PlayerInsightDto> getRecommendedTransfers(
            @RequestParam(defaultValue = "10") int limit) {
        return insightService.getRecommendedTransfers(limit);
    }

    @GetMapping("/undervalued")
    public List<PlayerInsightDto> getUndervaluedPlayers(
            @RequestParam(defaultValue = "10") int limit) {
        return insightService.getUndervaluedPlayers(limit);
    }
}
