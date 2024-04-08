package com.georg.boredapi.repository;

import com.georg.boredapi.entity.SourceLink;
import org.springframework.data.jpa.repository.JpaRepository;

/** The interface Source repository. */
public interface SourceRepository extends JpaRepository<SourceLink, Long> {
  /**
   * Find by link source link.
   *
   * @param link the link
   * @return the source link
   */
  SourceLink findByLink(String link);
}
