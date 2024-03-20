package com.georg.boredapi.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String activName;


    @OneToMany(mappedBy = "activity")
    private List<SourceLink> sourceList = new ArrayList<>();


    public Activity() {
    }

    public Activity(Long id, String activName) {
        this.id = id;
        this.activName = activName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getActivName() {
        return activName;
    }

    public void setActivName(String activName) {
        this.activName = activName;
    }

    public List<SourceLink> getSourceList() {
        return sourceList;
    }

    public void setSourceList(List<SourceLink> sourceList) {
        this.sourceList = sourceList;
    }
}


