package com.georg.boredapi.controller;

import com.georg.boredapi.service.SourceService;
import com.georg.boredapi.entity.SourceLink;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/source")
public class SourceController {

    private final SourceService sourceService;

    public SourceController(SourceService sourceService) {
        this.sourceService = sourceService;
    }

    @GetMapping
    public ResponseEntity<List<SourceLink>> getAllSourceLinks() {
        List<SourceLink> sourceLinks = sourceService.getAllSourceLinks();
        return ResponseEntity.ok(sourceLinks);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SourceLink> getSourceLinkById(@PathVariable Long id) {
        SourceLink sourceLink = sourceService.getSourceLinkById(id);
        if (sourceLink != null) {
            return ResponseEntity.ok(sourceLink);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<SourceLink> addSourceLink(@RequestBody SourceLink sourceLink) {
        SourceLink savedSourceLink = sourceService.addSourceLink(sourceLink);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedSourceLink);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SourceLink> updateSourceLink(@PathVariable Long id, @RequestBody SourceLink updatedSourceLink) {
        updatedSourceLink.setId(id);
        SourceLink updatedLink = sourceService.updateSourceLink(updatedSourceLink);
        if (updatedLink != null) {
            return ResponseEntity.ok(updatedLink);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SourceLink> deleteSourceLinkById(@PathVariable Long id) {
        return ResponseEntity.ok(sourceService.deleteSourceLinkById(id));
    }
}
