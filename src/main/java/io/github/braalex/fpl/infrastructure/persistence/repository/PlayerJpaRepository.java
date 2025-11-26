package io.github.braalex.fpl.infrastructure.persistence.repository;

import io.github.braalex.fpl.infrastructure.persistence.entity.PlayerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerJpaRepository extends JpaRepository<PlayerEntity, Integer> {
    List<PlayerEntity> findByTeamId(Integer teamId);
}
