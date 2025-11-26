package io.github.braalex.fpl.application.dto;

import io.github.braalex.fpl.infrastructure.persistence.entity.PlayerEntity;

public record PlayerResponse(
        int id,
        String webName,
        String teamName,
        String position,
        int totalPoints,
        double price,          // converted from 100 -> 10.0
        double selectedBy      // % ownership
) {
    public static PlayerResponse from(PlayerEntity entity) {
        return new PlayerResponse(
                entity.getId(),
                entity.getWebName(),
                entity.getTeam().getName(),
                mapPosition(entity.getElementType()),
                entity.getTotalPoints(),
                entity.getNowCost() / 10.0,
                entity.getSelectedByPercent()
        );
    }

    private static String mapPosition(int type) {
        return switch (type) {
            case 1 -> "GK";
            case 2 -> "DEF";
            case 3 -> "MID";
            case 4 -> "FWD";
            default -> "UNK";
        };
    }
}
