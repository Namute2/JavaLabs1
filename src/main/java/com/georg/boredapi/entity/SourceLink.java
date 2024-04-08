package com.georg.boredapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.util.Objects;

/** The type Source link. */
@Entity
public class SourceLink {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String link;

  @ManyToOne
  @JoinColumn(name = "activity_id")
  @JsonIgnore
  private Activity activity;

  /** Instantiates a new Source link. */
  public SourceLink() {
    // No initialization logic needed for this constructor
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SourceLink that = (SourceLink) o;
    return link.equals(that.link);
  }

  @Override
  public int hashCode() {
    return Objects.hash(link);
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
   * Gets link.
   *
   * @return the link
   */
  public String getLink() {
    return link;
  }

  /**
   * Sets link.
   *
   * @param link the link
   */
  public void setLink(String link) {
    this.link = link;
  }

  /**
   * Gets activity.
   *
   * @return the activity
   */
  public Activity getActivity() {
    return activity;
  }

  /**
   * Sets activity.
   *
   * @param activity the activity
   */
  public void setActivity(Activity activity) {
    this.activity = activity;
  }
}
