package io.github.braalex.fpl.infrastructure.persistence.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "fixtures")
public class FixtureEntity {

    @Id
    private Integer id;

    @Column(name = "gameweek")
    private Integer gameweek;

    @Column(name = "team_h")
    private Integer homeTeamId;

    @Column(name = "team_a")
    private Integer awayTeamId;

    @Column(name = "team_h_difficulty")
    private Integer homeDifficulty;

    @Column(name = "team_a_difficulty")
    private Integer awayDifficulty;

    @Column(name = "kickoff_time")
    private LocalDateTime kickoffTime;

    @Column(name = "finished")
    private Boolean finished;

    protected FixtureEntity() {
    }

    public FixtureEntity(Integer id, Integer gameweek, Integer homeTeamId,
                         Integer awayTeamId, Integer homeDifficulty,
                         Integer awayDifficulty, LocalDateTime kickoffTime,
                         Boolean finished) {
        this.id = id;
        this.gameweek = gameweek;
        this.homeTeamId = homeTeamId;
        this.awayTeamId = awayTeamId;
        this.homeDifficulty = homeDifficulty;
        this.awayDifficulty = awayDifficulty;
        this.kickoffTime = kickoffTime;
        this.finished = finished;
    }

    public Integer getId() {
        return id;
    }

    public Integer getGameweek() {
        return gameweek;
    }

    public Integer getHomeTeamId() {
        return homeTeamId;
    }

    public Integer getAwayTeamId() {
        return awayTeamId;
    }

    public Integer getHomeDifficulty() {
        return homeDifficulty;
    }

    public Integer getAwayDifficulty() {
        return awayDifficulty;
    }

    public LocalDateTime getKickoffTime() {
        return kickoffTime;
    }

    public Boolean getFinished() {
        return finished;
    }
}
