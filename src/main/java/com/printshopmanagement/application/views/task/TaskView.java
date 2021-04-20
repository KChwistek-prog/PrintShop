package com.printshopmanagement.application.views.task;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.printshopmanagement.application.controller.TaskController;
import com.printshopmanagement.application.domain.ProductDto;

import com.printshopmanagement.application.domain.Task;
import com.printshopmanagement.application.domain.TaskDto;
import com.printshopmanagement.application.repository.TaskDbService;
import com.printshopmanagement.application.views.main.MainView;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasStyle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Route(value = "task", layout = MainView.class)
@PageTitle("Task")
@CssImport("./views/tasks/tasks-view.css")
public class TaskView extends Div {
    private final Grid<TaskDto> grid = new Grid<>(TaskDto.class, false);
    private final Button cancel = new Button("Cancel");
    private final Button save = new Button("Save");

    private TextField id;
    private TextField taskName;
    private TextField taskType;
    private TextField taskStatus;
    private TextField taskComment;
    private TextField taskAcceptationDate;
    private TextField taskDeadline;

    public TaskView() throws IOException {
        addClassName("tasks-view");
        grid.setColumns("id", "taskName", "taskType", "taskStatus", "taskComment", "taskAcceptationDate", "taskDeadline");
        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER);
        grid.setWidthFull();
        grid.setHeightFull();
        SplitLayout splitLayout = new SplitLayout();
        splitLayout.setHeightFull();
        splitLayout.addToPrimary(grid);

        editorGrid(splitLayout);
        add(splitLayout);
        refresh();
    }

    public void refresh() throws IOException {
        URL url = new URL("http://localhost:8080/v1/getTaskList");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Content-Type", "application/json");
        con.getContent();
        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder content = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        con.disconnect();
        ObjectMapper mapper = new ObjectMapper();
        List<TaskDto> taskList;
        taskList = Arrays.asList(mapper.readValue(content.toString(), TaskDto[].class));
        in.close();
        grid.setItems(taskList);
    }

    private void editorGrid(SplitLayout splitLayout) {
        FormLayout formLayout = new FormLayout();

        Div editorLayoutDiv = new Div();
        editorLayoutDiv.setId("editor-layout");

        Div editorDiv = new Div();
        editorDiv.setId("editor");
        editorLayoutDiv.add(editorDiv);

        id = new TextField("Id");
        taskName = new TextField("Task Type");
        taskType = new TextField("Task Name");
        taskStatus = new TextField("Task Status");
        taskComment = new TextField("Task Comment");
        taskAcceptationDate = new TextField("Task Accepted");
        taskDeadline = new TextField("Task Deadline");

        Component[] fields = new Component[]{id, taskName, taskType, taskStatus, taskComment, taskAcceptationDate, taskDeadline};

        for (Component field : fields) {
            ((HasStyle) field).addClassName("full-width");
        }
        formLayout.add(fields);
        editorDiv.add(formLayout);
        createButtonLayout(editorLayoutDiv);
        splitLayout.addToSecondary(editorLayoutDiv);
    }

    private void createButtonLayout(Div editorLayoutDiv) {
        HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.setId("button-layout");
        buttonLayout.setWidthFull();
        buttonLayout.setSpacing(true);
        cancel.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        buttonLayout.add(save, cancel);
        editorLayoutDiv.add(buttonLayout);
    }
}
