package com.printshopmanagement.application.views.calendar;

import com.printshopmanagement.application.domain.Task;
import com.printshopmanagement.application.repository.TaskDbService;
import com.printshopmanagement.application.views.main.MainView;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.vaadin.stefan.fullcalendar.Entry;
import org.vaadin.stefan.fullcalendar.FullCalendar;
import org.vaadin.stefan.fullcalendar.FullCalendarBuilder;

import java.util.ArrayList;
import java.util.List;

@Route(value = "calendar", layout = MainView.class)
@PageTitle("Calendar")
public class Calendar extends Div {
    public Calendar(TaskDbService taskDbService) {
        FullCalendar calendar = FullCalendarBuilder.create().build();

        calendar.addEntries(getTaskFromDb(taskDbService));
        add(calendar);

    }

    private List<Entry> getTaskFromDb(TaskDbService taskDbService) {
        Entry entry = new Entry();
        List<Task> tasks = taskDbService.getTasks();
        List<Entry> entries = new ArrayList<>();

        for (Task task : tasks) {
            entry.setTitle(task.getTaskName());
            entry.setStart(task.getTaskAcceptationDate().toLocalDateTime());
            entry.setEnd(task.getTaskDeadline().toLocalDateTime());
            entry.setColor("#ff3333");
            entries.add(entry);
        }

            return entries;
        }

    }

