package io.github.braalex.fpl.application.dto;

import io.github.braalex.fpl.infrastructure.persistence.entity.TeamEntity;

public record TeamResponse(
        int id,
        String name,
        int strength
) {
    public static TeamResponse from(TeamEntity entity) {
        return new TeamResponse(
                entity.getId(),
                entity.getName(),
                entity.getStrength()
        );
    }
}
