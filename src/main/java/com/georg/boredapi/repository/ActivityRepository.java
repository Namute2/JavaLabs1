package com.georg.boredapi.repository;


import com.georg.boredapi.entity.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long> {
    @Query("SELECT a FROM Activity a JOIN a.sourceList sl WHERE sl.link = :sourceLink")
    List<Activity> findBySourceLink(@Param("sourceLink") String sourceLink);
}