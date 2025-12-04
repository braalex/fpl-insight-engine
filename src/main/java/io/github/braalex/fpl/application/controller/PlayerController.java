package io.github.braalex.fpl.application.controller;

import io.github.braalex.fpl.application.dto.PlayerResponse;
import io.github.braalex.fpl.application.dto.UndervaluedPlayerResponse;
import io.github.braalex.fpl.infrastructure.persistence.repository.PlayerJpaRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/players")
public class PlayerController {

    private final PlayerJpaRepository playerRepository;

    public PlayerController(PlayerJpaRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @GetMapping
    public List<PlayerResponse> getAllPlayers() {
        return playerRepository.findAll().stream()
                .map(PlayerResponse::from)
                .toList();
    }

    @GetMapping("/{id}")
    public PlayerResponse getPlayerById(@PathVariable Integer id) {
        return playerRepository.findById(id)
                .map(PlayerResponse::from)
                .orElseThrow();
    }

    @GetMapping("/team/{teamId}")
    public List<PlayerResponse> getPlayersByTeam(@PathVariable Integer teamId) {
        return playerRepository.findByTeamId(teamId).stream()
                .map(PlayerResponse::from)
                .toList();
    }

    @GetMapping("/top")
    public List<PlayerResponse> getTop10Players() {
        return playerRepository.findTop10ByOrderByTotalPointsDesc().stream()
                .map(PlayerResponse::from)
                .toList();
    }

    @GetMapping("/undervalued")
    public List<UndervaluedPlayerResponse> getUndervaluedPlayers() {
        return playerRepository.findUndervaluedPlayers(300).stream()
                .map(UndervaluedPlayerResponse::from)
                .toList();
    }
}
