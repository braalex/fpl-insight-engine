package io.github.braalex.fpl.infrastructure.persistence.repository;

import io.github.braalex.fpl.infrastructure.persistence.entity.PlayerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerJpaRepository extends JpaRepository<PlayerEntity, Integer> {
    List<PlayerEntity> findByTeamId(Integer teamId);
    List<PlayerEntity> findTop10ByOrderByTotalPointsDesc();

    // "Find players with at least X minutes, ordered by Form/Cost"
    @Query("""
        SELECT p FROM PlayerEntity p
        WHERE p.minutes > :minMinutes
        ORDER BY (p.form / p.nowCost) DESC
        LIMIT 10
    """)
    List<PlayerEntity> findUndervaluedPlayers(@Param("minMinutes") int minMinutes);
}
