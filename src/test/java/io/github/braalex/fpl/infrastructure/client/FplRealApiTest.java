package io.github.braalex.fpl.infrastructure.client;

import io.github.braalex.fpl.domain.ports.FplDataProvider;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.hibernate.autoconfigure.HibernateJpaAutoConfiguration;
import org.springframework.boot.jdbc.autoconfigure.DataSourceAutoConfiguration;
import org.springframework.boot.jdbc.autoconfigure.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@EnableAutoConfiguration(exclude = {
        DataSourceAutoConfiguration.class,
        DataSourceTransactionManagerAutoConfiguration.class,
        HibernateJpaAutoConfiguration.class
})
public class FplRealApiTest {

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
