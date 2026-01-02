package io.github.braalex.fpl.domain.model;

import java.time.LocalDateTime;

public record Fixture(
        int id,
        Integer gameweek,
        int homeTeamId,
        int awayTeamId,
        int homeDifficulty,
        int awayDifficulty,
        LocalDateTime kickoffTime,
        boolean finished
) {
}
