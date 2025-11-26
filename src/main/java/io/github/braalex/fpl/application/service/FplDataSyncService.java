package io.github.braalex.fpl.application.service;

import io.github.braalex.fpl.domain.ports.FplDataProvider;
import io.github.braalex.fpl.infrastructure.persistence.adapter.PostgresPlayerAdapter;
import io.github.braalex.fpl.infrastructure.persistence.adapter.PostgresTeamAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class FplDataSyncService {

    private static final Logger log = LoggerFactory.getLogger(FplDataSyncService.class);

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

    @EventListener(ApplicationReadyEvent.class)
    public void onStartup() {
        syncData();
    }

    @Scheduled(cron = "0 0 */6 * * *")
    public void scheduleSync() {
        log.info("Scheduled sync started...");
        syncData();
    }

    private void syncData() {
        log.info("Starting FPL data sync...");
        try {
            var teams = fplApiClient.fetchTeams();
            postgresTeamAdapter.saveAll(teams);
            log.info("Synced {} teams.", teams.size());

            var players = fplApiClient.fetchPlayers();
            postgresPlayerAdapter.saveAll(players);
            log.info("Synced {} players.", players.size());

            log.info("Data sync completed successfully.");
        } catch (Exception e) {
            log.error("Sync failed: {}", e.getMessage(), e);
        }
    }
}
