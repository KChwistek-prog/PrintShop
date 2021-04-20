package com.printshopmanagement.application.views.employee;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.printshopmanagement.application.controller.EmployeeController;
import com.printshopmanagement.application.domain.EmployeeDto;
import com.printshopmanagement.application.domain.MaterialDto;
import com.printshopmanagement.application.repository.EmployeeDbService;
import com.printshopmanagement.application.views.main.MainView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

@Route(value = "employee", layout = MainView.class)
@RouteAlias(value = "", layout = MainView.class)
@PageTitle("Employee")
@CssImport("./views/employee/employee-view.css")
public class EmployeeView extends Div {
    private final Grid<EmployeeDto> grid = new Grid<>(EmployeeDto.class, false);


    private TextField id;
    private TextField employeePersonalNumber;
    private TextField employeeFirstName;
    private TextField employeeLastName;
    private TextField employeeAddress;
    private TextField employeeStatus;
    private TextField employeeSalary;

    private final Button cancel = new Button("Cancel");
    private final Button save = new Button("Save");

    public EmployeeView() throws IOException {

        addClassName("employee-view");
        grid.addColumn("id").setAutoWidth(true);
        grid.addColumn("employeePersonalNumber").setAutoWidth(true);
        grid.addColumn("employeeFirstName").setAutoWidth(true);
        grid.addColumn("employeeLastName").setAutoWidth(true);
        grid.addColumn("employeeAddress").setAutoWidth(true);
        grid.addColumn("employeeStatus").setAutoWidth(true);
        grid.addColumn("employeeSalary").setAutoWidth(true);
        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER);
        grid.setWidthFull();
        grid.setHeightFull();
        SplitLayout splitLayout = new SplitLayout();
        splitLayout.setSizeFull();
        splitLayout.addToPrimary(grid);
        add(splitLayout);
        refresh();
    }

    public void refresh() throws IOException {
        URL url = new URL("http://localhost:8080/v1/getEmployees");
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
        List<EmployeeDto> materialList;
        materialList = Arrays.asList(mapper.readValue(content.toString(), EmployeeDto[].class));
        in.close();
        grid.setItems(materialList);
    }
}
