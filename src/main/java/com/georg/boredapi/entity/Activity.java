package com.georg.boredapi.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/** The type Activity. */
@Entity
public class Activity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  @OneToMany(mappedBy = "activity")
  private List<SourceLink> sourceList = new ArrayList<>();

  /** Instantiates a new Activity. */
  public Activity() {}

  /**
   * Instantiates a new Activity.
   *
   * @param id the id
   * @param name the name
   */
  public Activity(Long id, String name) {
    this.id = id;
    this.name = name;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Activity activity = (Activity) o;
    return name.equals(activity.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name);
  }

  /**
   * Gets id.
   *
   * @return the id
   */
  public Long getId() {
    return id;
  }

  /**
   * Sets id.
   *
   * @param id the id
   */
  public void setId(Long id) {
    this.id = id;
  }

  /**
   * Gets name.
   *
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * Sets name.
   *
   * @param activName the activ name
   */
  public void setName(String activName) {
    this.name = activName;
  }

  /**
   * Gets source list.
   *
   * @return the source list
   */
  public List<SourceLink> getSourceList() {
    return sourceList;
  }

  /**
   * Sets source list.
   *
   * @param sourceList the source list
   */
  public void setSourceList(List<SourceLink> sourceList) {
    this.sourceList = sourceList;
  }
}
