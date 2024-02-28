package com.Geor.ActivityApi.model;

import lombok.*;


@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Activity {
    private String activity;
    private int accessibility;
    private String type;
    private int participants;
    private int price;
    private int key;
}
