package com.printshopmanagement.application.views.product;

import com.printshopmanagement.application.domain.Product;
import com.printshopmanagement.application.views.main.MainView;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "product", layout = MainView.class)
@PageTitle("Product")
@CssImport("./views/products/products-view.css")

public class ProductView extends Div {

    private final Grid<Product> grid = new Grid<>(Product.class);

}
