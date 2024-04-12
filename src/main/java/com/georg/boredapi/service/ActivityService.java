package com.georg.boredapi.service;

import com.georg.boredapi.cache.MyCache;
import com.georg.boredapi.entity.Activity;
import com.georg.boredapi.entity.SourceLink;
import com.georg.boredapi.repository.ActivityRepository;
import com.georg.boredapi.repository.SourceRepository;
import java.util.List;
import java.util.Optional;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/** The type Activity service. */
@Service
@AllArgsConstructor
public class ActivityService {

  private final MyCache<Long, Activity> cache = new MyCache<>();
  private static String urlApi = "https://www.boredapi.com/api/{action}";
  private final RestTemplate restTemplate;
  private final ActivityRepository activityRepository;
  private final SourceRepository sourceRepository;

  /**
   * Gets inf.
   *
   * @param action the action
   * @return the inf
   */
  public String getInf(String action) {
    String url = urlApi.replace("{action}", action);
    return restTemplate.getForObject(url, String.class);
  }

  /**
   * Add activity activity.
   *
   * @param activity the activity
   * @return the activity
   */
  public Activity addActivity(Activity activity) {
    Activity savedActivity = activityRepository.save(activity);
    for (SourceLink sourceLink : activity.getSourceList()) {
      sourceLink.setActivity(savedActivity);
      sourceRepository.save(sourceLink);
    }
    return savedActivity;
  }

  /**
   * Gets activity.
   *
   * @return the activity
   */
  public List<Activity> getActivity() {
    return activityRepository.findAll();
  }

  /**
   * Gets activity by id.
   *
   * @param id the id
   * @return the activity by id
   */
  public Activity getActivityById(Long id) {
    Activity activity = cache.get(id);
    if (activity == null) {
      activity = activityRepository.findById(id).orElse(null);
      if (activity != null) {
        cache.put(id, activity);
      }
    }
    return activity;
  }

  /**
   * Update activity activity.
   *
   * @param updatedActivity the updated activity
   * @return the activity
   */
  public Activity updateActivity(Activity updatedActivity) {
    Optional<Activity> existingActivityOptional =
        activityRepository.findById(updatedActivity.getId());
    if (existingActivityOptional.isPresent()) {
      Activity existingActivity = existingActivityOptional.get();
      existingActivity.setName(updatedActivity.getName());
      return activityRepository.save(existingActivity);
    }
    return null;
  }

  /**
   * Delete activity by id.
   *
   * @param id the id
   */
  public void deleteActivityById(Long id) {
    activityRepository.deleteById(id);
    cache.remove(id);
  }

  /**
   * Gets activities by source link.
   *
   * @param sourceLink the source link
   * @return the activities by source link
   */
  public List<Activity> getActivitiesBySourceLink(String sourceLink) {
    return activityRepository.findBySourceLink(sourceLink);
  }

  public List<Activity> performBulkActivityOperation(List<Activity> activities) {
    return activities.stream()
            .map(this::addActivity)
            .toList();
  }
}
