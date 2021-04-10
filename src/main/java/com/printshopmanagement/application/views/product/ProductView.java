package com.printshopmanagement.application.views.product;

import com.printshopmanagement.application.domain.Product;
import com.printshopmanagement.application.repository.ProductDbService;
import com.printshopmanagement.application.views.main.MainView;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.artur.helpers.CrudServiceDataProvider;

import java.awt.*;

@Route(value = "product", layout = MainView.class)
@PageTitle("Product")
@CssImport("./views/products/products-view.css")

public class ProductView extends Div {
//    private TextField productId;
//    private TextField productType;
//    private TextField productName;
//    private TextField productQty;
//    private TextField productPrice;
//

    private Grid<Product> grid = new Grid<>(Product.class, false);
    private BeanValidationBinder<Product> binder;

//    private Product product;

    public ProductView(@Autowired ProductDbService productDbService) {
        addClassName("products-view");

        SplitLayout splitLayout = new SplitLayout();
        splitLayout.setSizeFull();

        createGridLayout(splitLayout);
        add(splitLayout);


        grid.addColumn("productId").setAutoWidth(true);
        grid.addColumn("productType").setAutoWidth(true);
        grid.addColumn("productName").setAutoWidth(true);
        grid.addColumn("productQty").setAutoWidth(true);
        grid.addColumn("productPrice").setAutoWidth(true);
        grid.setDataProvider(new CrudServiceDataProvider<>(productDbService));
        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER);
        grid.setHeightFull();

        binder = new BeanValidationBinder<>(Product.class);
    }

    private void createGridLayout(SplitLayout splitLayout){
        Div wrapper = new Div();
        wrapper.setId("grid-wrapper");
        wrapper.setWidthFull();
        splitLayout.addToPrimary(wrapper);
        wrapper.add(grid);
    }

//    private void refreshGrid(){
//        grid.select(null);
//        grid.getDataProvider().refreshAll();
//    }
//
//    private void populateForm(Product value){
//        this.product = value;
//        binder.readBean(this.product);
//    }
//
//    private void clearForm() {
//        populateForm(null);
//    }
}
