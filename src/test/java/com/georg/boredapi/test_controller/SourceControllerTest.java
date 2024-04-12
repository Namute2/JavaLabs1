package com.georg.boredapi.test_controller;

import com.georg.boredapi.controller.SourceController;
import com.georg.boredapi.entity.SourceLink;
import com.georg.boredapi.service.SourceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

class SourceControllerTest {

    @Mock
    private SourceService sourceService;

    private SourceController sourceController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        sourceController = new SourceController(sourceService);
    }

    @Test
    void testGetSourceLinkById() {
        Long id = 1L;
        SourceLink sourceLink = new SourceLink();
        sourceLink.setId(id);
        sourceLink.setLink("Link 1");

        when(sourceService.getSourceLinkById(id)).thenReturn(sourceLink);

        ResponseEntity<SourceLink> response = sourceController.getSourceLinkById(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(id, response.getBody().getId());
        assertEquals("Link 1", response.getBody().getLink());
    }

    @Test
    void testGetSourceLinkById_NotFound() {
        Long id = 1L;

        when(sourceService.getSourceLinkById(id)).thenReturn(null);

        ResponseEntity<SourceLink> response = sourceController.getSourceLinkById(id);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void testAddSourceLink() {
        SourceLink sourceLink = new SourceLink();
        sourceLink.setId(1L);
        sourceLink.setLink("Link 1");

        when(sourceService.addSourceLink(sourceLink)).thenReturn(sourceLink);

        ResponseEntity<SourceLink> response = sourceController.addSourceLink(sourceLink);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1L, response.getBody().getId());
        assertEquals("Link 1", response.getBody().getLink());
    }

    @Test
    void testUpdateSourceLink() {
        Long id = 1L;
        SourceLink updatedSourceLink = new SourceLink();
        updatedSourceLink.setId(id);
        updatedSourceLink.setLink("Updated Link");
        SourceLink updatedLink = new SourceLink();
        updatedLink.setId(id);
        updatedLink.setLink("Updated Link");

        when(sourceService.updateSourceLink(updatedSourceLink)).thenReturn(updatedLink);

        ResponseEntity<SourceLink> response = sourceController.updateSourceLink(id, updatedSourceLink);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(id, response.getBody().getId());
        assertEquals("Updated Link", response.getBody().getLink());
    }

    @Test
    void testUpdateSourceLink_NotFound() {
        Long id = 1L;
        SourceLink updatedSourceLink = new SourceLink();
        updatedSourceLink.setId(id);
        updatedSourceLink.setLink("Updated Link");

        when(sourceService.updateSourceLink(updatedSourceLink)).thenReturn(null);

        ResponseEntity<SourceLink> response = sourceController.updateSourceLink(id, updatedSourceLink);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void testDeleteSourceLinkById() {
        Long id = 1L;
        SourceLink deletedSourceLink = new SourceLink();
        deletedSourceLink.setId(id);
        deletedSourceLink.setLink("Deleted Link");

        when(sourceService.deleteSourceLinkById(id)).thenReturn(deletedSourceLink);

        ResponseEntity<SourceLink> response = sourceController.deleteSourceLinkById(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(id, response.getBody().getId());
        assertEquals("Deleted Link", response.getBody().getLink());
    }
}