package io.github.braalex.fpl.application.service;

import io.github.braalex.fpl.domain.ports.FplDataProvider;
import io.github.braalex.fpl.infrastructure.persistence.adapter.PostgresPlayerAdapter;
import io.github.braalex.fpl.infrastructure.persistence.adapter.PostgresTeamAdapter;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

@Service
public class FplDataSyncService implements CommandLineRunner {

    private final FplDataProvider fplApiClient;
    private final PostgresTeamAdapter postgresTeamAdapter;
    private final PostgresPlayerAdapter postgresPlayerAdapter;

    public FplDataSyncService(FplDataProvider fplApiClient,
                              PostgresTeamAdapter postgresTeamAdapter,
                              PostgresPlayerAdapter postgresPlayerAdapter) {
        this.fplApiClient = fplApiClient;
        this.postgresTeamAdapter = postgresTeamAdapter;
        this.postgresPlayerAdapter = postgresPlayerAdapter;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("SYNCING FPL DATA...");
        var teams = fplApiClient.fetchTeams();
        postgresTeamAdapter.saveAll(teams);
        System.out.println("Saved " + teams.size() + " teams to database.");
        var players = fplApiClient.fetchPlayers();
        postgresPlayerAdapter.saveAll(players);
        System.out.println("Saved " + players.size() + " players to database.");
        System.out.println("DATA SYNC COMPLETE!");
    }
}
