package com.printshopmanagement.application.views.material;

import com.printshopmanagement.application.domain.Task;
import com.printshopmanagement.application.repository.TaskDbService;
import com.printshopmanagement.application.views.main.MainView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = "material", layout = MainView.class)
@PageTitle("Material")
@CssImport("./views/materials/materials-view.css")
public class MaterialView extends Div {


    public MaterialView() {
    }
}
