package com.printshopmanagement.application.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Task {
    @Id
    @GeneratedValue
    private Long id;

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
    private Timestamp taskDeadline;

    public Task(String taskName, String taskType, String taskStatus, String taskComment, Timestamp taskAcceptationDate, Timestamp taskDeadline) {
        this.taskName = taskName;
        this.taskType = taskType;
        this.taskStatus = taskStatus;
        this.taskComment = taskComment;
        this.taskAcceptationDate = taskAcceptationDate;
        this.taskDeadline = taskDeadline;
    }
}
