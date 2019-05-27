package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class DashboardController {
    public Button btn_supplierManagment;

    public void toSupplierManagment(ActionEvent actionEvent) throws IOException {

        Parent root = FXMLLoader.load(this.getClass().getResource("/Views/SupplierManagment.fxml"));
        Scene supplierManagmentScene = new Scene(root);

        Stage stage = (Stage) btn_supplierManagment.getScene().getWindow();
        stage.setScene(supplierManagmentScene);
        stage.setTitle("Supplier Managment");
    }

    public void toBuyerManagment(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/Views/BuyerManagement.fxml"));
        Scene supplierManagmentScene = new Scene(root);

        Stage stage = (Stage) btn_supplierManagment.getScene().getWindow();
        stage.setScene(supplierManagmentScene);
        stage.setTitle("Buyer Managment");
    }

    public void toSupplierOrderManagment(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/Views/SupplyOrderManagement.fxml"));
        Scene supplierManagmentScene = new Scene(root);

        Stage stage = (Stage) btn_supplierManagment.getScene().getWindow();
        stage.setScene(supplierManagmentScene);
        stage.setTitle("Supplier Order Managment");
    }

    public void toBuyerOrderManagment(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/Views/BuyOrderManagement.fxml"));
        Scene supplierManagmentScene = new Scene(root);

        Stage stage = (Stage) btn_supplierManagment.getScene().getWindow();
        stage.setScene(supplierManagmentScene);
        stage.setTitle("Buyer Order Management");
    }

    public void toViewAllSupplierOrders(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/Views/ViewAllSupplyOrders.fxml"));
        Scene supplierManagmentScene = new Scene(root);

        Stage stage = (Stage) btn_supplierManagment.getScene().getWindow();
        stage.setScene(supplierManagmentScene);
        stage.setTitle("VIew All Supplier Orders");
    }

    public void toViewAllBuyerOrders(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/Views/ViewAllBuyerOrders.fxml"));
        Scene supplierManagmentScene = new Scene(root);

        Stage stage = (Stage) btn_supplierManagment.getScene().getWindow();
        stage.setScene(supplierManagmentScene);
        stage.setTitle("VIew All Buyer Orders");
    }

    public void toCheckStocks(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/Views/CheckStocks.fxml"));
        Scene supplierManagmentScene = new Scene(root);

        Stage stage = (Stage) btn_supplierManagment.getScene().getWindow();
        stage.setScene(supplierManagmentScene);
        stage.setTitle("Check Stocks");
    }
}
