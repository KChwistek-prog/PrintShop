package com.printshopmanagement.application.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TaskDto {
    private Long id;
    private String taskName;
    private String taskType;
    private String taskStatus;
    private String taskComment;
}
