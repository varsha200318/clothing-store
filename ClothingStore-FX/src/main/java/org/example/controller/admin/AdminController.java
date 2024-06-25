package org.example.controller.admin;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import org.example.controller.CustomersController;
import org.example.controller.LoginController;
import org.example.controller.OrdersController;
import org.example.controller.employee.EmployeeController;
import org.example.controller.products.ProductsController;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminController implements Initializable {

    public JFXButton btnDashboard;
    public JFXButton btnEmployee;
    public JFXButton btnProducts;
    public JFXButton btnOrders;
    public JFXButton btnCustomers;


    public void btnDashboardOnClick(ActionEvent actionEvent) {
        FXMLLoader fxmlLoader = loadFxmlPage("../resources.");
        LoginController homeController = fxmlLoader.getController();
        homeController.getDashboardProdCount();
        homeController.getDashboardCostCount();
    }
    public void btnEmployeeOnClick(ActionEvent actionEvent) {
        FXMLLoader fxmlLoader = loadFxmlPage("../resources.");
        EmployeeController controller = fxmlLoader.getController();
        controller.listEmployee();
    }
    public void btnProductsOnClick(ActionEvent actionEvent) {
        FXMLLoader fxmlLoader = loadFxmlPage("/view/admin/pages/products/products.fxml");
        ProductsController controller = fxmlLoader.getController();
        controller.listProducts();
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void btnOrdersOnClick(javafx.event.ActionEvent actionEvent) {
        FXMLLoader fxmlLoader = loadFxmlPage("/view/admin/pages/orders/orders.fxml");
        OrdersController orders = fxmlLoader.getController();
        orders.listOrders();
    }

    public void btnCustomersOnClick(javafx.event.ActionEvent actionEvent) {
        FXMLLoader fxmlLoader = loadFxmlPage("/view/admin/pages/customers/customers.fxml");
        CustomersController controller = fxmlLoader.getController();
        controller.listCustomers();
    }
}
