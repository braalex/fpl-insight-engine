package io.github.braalex.fpl.infrastructure.client;

import io.github.braalex.fpl.domain.model.Player;
import io.github.braalex.fpl.domain.model.Team;
import io.github.braalex.fpl.domain.ports.FplDataProvider;
import io.github.braalex.fpl.infrastructure.client.FplApiDto.BootstrapStaticResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.List;

@Component
public class FplRestClientAdapter implements FplDataProvider {

    public static final String BOOTSTRAP_STATIC_URI = "/bootstrap-static/";
    private final RestClient restClient;

    public FplRestClientAdapter(RestClient.Builder builder, @Value("${fpl.api.url}") String fplApiUrl) {
        this.restClient = builder.baseUrl(fplApiUrl).build();
    }

    @Override
    public List<Team> fetchTeams() {
        var response = restClient.get().uri(BOOTSTRAP_STATIC_URI).retrieve().body(BootstrapStaticResponse.class);
        return response.teams().stream()
                .map(t -> new Team(
                        t.id(),
                        t.name(),
                        t.strength()
                ))
                .toList();
    }

    @Override
    public List<Player> fetchPlayers() {
        var response = restClient.get().uri(BOOTSTRAP_STATIC_URI).retrieve().body(BootstrapStaticResponse.class);
        return response.elements().stream()
                .map(p -> new Player(
                        p.id(),
                        p.first_name(),
                        p.second_name(),
                        p.web_name(),
                        p.team(),
                        p.element_type(),
                        p.now_cost(),
                        p.selected_by_percent(),
                        p.total_points(),
                        p.form(),
                        p.transfers_in(),
                        p.transfers_out(),
                        p.minutes(),
                        p.goals_scored(),
                        p.assists(),
                        p.clean_sheets(),
                        p.status(),
                        p.news()
                ))
                .toList();
    }
}
