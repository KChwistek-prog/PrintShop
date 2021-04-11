package com.printshopmanagement.application.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.sql.Timestamp;

@Getter
@AllArgsConstructor
public class TaskDto {
    private Integer id;
    private String taskName;
    private String taskType;
    private String taskStatus;
    private String taskComment;
    private Timestamp taskAcceptationDate;
    private Timestamp taskRealisationTerm;
}
