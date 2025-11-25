package io.github.braalex.fpl.application.service;

import io.github.braalex.fpl.domain.ports.FplDataProvider;
import io.github.braalex.fpl.infrastructure.persistence.adapter.PostgresTeamAdapter;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

@Service
public class FplDataSyncService implements CommandLineRunner {

    private final FplDataProvider fplApiClient;
    private final PostgresTeamAdapter postgresAdapter;

    public FplDataSyncService(FplDataProvider fplApiClient, PostgresTeamAdapter postgresAdapter) {
        this.fplApiClient = fplApiClient;
        this.postgresAdapter = postgresAdapter;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("SYNCING FPL DATA...");
        var teams = fplApiClient.fetchTeams();
        System.out.println("Fetched " + teams.size() + " teams from API.");
        postgresAdapter.saveAll(teams);
        System.out.println("Saved " + teams.size() + " teams to database.");
        System.out.println("DATA SYNC COMPLETE!");
    }
}
