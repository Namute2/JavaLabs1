package com.georg.boredapi.repository;

import com.georg.boredapi.entity.SourceLink;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SourceRepository extends JpaRepository<SourceLink, Long> {
    SourceLink findByLink(String link);
}
