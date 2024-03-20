package com.georg.boredapi.service;


import com.georg.boredapi.entity.SourceLink;
import com.georg.boredapi.repository.SourceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SourceService {
    private final SourceRepository sourceRepository;

    public List<SourceLink> getAllSourceLinks() {
        return sourceRepository.findAll();
    }

    public SourceLink getSourceLinkById(Long id) {
        Optional<SourceLink> sourceLinkOptional = sourceRepository.findById(id);
        return sourceLinkOptional.orElse(null);
    }

    public SourceLink addSourceLink(SourceLink sourceLink) {
        return sourceRepository.save(sourceLink);
    }

    public SourceLink updateSourceLink(SourceLink updatedSourceLink) {
        Optional<SourceLink> existingSourceLinkOptional = sourceRepository.findById(updatedSourceLink.getId());
        if (existingSourceLinkOptional.isPresent()) {
            SourceLink existingSourceLink = existingSourceLinkOptional.get();
            existingSourceLink.setLink(updatedSourceLink.getLink());
            return sourceRepository.save(existingSourceLink);
        }
        return null;
    }

    public SourceLink deleteSourceLinkById(Long id) {
        SourceLink sourceLink = sourceRepository.findById(id).orElse(null);
        if (sourceLink  != null){
            sourceRepository.delete(sourceLink);
        }
        return sourceLink;
    }
}
