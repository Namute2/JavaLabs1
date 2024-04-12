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

    @Test
    void testEquals() {
        String name = "Test Activity";
        Activity activity1 = new Activity(1L, name);
        Activity activity2 = new Activity(2L, name);
        Activity activity3 = new Activity(1L, "Different Activity");

        assertEquals(activity1, activity1);
        assertEquals(activity3, activity3);
        assertEquals(activity3, activity1);
        assertNotEquals(activity1, activity2);
        assertNotEquals(activity2, activity1);
        assertNotEquals(activity1, null);
        assertNotEquals(activity1, "Test Activity");
    }

    @Test
    void testHashCode() {
        String name = "Test Activity";
        Activity activity1 = new Activity(1L, name);
        Activity activity2 = new Activity(2L, name);
        Activity activity3 = new Activity(1L, "Different Activity");

        assertEquals(activity1.hashCode(), activity1.hashCode());
        assertEquals(activity3.hashCode(), activity1.hashCode());
        assertNotEquals(activity1.hashCode(), activity2.hashCode());
    }
}