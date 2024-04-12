package com.georg.boredapi.test_entity;

import com.georg.boredapi.entity.Activity;
import com.georg.boredapi.entity.SourceLink;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TestActivity {

    private Activity activity;

    @BeforeEach
    void setUp() {
        activity = new Activity();
    }

    @Test
    void testDefaultConstructor() {
        assertNotNull(activity);
    }

    @Test
    void testParameterizedConstructor() {
        Long id = 1L;
        String name = "Test Activity";
        activity = new Activity(id, name);

        assertEquals(id, activity.getId());
        assertEquals(name, activity.getName());
    }

    @Test
    void testIdGetterAndSetter() {
        Long id = 1L;
        activity.setId(id);

        assertEquals(id, activity.getId());
    }

    @Test
    void testNameGetterAndSetter() {
        String name = "Test Activity";
        activity.setName(name);

        assertEquals(name, activity.getName());
    }
    
}