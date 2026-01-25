package io.github.braalex.fpl.application.dto;

public record PlayerInsightDto(
        int id,
        String name,
        String teamName,
        String position,
        double price,
        double form,
        double difficultyRating,
        double transferRating
) {
}
