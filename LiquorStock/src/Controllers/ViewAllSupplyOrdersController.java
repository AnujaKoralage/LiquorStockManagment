package Controllers;

import BO.BOFactory;
import BO.BOTypes;
import BO.custom.SupplierBO;
import BO.custom.Supplier_order_grnBO;
import DTO.SupplierDTO;
import DTO.Supplier_order_grnDTO;
import UtilityClasses.SupplierOrderItemUtil;
import UtilityClasses.SupplierOrderUtil;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ViewAllSupplyOrdersController {
    public TableView<SupplierOrderUtil> tbl_allSupplierOrders;

    private Supplier_order_grnBO supplier_order_grnBO = BOFactory.getInstance().getBO(BOTypes.SUPPLIERORDERDRN);
    public static SupplierOrderUtil grn = null;
    public void initialize(){

        tbl_allSupplierOrders.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("orderID"));
        tbl_allSupplierOrders.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("orderDate"));
        tbl_allSupplierOrders.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("supplierID"));
        tbl_allSupplierOrders.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("suplierName"));



        SupplierBO supplierBO = BOFactory.getInstance().getBO(BOTypes.SUPPLIER);
        ObservableList<SupplierOrderUtil> orders = tbl_allSupplierOrders.getItems();
        try {
            List<SupplierDTO> allsuppliers = supplierBO.read();
            List<Supplier_order_grnDTO> supplier_order_grnDTOS = supplier_order_grnBO.read();
            for (Supplier_order_grnDTO supplier_order_grnDTO:supplier_order_grnDTOS) {
                String supplierName = null;
                for (SupplierDTO supplierDTO:allsuppliers) {
                    if (supplierDTO.getId() == supplier_order_grnDTO.getSupplierID()){
                        supplierName = supplierDTO.getName();
                        break;
                    }
                }
                orders.add(new SupplierOrderUtil(supplier_order_grnDTO.getGrnID(),supplier_order_grnDTO.getDate(),supplier_order_grnDTO.getSupplierID(),supplierName));
            }
        } catch (SQLException e) {
            Logger.getLogger("com.anuja.controller").log(Level.ALL,null,e);
            e.printStackTrace();
        }

        tbl_allSupplierOrders.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<SupplierOrderUtil>() {
            @Override
            public void changed(ObservableValue<? extends SupplierOrderUtil> observable, SupplierOrderUtil oldValue, SupplierOrderUtil newValue) {
                grn = tbl_allSupplierOrders.getSelectionModel().getSelectedItem();

                Parent root = null;
                try {
                    root = FXMLLoader.load(this.getClass().getResource("/Views/SupplyOrderManagement.fxml"));
                } catch (IOException e) {
                    Logger.getLogger("com.anuja.controller").log(Level.ALL,null,e);
                    e.printStackTrace();
                }
                Scene scene = new Scene(root);

                Stage stage = (Stage) tbl_allSupplierOrders.getScene().getWindow();
                stage.setScene(scene);
                stage.setTitle("Supplier Managment");
            }
        });


    }

    public void backDashboard(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/Views/Dashboard.fxml"));
        Scene scene = new Scene(root);

        Stage stage = (Stage) tbl_allSupplierOrders.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Liquor Stock Management");
    }
}
