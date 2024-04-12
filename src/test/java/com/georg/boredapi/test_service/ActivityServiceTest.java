package com.georg.boredapi.test_service;

import com.georg.boredapi.cache.MyCache;
import com.georg.boredapi.entity.Activity;
import com.georg.boredapi.repository.ActivityRepository;
import com.georg.boredapi.repository.SourceRepository;
import com.georg.boredapi.service.ActivityService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class ActivityServiceTest {

    private ActivityService activityService;

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private ActivityRepository activityRepository;

    @Mock
    private SourceRepository sourceRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        activityService = new ActivityService(restTemplate, activityRepository, sourceRepository);
    }

    @Test
    void testGetInf() {
        String action = "test";
        String expectedUrl = "https://www.boredapi.com/api/test";
        String expectedResult = "Some result";

        when(restTemplate.getForObject((expectedUrl), (String.class))).thenReturn(expectedResult);

        String result = activityService.getInf(action);

        assertEquals(expectedResult, result);
        verify(restTemplate, times(1)).getForObject((expectedUrl), (String.class));
    }

    @Test
    void testAddActivity() {
        Activity activity = new Activity();
        activity.setSourceList(Collections.emptyList());

        when(activityRepository.save((activity))).thenReturn(activity);

        Activity result = activityService.addActivity(activity);

        assertEquals(activity, result);
        verify(activityRepository, times(1)).save((activity));
        verify(sourceRepository, never()).save(any());
    }

    @Test
    void testGetActivity() {
        List<Activity> expectedActivities = Collections.singletonList(new Activity());

        when(activityRepository.findAll()).thenReturn(expectedActivities);

        List<Activity> result = activityService.getActivity();

        assertEquals(expectedActivities, result);
        verify(activityRepository, times(1)).findAll();
    }

    @Test
    void testGetActivityByIdFromRepository() {
        Long id = 1L;
        Activity expectedActivity = new Activity();

        when(activityRepository.findById((id))).thenReturn(Optional.of(expectedActivity));

        Activity result = activityService.getActivityById(id);

        assertEquals(expectedActivity, result);
        verify(activityRepository, times(1)).findById((id));
    }

    @Test
    void testUpdateActivity() {
        Long id = 1L;
        Activity updatedActivity = new Activity();
        updatedActivity.setId(id);
        updatedActivity.setName("New Name");
        Activity existingActivity = new Activity();
        existingActivity.setId(id);

        when(activityRepository.findById((id))).thenReturn(Optional.of(existingActivity));
        when(activityRepository.save((existingActivity))).thenReturn(existingActivity);

        Activity result = activityService.updateActivity(updatedActivity);

        assertEquals(existingActivity, result);
        assertEquals(updatedActivity.getName(), existingActivity.getName());
        verify(activityRepository, times(1)).findById((id));
        verify(activityRepository, times(1)).save((existingActivity));
    }

    @Test
    void testUpdateActivityNonExisting() {
        Long id = 1L;
        Activity updatedActivity = new Activity();
        updatedActivity.setId(id);

        when(activityRepository.findById((id))).thenReturn(Optional.empty());

        Activity result = activityService.updateActivity(updatedActivity);

        assertEquals(null, result);
        verify(activityRepository, times(1)).findById((id));
        verify(activityRepository, never()).save(any());
    }

    @Test
    void testGetActivitiesBySourceLink() {
        String sourceLink = "http://example.com";
        List<Activity> expectedActivities = Collections.singletonList(new Activity());

        when(activityRepository.findBySourceLink((sourceLink))).thenReturn(expectedActivities);

        List<Activity> result = activityService.getActivitiesBySourceLink(sourceLink);

        assertEquals(expectedActivities, result);
        verify(activityRepository, times(1)).findBySourceLink((sourceLink));
    }

}