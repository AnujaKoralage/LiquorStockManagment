package Controllers;

import BO.BOFactory;
import BO.BOTypes;
import BO.custom.BuyerBO;
import BO.custom.Buyer_grnBO;
import BO.custom.Supplier_order_grnBO;
import DTO.BuyerDTO;
import DTO.Buyer_GRNDTO;
import UtilityClasses.AllBuyerOrders;
import UtilityClasses.ItemUtil;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ViewAllBuyerOrdersController {
    public TableView <AllBuyerOrders>tbl_buyerOrder;
    public static AllBuyerOrders selectedItem =null;

    public void initialize(){

        tbl_buyerOrder.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("orderID"));
        tbl_buyerOrder.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("date"));
        tbl_buyerOrder.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("buyerID"));
        tbl_buyerOrder.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("buyerName"));

        Buyer_grnBO buyer_grnBO =BOFactory.getInstance().getBO(BOTypes.BUYERGRN);
        BuyerBO buyerBO = BOFactory.getInstance().getBO(BOTypes.BUYER);

        ObservableList items = tbl_buyerOrder.getItems();
        try {
            List<Buyer_GRNDTO> read = buyer_grnBO.read();
            List<BuyerDTO> read1 = buyerBO.read();
            for (Buyer_GRNDTO buyer_grndto:read) {
                String buyerName =null;
                for (BuyerDTO buyerDTO:read1) {
                    if (buyerDTO.getBuyerID() == buyer_grndto.getBuyerID()){
                        buyerName = buyerDTO.getName();
                    }
                }
                items.add(new AllBuyerOrders(buyer_grndto.getGrnID(),buyer_grndto.getDate(),buyer_grndto.getBuyerID(),buyerName));
            }
        } catch (SQLException e) {
            Logger.getLogger("com.anuja.controller").log(Level.ALL,null,e);
            e.printStackTrace();
        }

        tbl_buyerOrder.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<AllBuyerOrders>() {
            @Override
            public void changed(ObservableValue<? extends AllBuyerOrders> observable, AllBuyerOrders oldValue, AllBuyerOrders newValue) {

                selectedItem = tbl_buyerOrder.getSelectionModel().getSelectedItem();
                Parent root = null;
                try {
                    root = FXMLLoader.load(this.getClass().getResource("/Views/BuyOrderManagement.fxml"));
                } catch (IOException e) {
                    Logger.getLogger("com.anuja.controller").log(Level.ALL,null,e);
                    e.printStackTrace();
                }
                Scene scene = new Scene(root);

                Stage stage = (Stage) tbl_buyerOrder.getScene().getWindow();
                stage.setScene(scene);
                stage.setTitle("Update Order");
            }
        });

    }

    public void goDashboard(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/Views/Dashboard.fxml"));
        Scene scene = new Scene(root);

        Stage stage = (Stage) tbl_buyerOrder.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Liquor Stock Management");
    }
}
