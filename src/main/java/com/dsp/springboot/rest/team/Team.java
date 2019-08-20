package com.dsp.springboot.rest.team;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int yearFounded;
    private int stadiumCapacity;

    public Team() {
        super();
    }

    public Team(Long id, String name, int yearFounded, int stadiumCapacity) {
        super();
        this.id = id;
        this.name = name;
        this.yearFounded = yearFounded;
        this.stadiumCapacity = stadiumCapacity;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String toString() {
        return this.name;
    }

    public int getYearFounded()
    {
        return yearFounded;
    }

    public void setYearFounded(int yearFounded)
    {
        this.yearFounded = yearFounded;
    }

    public int getStadiumCapacity()
    {
        return stadiumCapacity;
    }

    public void setStadiumCapacity(int stadiumCapacity)
    {
        this.stadiumCapacity = stadiumCapacity;
    }
}
