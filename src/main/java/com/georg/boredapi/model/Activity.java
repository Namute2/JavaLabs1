package com.georg.boredapi.model;

import lombok.*;


@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Activity {
    private String deed;
    private int accessibility;
    private String type;
    private int participants;
    private int price;
    private int key;
}
