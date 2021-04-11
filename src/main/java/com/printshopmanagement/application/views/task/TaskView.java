package com.printshopmanagement.application.views.task;

import com.printshopmanagement.application.domain.Employee;
import com.printshopmanagement.application.domain.Task;
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
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.data.converter.StringToIntegerConverter;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.artur.helpers.CrudServiceDataProvider;

import java.util.Optional;

@Route(value = "task", layout = MainView.class)
@PageTitle("Task")
@CssImport("./views/tasks/tasks-view.css")
public class TaskView extends Div {
    private Grid<Task> grid = new Grid<>(Task.class, false);

    private Button cancel = new Button("Cancel");
    private Button save = new Button("Save");
    private Button delete = new Button("Delete");

    private TextField taskName;
    private TextField taskType;
    private TextField taskStatus;
    private TextField taskComment;

    private BeanValidationBinder<Task> binder;
    private Task task;
    public TaskView(@Autowired TaskDbService taskDbService) {
        addClassName("tasks-view");

        SplitLayout splitLayout = new SplitLayout();
        splitLayout.setSizeFull();

        createGridLayout(splitLayout);
        createEditorLayout(splitLayout);
        add(splitLayout);

        grid.addColumn("id").setAutoWidth(true);
        grid.addColumn("taskName").setAutoWidth(true);
        grid.addColumn("taskType").setAutoWidth(true);
        grid.addColumn("taskStatus").setAutoWidth(true);
        grid.addColumn("taskComment").setAutoWidth(true);
        grid.addColumn("taskAcceptationDate").setAutoWidth(true);
        grid.addColumn("taskRealisationTerm").setAutoWidth(true);
        grid.setDataProvider(new CrudServiceDataProvider<>(taskDbService));
        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER);
        grid.setHeightFull();


        // when a row is selected or deselected, populate form
        grid.asSingleSelect().addValueChangeListener(event -> {
            if (event.getValue() != null) {
                Optional<Task> employeeFromBackend = taskDbService.get(event.getValue().getId());
                if (employeeFromBackend.isPresent()) {
                    populateForm(employeeFromBackend.get());
                } else {
                    refreshGrid();
                }
            } else {
                clearForm();
            }
        });
        binder = new BeanValidationBinder<>(Task.class);
        binder.bindInstanceFields(this);

        cancel.addClickListener(e -> {
            clearForm();
            refreshGrid();
        });

        save.addClickListener(e -> {
            try {
                if (this.task == null) {
                    this.task = new Task();
                }
                binder.writeBean(this.task);
                taskDbService.saveTask(this.task);
                clearForm();
                refreshGrid();
                Notification.show("Task created.");
            } catch (ValidationException validationException) {
                Notification.show("An exception happened while trying to store the task details.");
            }
        });
    }

    private void createGridLayout(SplitLayout splitLayout) {
        Div wrapper = new Div();
        wrapper.setId("grid-wrapper");
        wrapper.setWidthFull();
        splitLayout.addToPrimary(wrapper);
        wrapper.add(grid);
    }

    private void createEditorLayout(SplitLayout splitLayout){
        Div editorLayoutDiv = new Div();
        editorLayoutDiv.setId("editor-layout");

        Div editorDiv = new Div();
        editorDiv.setId("editor");
        editorLayoutDiv.add(editorDiv);

        FormLayout formLayout = new FormLayout();
        taskName = new TextField("Task Name");
        taskType = new TextField("Task Type");
        taskStatus = new TextField("Task Status");
        taskComment = new TextField("Task Comment");
        Component[] fields = new Component[]{taskName,taskType,taskStatus,taskComment};

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
        delete.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        buttonLayout.add(save, cancel, delete);
        editorLayoutDiv.add(buttonLayout);
    }

    private void refreshGrid() {
        grid.select(null);
        grid.getDataProvider().refreshAll();
    }

    private void clearForm() {
        populateForm(null);
    }

    private void populateForm(Task value) {
        this.task = value;
        binder.readBean(this.task);

    }
}
