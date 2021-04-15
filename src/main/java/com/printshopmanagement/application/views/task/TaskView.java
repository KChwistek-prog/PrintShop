package com.printshopmanagement.application.views.task;

import com.printshopmanagement.application.domain.Task;
import com.printshopmanagement.application.repository.TaskDbService;
import com.printshopmanagement.application.views.main.MainView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = "task", layout = MainView.class)
@PageTitle("Task")
@CssImport("./views/tasks/tasks-view.css")
public class TaskView extends Div {
    private final Grid<Task> grid = new Grid<>(Task.class, false);

    private final Button cancel = new Button("Cancel");
    private final Button save = new Button("Save");
    private final Button delete = new Button("Delete");

    private TextField taskName;
    private TextField taskType;
    private TextField taskStatus;
    private TextField taskComment;

    private Task task;
    public TaskView(@Autowired TaskDbService taskDbService) {
    }
}
