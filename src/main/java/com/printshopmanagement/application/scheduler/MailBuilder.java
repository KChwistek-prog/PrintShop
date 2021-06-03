package com.printshopmanagement.application.scheduler;

import com.printshopmanagement.application.repository.TaskDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class MailBuilder {

    private final TaskDbService taskDbService;

    @Autowired
    public MailBuilder(TaskDbService taskDbService) {
        this.taskDbService = taskDbService;
    }

    public String prepareSubject() {
        String date = LocalDateTime.now().toString();
        return "Daily report for " + date;
    }

    public String prepareData() {
        String message = "Accepted tasks: \n"
                + taskDbService.getDailyTasks().toString();
        return message;
    }

}
