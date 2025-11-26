package io.github.braalex.fpl.domain.model;

public record Player(
        int id,                      // element_id in API
        String first_name,
        String second_name,
        String web_name,             // display name
        int team,
        int element_type,            // position: 1=GK, 2=DEF, 3=MID, 4=FWD
        int now_cost,                // current price (in 0.1m units, so divide by 10)
        double selected_by_percent,
        int total_points,
        double form,                 // last 4 gameweeks
        int transfers_in,
        int transfers_out,
        int minutes,
        int goals_scored,
        int assists,
        int clean_sheets,            // GK/DEF only
        String status,               // "a" (available), "u" (unavailable), "d" (doubtful)
        String news
) {
}
