package io.github.braalex.fpl.application.controller;

import io.github.braalex.fpl.application.dto.TeamResponse;
import io.github.braalex.fpl.infrastructure.persistence.repository.TeamJpaRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/teams")
public class TeamController {

    private final TeamJpaRepository teamRepository;

    public TeamController(TeamJpaRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @GetMapping
    public List<TeamResponse> getAllTeams() {
        return teamRepository.findAll().stream()
                .map(TeamResponse::from)
                .toList();
    }

    @GetMapping("/{id}")
    public TeamResponse getTeamById(@PathVariable Integer id) {
        return teamRepository.findById(id)
                .map(TeamResponse::from)
                .orElseThrow();
    }
}
