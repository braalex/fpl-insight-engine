package io.github.braalex.fpl.domain.ports;

import io.github.braalex.fpl.domain.model.Team;
import java.util.List;

public interface FplDataProvider {
    List<Team> fetchTeams();
}
