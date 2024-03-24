package com.georg.boredapi.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;



    @OneToMany(mappedBy = "activity")
    private List<SourceLink> sourceList = new ArrayList<>();


    public Activity() {
    }

    public Activity(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Activity activity = (Activity) o;
        return name.equals(activity.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
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

    public void setName(String activName) {
        this.name = activName;
    }

    public List<SourceLink> getSourceList() {
        return sourceList;
    }

    public void setSourceList(List<SourceLink> sourceList) {
        this.sourceList = sourceList;
    }
}


