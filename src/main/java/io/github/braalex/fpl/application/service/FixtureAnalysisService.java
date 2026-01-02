package io.github.braalex.fpl.application.service;

import io.github.braalex.fpl.infrastructure.persistence.entity.FixtureEntity;
import io.github.braalex.fpl.infrastructure.persistence.repository.FixtureJpaRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;

@Service
public class FixtureAnalysisService {

    private final FixtureJpaRepository fixtureRepository;

    public FixtureAnalysisService(FixtureJpaRepository fixtureRepository) {
        this.fixtureRepository = fixtureRepository;
    }

    public double getNext5Difficulty(int teamId) {
        return fixtureRepository.findByHomeTeamIdOrAwayTeamId(teamId, teamId).stream()
                .filter(f -> !f.getFinished() && f.getKickoffTime() != null)
                .sorted(Comparator.comparing(FixtureEntity::getKickoffTime))
                .limit(5)
                .mapToInt(f -> {
                    boolean isHome = f.getHomeTeamId() == teamId;
                    return isHome ? f.getHomeDifficulty() : f.getAwayDifficulty();
                })
                .average()
                .orElse(0);
    }
}
