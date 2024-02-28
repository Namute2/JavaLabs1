package com.geor2.activityApi.controller;


import com.geor2.activityApi.service.ActivityService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class ActivityController {

    private final ActivityService activityService;

    @GetMapping("/{action}")
    public String apiAction(@PathVariable String action) {
    return activityService.getInf(action);
    }
}
