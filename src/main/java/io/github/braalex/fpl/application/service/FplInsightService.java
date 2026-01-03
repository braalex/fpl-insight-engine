package io.github.braalex.fpl.application.service;

import io.github.braalex.fpl.application.dto.PlayerInsightDto;
import io.github.braalex.fpl.infrastructure.persistence.entity.TeamEntity;
import io.github.braalex.fpl.infrastructure.persistence.repository.PlayerJpaRepository;
import io.github.braalex.fpl.infrastructure.persistence.repository.TeamJpaRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static io.github.braalex.fpl.application.dto.PlayerResponse.mapPosition;

@Service
public class FplInsightService {

    private final PlayerJpaRepository playerRepository;
    private final TeamJpaRepository teamRepository;
    private final FixtureAnalysisService fixtureService;

    public FplInsightService(PlayerJpaRepository playerRepository,
                             TeamJpaRepository teamRepository,
                             FixtureAnalysisService fixtureService) {
        this.playerRepository = playerRepository;
        this.teamRepository = teamRepository;
        this.fixtureService = fixtureService;
    }

    public List<PlayerInsightDto> getRecommendedTransfers(int limit) {
        var players = playerRepository.findAll();
        var teams = teamRepository.findAll();
        Map<Integer, Double> difficultyMap = teams.stream()
                .collect(Collectors.toMap(
                        TeamEntity::getId,
                        t -> fixtureService.getNext5Difficulty(t.getId())
                ));

        Map<Integer, String> teamNames = teams.stream()
                .collect(Collectors.toMap(
                        TeamEntity::getId,
                        TeamEntity::getName
                ));

        return players.stream()
                .map(p -> {
                    double difficulty = difficultyMap.get(p.getTeam().getId());
                    double form = p.getForm();
                    double fixtureBonus = (5.0 - difficulty);
                    double rawScore = (form * 0.7) + (fixtureBonus * 0.3);

                    int chance = p.getChanceOfPlayingNextRound() == null ? 100 : p.getChanceOfPlayingNextRound();
                    double availabilityFactor = chance / 100.0;
                    double transferRating = rawScore * availabilityFactor;

                    return new PlayerInsightDto(
                            p.getId(),
                            p.getWebName(),
                            teamNames.get(p.getTeam().getId()),
                            mapPosition(p.getElementType()),
                            form,
                            difficulty,
                            transferRating
                    );
                })
                .sorted(Comparator.comparing(PlayerInsightDto::transferRating).reversed())
                .limit(limit)
                .toList();
    }
}
