package com.printshopmanagement.application.views.material;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.printshopmanagement.application.controller.MaterialController;
import com.printshopmanagement.application.domain.MaterialDto;
import com.printshopmanagement.application.repository.MaterialDbService;
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
import java.util.Arrays;
import java.util.List;

@Route(value = "material", layout = MainView.class)
@PageTitle("Material")
@CssImport("./views/materials/materials-view.css")
public class MaterialView extends Div {
    private final Grid<MaterialDto> grid = new Grid<>(MaterialDto.class);
    private TextField id;
    private TextField materialType;
    private TextField materialName;
    private TextField materialQty;
    private TextField comments;

    private final Button cancel = new Button("Cancel");
    private final Button save = new Button("Save");

    public MaterialView() throws IOException {

        addClassName("materials-view");
        grid.setColumns("id", "materialType", "materialName", "materialQty", "comments");
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
        URL url = new URL("http://localhost:8080/v1/getMaterials");
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
        List<MaterialDto> materialList;
        materialList = Arrays.asList(mapper.readValue(content.toString(), MaterialDto[].class));
        in.close();
        grid.setItems(materialList);
    }

    private void editorGrid(SplitLayout splitLayout) {
        Div editorLayoutDiv = new Div();
        editorLayoutDiv.setId("editor-layout");

        Div editorDiv = new Div();
        editorDiv.setId("editor");
        editorLayoutDiv.add(editorDiv);

        FormLayout formLayout = new FormLayout();

        id = new TextField("id");
        materialType = new TextField("Material Type");
        materialName = new TextField("Material Name");
        materialQty = new TextField("Material Qty");
        comments = new TextField("Comments");

        Component[] fields = new Component[]{materialType, materialName, materialQty, comments};

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
