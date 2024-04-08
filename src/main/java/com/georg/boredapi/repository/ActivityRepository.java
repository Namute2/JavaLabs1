package com.georg.boredapi.repository;

import com.georg.boredapi.entity.Activity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/** The interface Activity repository. */
@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long> {
  /**
   * Find by source link list.
   *
   * @param sourceLink the source link
   * @return the list
   */
  @Query(value = "SELECT a FROM Activity a JOIN a.sourceList sl WHERE sl.link = :sourceLink")
  List<Activity> findBySourceLink(@Param("sourceLink") String sourceLink);
}
