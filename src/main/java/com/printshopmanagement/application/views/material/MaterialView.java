package com.printshopmanagement.application.views.material;

import com.printshopmanagement.application.views.main.MainView;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;

@Route(value = "material", layout = MainView.class)
@PageTitle("Material")
@CssImport("./views/materials/materials-view.css")
public class MaterialView extends Div {
}
