package io.github.braalex.fpl.infrastructure.persistence.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "players")
public class PlayerEntity {

    @Id
    private Integer id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "second_name")
    private String secondName;

    @Column(name = "web_name")
    private String webName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id", referencedColumnName = "id")
    private TeamEntity team;

    @Column(name = "element_type")
    private Integer elementType;

    @Column(name = "now_cost")
    private Integer nowCost;

    @Column(name = "selected_by_percent")
    private Double selectedByPercent;

    @Column(name = "total_points")
    private Integer totalPoints;

    @Column(name = "form")
    private Double form;

    @Column(name = "transfers_in")
    private Integer transfersIn;

    @Column(name = "transfers_out")
    private Integer transfersOut;

    @Column(name = "minutes")
    private Integer minutes;

    @Column(name = "goals_scored")
    private Integer goalsScored;

    @Column(name = "assists")
    private Integer assists;

    @Column(name = "clean_sheets")
    private Integer cleanSheets;

    @Column(name = "status")
    private String status;

    @Column(name = "news")
    private String news;

    protected PlayerEntity() {
    }

    public PlayerEntity(Integer id, String firstName, String secondName, String webName,
                        TeamEntity team, Integer elementType, Integer nowCost,
                        Double selectedByPercent, Integer totalPoints, Double form,
                        Integer transfersIn, Integer transfersOut, Integer minutes,
                        Integer goalsScored, Integer assists, Integer cleanSheets,
                        String status, String news) {
        this.id = id;
        this.firstName = firstName;
        this.secondName = secondName;
        this.webName = webName;
        this.team = team;
        this.elementType = elementType;
        this.nowCost = nowCost;
        this.selectedByPercent = selectedByPercent;
        this.totalPoints = totalPoints;
        this.form = form;
        this.transfersIn = transfersIn;
        this.transfersOut = transfersOut;
        this.minutes = minutes;
        this.goalsScored = goalsScored;
        this.assists = assists;
        this.cleanSheets = cleanSheets;
        this.status = status;
        this.news = news;
    }

    public Integer getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public String getWebName() {
        return webName;
    }

    public TeamEntity getTeam() {
        return team;
    }

    public Integer getElementType() {
        return elementType;
    }

    public Integer getNowCost() {
        return nowCost;
    }

    public Double getSelectedByPercent() {
        return selectedByPercent;
    }

    public Integer getTotalPoints() {
        return totalPoints;
    }

    public Double getForm() {
        return form;
    }

    public Integer getTransfersIn() {
        return transfersIn;
    }

    public Integer getTransfersOut() {
        return transfersOut;
    }

    public Integer getMinutes() {
        return minutes;
    }

    public Integer getGoalsScored() {
        return goalsScored;
    }

    public Integer getAssists() {
        return assists;
    }

    public Integer getCleanSheets() {
        return cleanSheets;
    }

    public String getStatus() {
        return status;
    }

    public String getNews() {
        return news;
    }
}
