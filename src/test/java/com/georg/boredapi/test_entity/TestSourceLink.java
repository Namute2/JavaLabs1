package com.georg.boredapi.test_entity;

import com.georg.boredapi.entity.Activity;
import com.georg.boredapi.entity.SourceLink;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TestSourceLink {

    private SourceLink sourceLink;

    @BeforeEach
    void setUp() {
        sourceLink = new SourceLink();
    }

    @Test
    void testDefaultConstructor() {
        assertNotNull(sourceLink);
    }

    @Test
    void testIdGetterAndSetter() {
        Long id = 1L;
        sourceLink.setId(id);

        assertEquals(id, sourceLink.getId());
    }

    @Test
    void testLinkGetterAndSetter() {
        String link = "https://example.com";
        sourceLink.setLink(link);

        assertEquals(link, sourceLink.getLink());
    }

    @Test
    void testActivityGetterAndSetter() {
        Activity activity = new Activity(1L, "Test Activity");
        sourceLink.setActivity(activity);

        assertEquals(activity, sourceLink.getActivity());
    }

    @Test
    void testEquals() {
        String link = "https://example.com";
        SourceLink sourceLink1 = new SourceLink();
        SourceLink sourceLink2 = new SourceLink();
        SourceLink sourceLink3 = new SourceLink();
        sourceLink1.setLink(link);
        sourceLink2.setLink(link);
        sourceLink3.setLink("https://anotherexample.com");

        assertEquals(sourceLink1, sourceLink1);
        assertEquals(sourceLink1, sourceLink2);
        assertEquals(sourceLink2, sourceLink1);
        assertNotEquals(sourceLink1, sourceLink3);
        assertNotEquals(sourceLink3, sourceLink1);
        assertNotEquals(null, sourceLink1);
        assertNotEquals("https://example.com", sourceLink1);
    }

    @Test
    void testHashCode() {
        String link = "https://example.com";
        SourceLink sourceLink1 = new SourceLink();
        SourceLink sourceLink2 = new SourceLink();
        SourceLink sourceLink3 = new SourceLink();
        sourceLink1.setLink(link);
        sourceLink2.setLink(link);
        sourceLink3.setLink("https://anotherexample.com");

        assertEquals(sourceLink1.hashCode(), sourceLink1.hashCode());
        assertEquals(sourceLink1.hashCode(), sourceLink2.hashCode());
        assertNotEquals(sourceLink1.hashCode(), sourceLink3.hashCode());
    }
}