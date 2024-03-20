package com.georg.boredapi.service;


import com.georg.boredapi.entity.Activity;
import com.georg.boredapi.entity.SourceLink;
import com.georg.boredapi.repository.ActivityRepository;
import com.georg.boredapi.repository.SourceRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ActivityService {

    private static String urlApi = "https://www.boredapi.com/api/{action}";
    private final RestTemplate restTemplate;
    private final ActivityRepository activityRepository;
    private final SourceRepository sourceRepository;

    public String getInf(String action) {
        String url = urlApi.replace("{action}", action);
        return restTemplate.getForObject(url, String.class);
    }

    public Activity addActivity(Activity activity) {
        Activity savedActivity = activityRepository.save(activity);
        for (SourceLink sourceLink : activity.getSourceList()) {
            sourceLink.setActivity(savedActivity);
            sourceRepository.save(sourceLink);
        }
        return savedActivity;
    }

    public List<Activity> getActivity() {
        return activityRepository.findAll();
    }

    public Activity getActivityById(Long id) {
        Optional<Activity> activityOptional = activityRepository.findById(id);
        return activityOptional.orElse(null);
    }

    public Activity updateActivity(Activity updatedActivity) {
        Optional<Activity> existingActivityOptional = activityRepository.findById(updatedActivity.getId());
        if (existingActivityOptional.isPresent()) {
            Activity existingActivity = existingActivityOptional.get();
            existingActivity.setActivName(updatedActivity.getActivName());
            return activityRepository.save(existingActivity);
        }
        return null;
    }

    public void deleteActivityById(Long id) {
        activityRepository.deleteById(id);
    }
}
