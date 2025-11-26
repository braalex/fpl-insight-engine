package io.github.braalex.fpl.infrastructure.client;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

public class FplApiDto {

    @JsonIgnoreProperties(ignoreUnknown = true)
    record BootstrapStaticResponse(
            List<TeamDto> teams,
            List<PlayerDto> elements  // FPL calls players "elements"
    ) {
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    record TeamDto(
            int id,
            String name,
            int strength
    ) {
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    record PlayerDto(
            int id,
            String first_name,
            String second_name,
            String web_name,
            int team,
            int element_type,
            int now_cost,
            double selected_by_percent,
            int total_points,
            double form,
            int transfers_in,
            int transfers_out,
            int minutes,
            int goals_scored,
            int assists,
            int clean_sheets,
            String status,
            String news
    ) {
    }
}
