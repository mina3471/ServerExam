package com.practice;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RestDTO {
    private String storeName;
    private String address;
    private String language;
    private int count;
}
