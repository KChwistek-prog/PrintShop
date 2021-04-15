package com.printshopmanagement.application.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.sql.Timestamp;

@Getter
@AllArgsConstructor
public class TaskDto {
    private final Long id;
    private final String taskName;
    private final String taskType;
    private final String taskStatus;
    private final String taskComment;
    private final Timestamp taskAcceptationDate;
    private final Timestamp taskRealisationTerm;
}
