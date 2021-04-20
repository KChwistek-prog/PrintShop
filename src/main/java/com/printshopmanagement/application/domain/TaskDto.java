package com.printshopmanagement.application.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TaskDto {
    private Long id;
    private String taskName;
    private String taskType;
    private String taskStatus;
    private String taskComment;
    private Timestamp taskAcceptationDate;
    private Timestamp taskDeadline;
}
