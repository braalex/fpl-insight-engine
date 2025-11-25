package io.github.braalex.fpl.infrastructure.client;

import io.github.braalex.fpl.domain.model.Team;
import io.github.braalex.fpl.domain.ports.FplDataProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.List;

@Component
public class FplRestClientAdapter implements FplDataProvider {

    public static final String BOOTSTRAP_STATIC_URI = "/bootstrap-static/";
    private final RestClient restClient;

    public FplRestClientAdapter(RestClient.Builder builder, @Value("${fpl.api.url}")  String fplApiUrl) {
        this.restClient = builder.baseUrl(fplApiUrl).build();
    }

    @Override
    public List<Team> fetchTeams() {
        var response = restClient.get().uri(BOOTSTRAP_STATIC_URI).retrieve().body(FplJsonDto.class);

        return response.teams().stream().map(t -> new Team(t.id(), t.name(), t.strength())).toList();
    }

    record FplJsonDto(List<FplTeamDto> teams) {}
    record FplTeamDto(int id, String name, int strength) {}
}
