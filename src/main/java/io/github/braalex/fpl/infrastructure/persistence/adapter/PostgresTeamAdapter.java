package io.github.braalex.fpl.infrastructure.persistence.adapter;

import io.github.braalex.fpl.domain.model.Team;
import io.github.braalex.fpl.infrastructure.persistence.entity.TeamEntity;
import io.github.braalex.fpl.infrastructure.persistence.repository.TeamJpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PostgresTeamAdapter {

    private final TeamJpaRepository repository;

    public PostgresTeamAdapter(TeamJpaRepository repository) {
        this.repository = repository;
    }

    public void saveAll(List<Team> teams) {
        List<TeamEntity> entities = teams.stream()
                .map(t -> new TeamEntity(t.id(), t.name(), t.strength()))
                .toList();

        repository.saveAll(entities);
    }
}
