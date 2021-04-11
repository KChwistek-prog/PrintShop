package com.printshopmanagement.application.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Task {
    @Id
    @GeneratedValue
    private Integer id;

    @Column
    private String taskName;

    @Column
    private String taskType;

    @Column
    private String taskStatus;

    @Column
    private String taskComment;

    @Column
    private Timestamp taskAcceptationDate;

    @Column
    private Timestamp taskRealisationTerm;

    public Task(String taskName, String taskType, String taskStatus, String taskComment) {
        this.taskName = taskName;
        this.taskType = taskType;
        this.taskStatus = taskStatus;
        this.taskComment = taskComment;
    }
}
