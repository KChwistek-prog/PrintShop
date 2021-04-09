package com.printshopmanagement.application.views.map;

import com.printshopmanagement.application.views.main.MainView;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;


@Route(value = "GoogleMap", layout = MainView.class)
@PageTitle("GoogleMap")
public class GoogleMapView extends Div {

}
