package com.printshopmanagement.application.scheduler;

import com.printshopmanagement.application.repository.TaskDbService;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class MailBuilder {

    TaskDbService taskDbService;

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
        System.out.println(message);
        return message;
    }

}
