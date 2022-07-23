package com.example.project_01.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class WifiHistory {

    private Integer id;
    private String lat;
    private String lnt;
    private LocalDateTime regDate;
}
