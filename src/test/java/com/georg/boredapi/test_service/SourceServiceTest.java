package com.georg.boredapi.test_service;

import com.georg.boredapi.entity.SourceLink;
import com.georg.boredapi.repository.SourceRepository;
import com.georg.boredapi.service.SourceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class SourceServiceTest {

    private SourceService sourceService;

    @Mock
    private SourceRepository sourceRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        sourceService = new SourceService(sourceRepository);
    }

    @Test
    void testGetAllSourceLinks() {
        List<SourceLink> expectedSourceLinks = Collections.singletonList(new SourceLink());

        when(sourceRepository.findAll()).thenReturn(expectedSourceLinks);

        List<SourceLink> result = sourceService.getAllSourceLinks();

        assertEquals(expectedSourceLinks, result);
        verify(sourceRepository, times(1)).findAll();
    }

    @Test
    void testGetSourceLinkById() {
        Long id = 1L;
        SourceLink expectedSourceLink = new SourceLink();

        when(sourceRepository.findById((id))).thenReturn(Optional.of(expectedSourceLink));

        SourceLink result = sourceService.getSourceLinkById(id);

        assertEquals(expectedSourceLink, result);
        verify(sourceRepository, times(1)).findById((id));
    }

    @Test
    void testGetSourceLinkByIdNonExisting() {
        Long id = 1L;

        when(sourceRepository.findById((id))).thenReturn(Optional.empty());

        SourceLink result = sourceService.getSourceLinkById(id);

        assertEquals(null, result);
        verify(sourceRepository, times(1)).findById((id));
    }

    @Test
    void testAddSourceLink() {
        SourceLink sourceLink = new SourceLink();

        when(sourceRepository.save((sourceLink))).thenReturn(sourceLink);

        SourceLink result = sourceService.addSourceLink(sourceLink);

        assertEquals(sourceLink, result);
        verify(sourceRepository, times(1)).save((sourceLink));
    }

    @Test
    void testUpdateSourceLink() {
        Long id = 1L;
        SourceLink updatedSourceLink = new SourceLink();
        updatedSourceLink.setId(id);
        updatedSourceLink.setLink("New Link");
        SourceLink existingSourceLink = new SourceLink();
        existingSourceLink.setId(id);

        when(sourceRepository.findById((id))).thenReturn(Optional.of(existingSourceLink));
        when(sourceRepository.save((existingSourceLink))).thenReturn(existingSourceLink);

        SourceLink result = sourceService.updateSourceLink(updatedSourceLink);

        assertEquals(existingSourceLink, result);
        assertEquals(updatedSourceLink.getLink(), existingSourceLink.getLink());
        verify(sourceRepository, times(1)).findById((id));
        verify(sourceRepository, times(1)).save((existingSourceLink));
    }

    @Test
    void testUpdateSourceLinkNonExisting() {
        Long id = 1L;
        SourceLink updatedSourceLink = new SourceLink();
        updatedSourceLink.setId(id);

        when(sourceRepository.findById((id))).thenReturn(Optional.empty());

        SourceLink result = sourceService.updateSourceLink(updatedSourceLink);

        assertEquals(null, result);
        verify(sourceRepository, times(1)).findById((id));
        verify(sourceRepository, never()).save(any());
    }

    @Test
    void testDeleteSourceLinkById() {
        Long id = 1L;
        SourceLink sourceLink = new SourceLink();
        sourceLink.setId(id);

        when(sourceRepository.findById((id))).thenReturn(Optional.of(sourceLink));

        SourceLink result = sourceService.deleteSourceLinkById(id);

        assertEquals(sourceLink, result);
        verify(sourceRepository, times(1)).findById((id));
        verify(sourceRepository, times(1)).delete((sourceLink));
    }

    @Test
    void testDeleteSourceLinkByIdNonExisting() {
        Long id = 1L;

        when(sourceRepository.findById((id))).thenReturn(Optional.empty());

        SourceLink result = sourceService.deleteSourceLinkById(id);

        assertEquals(null, result);
        verify(sourceRepository, times(1)).findById((id));
        verify(sourceRepository, never()).delete(any());
    }
}