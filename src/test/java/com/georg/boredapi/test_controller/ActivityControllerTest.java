package com.georg.boredapi.test_controller;

import com.georg.boredapi.controller.ActivityController;
import com.georg.boredapi.entity.Activity;
import com.georg.boredapi.service.ActivityService;
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
import static org.mockito.Mockito.*;

class ActivityControllerTest {

    @Mock
    private ActivityService activityService;

    private ActivityController activityController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        activityController = new ActivityController(activityService);
    }

    @Test
    void testGetAllActivities() {
        List<Activity> activities = new ArrayList<>();
        activities.add(new Activity(1L, "Activity 1"));
        activities.add(new Activity(2L, "Activity 2"));

        when(activityService.getActivity()).thenReturn(activities);

        ResponseEntity<List<Activity>> response = activityController.getAllActivities();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().size());
    }

    @Test
    void testGetActivityById() {
        Long id = 1L;
        Activity activity = new Activity(id, "Activity 1");

        when(activityService.getActivityById(id)).thenReturn(activity);

        ResponseEntity<Activity> response = activityController.getActivityById(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(id, response.getBody().getId());
        assertEquals("Activity 1", response.getBody().getName());
    }

    @Test
    void testGetActivityById_NotFound() {
        Long id = 1L;

        when(activityService.getActivityById(id)).thenReturn(null);

        ResponseEntity<Activity> response = activityController.getActivityById(id);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void testAddActivity() {
        Activity activity = new Activity(1L, "Activity 1");

        when(activityService.addActivity(activity)).thenReturn(activity);

        ResponseEntity<Activity> response = activityController.addActivity(activity);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1L, response.getBody().getId());
        assertEquals("Activity 1", response.getBody().getName());
    }

    @Test
    void testUpdateActivity() {
        Long id = 1L;
        Activity updatedActivity = new Activity(id, "Updated Activity");
        Activity savedActivity = new Activity(id, "Saved Activity");

        when(activityService.updateActivity(updatedActivity)).thenReturn(savedActivity);

        ResponseEntity<Activity> response = activityController.updateActivity(id, updatedActivity);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(id, response.getBody().getId());
        assertEquals("Saved Activity", response.getBody().getName());
    }

    @Test
    void testUpdateActivity_NotFound() {
        Long id = 1L;
        Activity updatedActivity = new Activity(id, "Updated Activity");

        when(activityService.updateActivity(updatedActivity)).thenReturn(null);

        ResponseEntity<Activity> response = activityController.updateActivity(id, updatedActivity);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void testDeleteActivityById() {
        Long id = 1L;

        ResponseEntity<Void> response = activityController.deleteActivityById(id);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(activityService, times(1)).deleteActivityById(id);
    }

    @Test
    void testGetActivitiesBySourceLink() {
        String sourceLink = "https://example.com";

        List<Activity> activities = new ArrayList<>();
        activities.add(new Activity(1L, "Activity 1"));
        activities.add(new Activity(2L, "Activity 2"));

        when(activityService.getActivitiesBySourceLink(sourceLink)).thenReturn(activities);

        List<Activity> response = activityController.getActivitiesBySourceLink(sourceLink);

        assertNotNull(response);
        assertEquals(2, response.size());
    }

    @Test
    void testPerformBulkCountryOperation() {
        List<Activity> activities = new ArrayList<>();
        activities.add(new Activity(1L, "Activity 1"));
        activities.add(new Activity(2L, "Activity 2"));

        when(activityService.performBulkActivityOperation(activities)).thenReturn(activities);

        ResponseEntity<List<Activity>> response = activityController.performBulkCountryOperation(activities);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().size());
    }
}