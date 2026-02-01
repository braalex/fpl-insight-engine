package io.github.braalex.fpl.application.service;

import io.github.braalex.fpl.application.dto.PlayerInsightDto;
import io.github.braalex.fpl.infrastructure.persistence.entity.PlayerEntity;
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

    private record FplContext(
            List<PlayerEntity> players,
            Map<Integer, String> teamNames,
            Map<Integer, Double> difficulties
    ) {
    }

    private FplContext buildContext() {
        var players = playerRepository.findAll();
        var teams = teamRepository.findAll();
        var teamNames = teams.stream()
                .collect(Collectors.toMap(TeamEntity::getId, TeamEntity::getName));
        var difficulties = teams.stream()
                .collect(Collectors.toMap(
                        TeamEntity::getId,
                        t -> fixtureService.getNext5Difficulty(t.getId())));

        return new FplContext(players, teamNames, difficulties);
    }

    public List<PlayerInsightDto> getRecommendedTransfers(int limit) {
        var context = buildContext();

        return context.players.stream()
                .map(p -> {
                    double difficulty = context.difficulties.get(p.getTeam().getId());
                    double form = p.getForm();
                    double fixtureBonus = (5.0 - difficulty);
                    double rawScore = (form * 0.7) + (fixtureBonus * 0.3);

                    int chance = p.getChanceOfPlayingNextRound() == null ? 100 : p.getChanceOfPlayingNextRound();
                    double availabilityFactor = chance / 100.0;
                    double transferRating = rawScore * availabilityFactor;

                    return createDto(p, context, difficulty, transferRating);
                })
                .sorted(Comparator.comparing(PlayerInsightDto::transferRating).reversed())
                .limit(limit)
                .toList();
    }

    public List<PlayerInsightDto> getUndervaluedPlayers(int limit) {
        var context = buildContext();

        return context.players.stream()
                .filter(p -> p.getMinutes() >= 300)
                .filter(p -> {
                    int chance = p.getChanceOfPlayingNextRound() == null ? 100 : p.getChanceOfPlayingNextRound();
                    return chance >= 75;
                })
                .filter(p -> p.getForm() >= 3.0)
                .map(p -> {
                    double realPrice = p.getNowCost() / 10.0;
                    double valueScore = p.getTotalPoints() / realPrice;
                    double difficulty = context.difficulties.get(p.getTeam().getId());

                    return createDto(p, context, difficulty, valueScore);
                })
                .sorted(Comparator.comparing(PlayerInsightDto::transferRating).reversed())
                .limit(limit)
                .toList();
    }

    private PlayerInsightDto createDto(PlayerEntity p, FplContext context, double difficulty, double rating) {
        return new PlayerInsightDto(
                p.getId(),
                p.getWebName(),
                context.teamNames.get(p.getTeam().getId()),
                mapPosition(p.getElementType()),
                p.getNowCost() / 10.0,
                p.getForm(),
                round(difficulty),
                round(rating)
        );
    }

    private double round(double value) {
        return Math.round(value * 10.0) / 10.0;
    }
}
