package com.georg.boredapi.service;

import com.georg.boredapi.entity.SourceLink;
import com.georg.boredapi.repository.SourceRepository;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/** The type Source service. */
@Service
@AllArgsConstructor
public class SourceService {
  private final SourceRepository sourceRepository;

  /**
   * Gets all source links.
   *
   * @return the all source links
   */
  public List<SourceLink> getAllSourceLinks() {
    return sourceRepository.findAll();
  }

  /**
   * Gets source link by id.
   *
   * @param id the id
   * @return the source link by id
   */
  public SourceLink getSourceLinkById(Long id) {
    Optional<SourceLink> sourceLinkOptional = sourceRepository.findById(id);
    return sourceLinkOptional.orElse(null);
  }

  /**
   * Add source link source link.
   *
   * @param sourceLink the source link
   * @return the source link
   */
  public SourceLink addSourceLink(SourceLink sourceLink) {
    return sourceRepository.save(sourceLink);
  }

  /**
   * Update source link source link.
   *
   * @param updatedSourceLink the updated source link
   * @return the source link
   */
  public SourceLink updateSourceLink(SourceLink updatedSourceLink) {
    Optional<SourceLink> existingSourceLinkOptional =
        sourceRepository.findById(updatedSourceLink.getId());
    if (existingSourceLinkOptional.isPresent()) {
      SourceLink existingSourceLink = existingSourceLinkOptional.get();
      existingSourceLink.setLink(updatedSourceLink.getLink());
      return sourceRepository.save(existingSourceLink);
    }
    return null;
  }

  /**
   * Delete source link by id source link.
   *
   * @param id the id
   * @return the source link
   */
  public SourceLink deleteSourceLinkById(Long id) {
    SourceLink sourceLink = sourceRepository.findById(id).orElse(null);
    if (sourceLink != null) {
      sourceRepository.delete(sourceLink);
    }
    return sourceLink;
  }
}
