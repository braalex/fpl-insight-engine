package io.github.braalex.fpl.infrastructure.persistence.adapter;

import io.github.braalex.fpl.domain.model.Player;
import io.github.braalex.fpl.infrastructure.persistence.entity.PlayerEntity;
import io.github.braalex.fpl.infrastructure.persistence.entity.TeamEntity;
import io.github.braalex.fpl.infrastructure.persistence.repository.PlayerJpaRepository;
import io.github.braalex.fpl.infrastructure.persistence.repository.TeamJpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PostgresPlayerAdapter {

    private final PlayerJpaRepository playerRepository;
    private final TeamJpaRepository teamRepository;

    public PostgresPlayerAdapter(PlayerJpaRepository playerRepository, TeamJpaRepository teamRepository) {
        this.playerRepository = playerRepository;
        this.teamRepository = teamRepository;
    }

    public void saveAll(List<Player> players) {
        List<PlayerEntity> entities = players.stream()
                .map(p -> {
                    TeamEntity teamEntity = teamRepository.findById(p.team())
                            .orElseThrow(() -> new RuntimeException("Team not found: " + p.team()));

                    return new PlayerEntity(
                            p.id(),
                            p.first_name(),
                            p.second_name(),
                            p.web_name(),
                            teamEntity,
                            p.element_type(),
                            p.now_cost(),
                            p.selected_by_percent(),
                            p.total_points(),
                            p.form(),
                            p.transfers_in(),
                            p.transfers_out(),
                            p.minutes(),
                            p.goals_scored(),
                            p.assists(),
                            p.clean_sheets(),
                            p.status(),
                            p.news()
                    );
                })
                .toList();

        playerRepository.saveAll(entities);
    }
}