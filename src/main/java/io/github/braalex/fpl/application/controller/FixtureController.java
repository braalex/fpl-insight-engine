package io.github.braalex.fpl.application.controller;

import io.github.braalex.fpl.application.service.FixtureAnalysisService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/fixtures")
public class FixtureController {

    private final FixtureAnalysisService analysisService;

    public FixtureController(FixtureAnalysisService analysisService) {
        this.analysisService = analysisService;
    }

    @GetMapping("/difficulty/{teamId}")
    public double getDifficulty(@PathVariable int teamId) {
        return analysisService.getNext5Difficulty(teamId);
    }
}
