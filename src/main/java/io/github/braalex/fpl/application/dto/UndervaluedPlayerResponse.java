package io.github.braalex.fpl.application.dto;

import io.github.braalex.fpl.infrastructure.persistence.entity.PlayerEntity;

public record UndervaluedPlayerResponse(
        String name,
        String team,
        double price,
        double form,
        double valueRatio
) {
    public static UndervaluedPlayerResponse from(PlayerEntity p) {
        double price = p.getNowCost() / 10.0;
        double form = p.getForm();
        double value = (price > 0) ? (form / price) : 0.0;

        return new UndervaluedPlayerResponse(
                p.getWebName(),
                p.getTeam().getName(),
                price,
                form,
                Math.round(value * 100.0) / 100.0
        );
    }
}
