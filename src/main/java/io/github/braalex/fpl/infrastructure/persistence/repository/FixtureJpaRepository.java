package io.github.braalex.fpl.infrastructure.persistence.repository;

import io.github.braalex.fpl.infrastructure.persistence.entity.FixtureEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FixtureJpaRepository extends JpaRepository<FixtureEntity, Integer> {
    List<FixtureEntity> findByHomeTeamIdOrAwayTeamId(Integer homeTeamId, Integer awayTeamId);
}
