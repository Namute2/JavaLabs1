package com.georg.boredapi.controller;

import com.georg.boredapi.entity.SourceLink;
import com.georg.boredapi.service.SourceService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** The type Source controller. */
@RestController
@RequestMapping("/api/v1/source")
public class SourceController {

  private final SourceService sourceService;

  /**
   * Instantiates a new Source controller.
   *
   * @param sourceService the source service
   */
  public SourceController(SourceService sourceService) {
    this.sourceService = sourceService;
  }

  /**
   * Gets all source links.
   *
   * @return the all source links
   */
  @GetMapping
  public ResponseEntity<List<SourceLink>> getAllSourceLinks() {
    List<SourceLink> sourceLinks = sourceService.getAllSourceLinks();
    return ResponseEntity.ok(sourceLinks);
  }

  /**
   * Gets source link by id.
   *
   * @param id the id
   * @return the source link by id
   */
  @GetMapping("/{id}")
  public ResponseEntity<SourceLink> getSourceLinkById(@PathVariable Long id) {
    SourceLink sourceLink = sourceService.getSourceLinkById(id);
    if (sourceLink != null) {
      return ResponseEntity.ok(sourceLink);
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  /**
   * Add source link response entity.
   *
   * @param sourceLink the source link
   * @return the response entity
   */
  @PostMapping
  public ResponseEntity<SourceLink> addSourceLink(@RequestBody SourceLink sourceLink) {
    SourceLink savedSourceLink = sourceService.addSourceLink(sourceLink);
    return ResponseEntity.status(HttpStatus.CREATED).body(savedSourceLink);
  }

  /**
   * Update source link response entity.
   *
   * @param id the id
   * @param updatedSourceLink the updated source link
   * @return the response entity
   */
  @PutMapping("/{id}")
  public ResponseEntity<SourceLink> updateSourceLink(
      @PathVariable Long id, @RequestBody SourceLink updatedSourceLink) {
    updatedSourceLink.setId(id);
    SourceLink updatedLink = sourceService.updateSourceLink(updatedSourceLink);
    if (updatedLink != null) {
      return ResponseEntity.ok(updatedLink);
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  /**
   * Delete source link by id response entity.
   *
   * @param id the id
   * @return the response entity
   */
  @DeleteMapping("/{id}")
  public ResponseEntity<SourceLink> deleteSourceLinkById(@PathVariable Long id) {
    return ResponseEntity.ok(sourceService.deleteSourceLinkById(id));
  }
}
