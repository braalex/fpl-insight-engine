package io.github.braalex.fpl.infrastructure.client;

import io.github.braalex.fpl.AbstractIntegrationTest;
import io.github.braalex.fpl.domain.ports.FplDataProvider;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

public class FplRealApiTest extends AbstractIntegrationTest {

    @Autowired
    private FplDataProvider fplDataProvider;

    @Test
    void shouldFetchRealTeamsFromFpl() {
        var teams = fplDataProvider.fetchTeams();

        assertThat(teams).isNotEmpty();
        assertThat(teams).anyMatch(team -> team.name().equals("Arsenal"));

        System.out.println("Verified: " + teams.size() + " teams fetched.");
    }
}
