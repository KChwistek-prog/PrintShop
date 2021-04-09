package com.printshopmanagement.application.views.task;

import com.printshopmanagement.application.views.main.MainView;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;

@Route(value = "task", layout = MainView.class)
@PageTitle("Task")
@CssImport("./views/tasks/tasks-view.css")
public class TaskView extends Div {
}
