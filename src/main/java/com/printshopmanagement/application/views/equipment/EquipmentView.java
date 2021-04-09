package com.printshopmanagement.application.views.equipment;

import com.printshopmanagement.application.views.main.MainView;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "equipment", layout = MainView.class)
@PageTitle("Equipment")
@CssImport("./views/equipments/equipments-view.css")
public class EquipmentView extends Div {
}
