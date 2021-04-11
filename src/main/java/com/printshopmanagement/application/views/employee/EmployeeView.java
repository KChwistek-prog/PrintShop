package com.printshopmanagement.application.views.employee;

import com.printshopmanagement.application.domain.Employee;
import com.printshopmanagement.application.repository.EmployeeDbService;
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
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.data.converter.StringToIntegerConverter;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.artur.helpers.CrudServiceDataProvider;

import java.util.Optional;

@Route(value = "employee", layout = MainView.class)
@RouteAlias(value = "", layout = MainView.class)
@PageTitle("Employee")
@CssImport("./views/employee/employee-view.css")
public class EmployeeView extends Div {
    private Grid<Employee> grid = new Grid<>(Employee.class, false);

    private TextField id;
    private TextField employeePersonalNumber;
    private TextField employeeFirstName;
    private TextField employeeLastName;
    private TextField employeeAddress;
    private TextField employeeStatus;
    private TextField employeeSalary;

    private Button cancel = new Button("Cancel");
    private Button save = new Button("Save");

    private BeanValidationBinder<Employee> binder;

    private Employee employee;

    public EmployeeView(@Autowired EmployeeDbService employeeDbService){
        addClassName("employee-view");
        // Create UI
        SplitLayout splitLayout = new SplitLayout();
        splitLayout.setSizeFull();

        createGridLayout(splitLayout);
        createEditorLayout(splitLayout);

        add(splitLayout);

        // Configure Grid
        grid.addColumn("id").setAutoWidth(true);
        grid.addColumn("employeePersonalNumber").setAutoWidth(true);
        grid.addColumn("employeeFirstName").setAutoWidth(true);
        grid.addColumn("employeeLastName").setAutoWidth(true);
        grid.addColumn("employeeAddress").setAutoWidth(true);
        grid.addColumn("employeeStatus").setAutoWidth(true);
        grid.addColumn("employeeSalary").setAutoWidth(true);
        grid.setDataProvider(new CrudServiceDataProvider<>(employeeDbService));
        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER);
        grid.setHeightFull();

        // when a row is selected or deselected, populate form
        grid.asSingleSelect().addValueChangeListener(event -> {
            if (event.getValue() != null) {
                Optional<Employee> employeeFromBackend = employeeDbService.get(event.getValue().getId());
                // when a row is selected but the data is no longer available, refresh grid
                if (employeeFromBackend.isPresent()) {
                    populateForm(employeeFromBackend.get());
                } else {
                    refreshGrid();
                }
            } else {
                clearForm();
            }
        });

        // Configure Form
        binder = new BeanValidationBinder<>(Employee.class);
        // Bind fields. This where you'd define e.g. validation rules
        binder.forField(id).withConverter(new StringToIntegerConverter("Only numbers are allowed")).bind("id");
        binder.forField(employeePersonalNumber).withConverter(new StringToIntegerConverter("Only numbers are allowed"))
                .bind("employeePersonalNumber");
        binder.forField(employeeSalary).withConverter(new StringToIntegerConverter("Only numbers are allowed"))
                .bind("employeeSalary");

        binder.bindInstanceFields(this);

        cancel.addClickListener(e -> {
            clearForm();
            refreshGrid();
        });

        save.addClickListener(e -> {
            try {
                if (this.employee == null) {
                    this.employee = new Employee();
                }
                binder.writeBean(this.employee);
                employeeDbService.update(this.employee);
                clearForm();
                refreshGrid();
                Notification.show("Employee details stored.");
            } catch (ValidationException validationException) {
                Notification.show("An exception happened while trying to store the employee details.");
            }
        });
    }

    private void createEditorLayout(SplitLayout splitLayout) {
        Div editorLayoutDiv = new Div();
        editorLayoutDiv.setId("editor-layout");

        Div editorDiv = new Div();
        editorDiv.setId("editor");
        editorLayoutDiv.add(editorDiv);

        FormLayout formLayout = new FormLayout();
        id = new TextField("Id");
        employeePersonalNumber = new TextField("Employee Personal Number");
        employeeFirstName = new TextField("Employee First Name");
        employeeLastName = new TextField("Employee Last Name");
        employeeAddress = new TextField("Employee Address");
        employeeStatus = new TextField("Employee Status");
        employeeSalary = new TextField("Employee Salary");
        Component[] fields = new Component[]{id, employeePersonalNumber, employeeFirstName, employeeLastName,
                employeeAddress, employeeStatus, employeeSalary};

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

    private void createGridLayout(SplitLayout splitLayout) {
        Div wrapper = new Div();
        wrapper.setId("grid-wrapper");
        wrapper.setWidthFull();
        splitLayout.addToPrimary(wrapper);
        wrapper.add(grid);
    }

    private void refreshGrid() {
        grid.select(null);
        grid.getDataProvider().refreshAll();
    }

    private void clearForm() {
        populateForm(null);
    }

    private void populateForm(Employee value) {
        this.employee = value;
        binder.readBean(this.employee);

    }
}
