package io.github.braalex.fpl.application.controller;

import io.github.braalex.fpl.infrastructure.persistence.entity.PlayerEntity;
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
    public List<PlayerEntity> getAllPlayers() {
        return playerRepository.findAll();
    }

    @GetMapping("/{id}")
    public PlayerEntity getPlayerById(@PathVariable Integer id) {
        return playerRepository.findById(id).orElseThrow();
    }

    @GetMapping("/team/{teamId}")
    public List<PlayerEntity> getPlayersByTeam(@PathVariable Integer teamId) {
        return playerRepository.findByTeamId(teamId);
    }

}
