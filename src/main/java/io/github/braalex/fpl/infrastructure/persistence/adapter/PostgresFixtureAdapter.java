package io.github.braalex.fpl.infrastructure.persistence.adapter;

import io.github.braalex.fpl.domain.model.Fixture;
import io.github.braalex.fpl.infrastructure.persistence.entity.FixtureEntity;
import io.github.braalex.fpl.infrastructure.persistence.repository.FixtureJpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PostgresFixtureAdapter {
    private final FixtureJpaRepository repository;

    public PostgresFixtureAdapter(FixtureJpaRepository repository) {
        this.repository = repository;
    }

    public void saveAll(List<Fixture> fixtures) {
        List<FixtureEntity> entities = fixtures.stream()
                .map(f -> new FixtureEntity(
                        f.id(),
                        f.gameweek(),
                        f.homeTeamId(),
                        f.awayTeamId(),
                        f.homeDifficulty(),
                        f.awayDifficulty(),
                        f.kickoffTime(),
                        f.finished()
                ))
                .toList();

        repository.saveAll(entities);
    }
}
