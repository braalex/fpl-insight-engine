package io.github.braalex.fpl.application.dto;

public record PlayerInsightDto(
        int id,
        String name,
        String teamName,
        String position,
        double form,
        double difficultyRating,
        double transferRating
) {
}
