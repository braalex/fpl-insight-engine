package io.github.braalex.fpl.infrastructure.persistence.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "teams")
public class TeamEntity {

    @Id
    private Integer id;
    private String name;
    private Integer strength;

    protected TeamEntity() {
    }

    public TeamEntity(Integer id, String name, Integer strength) {
        this.id = id;
        this.name = name;
        this.strength = strength;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getStrength() {
        return strength;
    }
}
