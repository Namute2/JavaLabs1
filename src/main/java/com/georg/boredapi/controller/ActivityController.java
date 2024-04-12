package com.georg.boredapi.controller;

import com.georg.boredapi.entity.Activity;
import com.georg.boredapi.service.ActivityService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/** The type Activity controller. */
@RestController
@RequestMapping("/api/v1/activity")
public class ActivityController {
  private final ActivityService activityService;

  /**
   * Instantiates a new Activity controller.
   *
   * @param activityService the activity service
   */
  public ActivityController(ActivityService activityService) {
    this.activityService = activityService;
  }

  /**
   * Gets all activities.
   *
   * @return the all activities
   */
  @GetMapping
  public ResponseEntity<List<Activity>> getAllActivities() {
    List<Activity> activities = activityService.getActivity();
    return ResponseEntity.ok(activities);
  }

  /**
   * Gets activity by id.
   *
   * @param id the id
   * @return the activity by id
   */
  @GetMapping("/{id}")
  public ResponseEntity<Activity> getActivityById(@PathVariable Long id) {
    Activity activity = activityService.getActivityById(id);
    if (activity != null) {
      return ResponseEntity.ok(activity);
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  /**
   * Add activity response entity.
   *
   * @param activity the activity
   * @return the response entity
   */
  @PostMapping
  public ResponseEntity<Activity> addActivity(@RequestBody Activity activity) {
    Activity savedActivity = activityService.addActivity(activity);
    return ResponseEntity.status(HttpStatus.CREATED).body(savedActivity);
  }

  /**
   * Update activity response entity.
   *
   * @param id the id
   * @param updatedActivity the updated activity
   * @return the response entity
   */
  @PutMapping("/{id}")
  public ResponseEntity<Activity> updateActivity(
      @PathVariable Long id, @RequestBody Activity updatedActivity) {
    updatedActivity.setId(id);
    Activity updatedAct = activityService.updateActivity(updatedActivity);
    if (updatedAct != null) {
      return ResponseEntity.ok(updatedAct);
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  /**
   * Delete activity by id response entity.
   *
   * @param id the id
   * @return the response entity
   */
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteActivityById(@PathVariable Long id) {
    activityService.deleteActivityById(id);
    return ResponseEntity.noContent().build();
  }

  /**
   * Gets activities by source link.
   *
   * @param sourceLink the source link
   * @return the activities by source link
   */
  @GetMapping("/link")
  public List<Activity> getActivitiesBySourceLink(@RequestParam("sourceLink") String sourceLink) {
    return activityService.getActivitiesBySourceLink(sourceLink);
  }

  @PostMapping("/bulk")
  public ResponseEntity<List<Activity>> performBulkCountryOperation(@RequestBody List<Activity> activities) {
    List<Activity> createdActivities = activityService.performBulkActivityOperation(activities);
    return ResponseEntity.ok(createdActivities);
  }
}
