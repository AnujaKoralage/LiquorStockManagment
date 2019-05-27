package Controllers;

import BO.BO;
import BO.BOFactory;
import BO.BOTypes;
import BO.custom.BuyerBO;
import DTO.BuyerDTO;
import UtilityClasses.BuyerUtil;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BuyerManagementController {
    public TextField txt_buyerID;
    public TextField txt_buyerName;
    public TextField txt_buyerAddress;
    public TableView<BuyerUtil> tbl_allBuyers;
    public Button btn_saveBuyer;
    public Button btn_updateBuyer;
    public Button btn_back;

    private BuyerBO buyerBO = BOFactory.getInstance().getBO(BOTypes.BUYER);

    public void initialize(){
        txt_buyerID.setEditable(false);
        btn_saveBuyer.setVisible(true);
        btn_updateBuyer.setVisible(false);

        try {
            txt_buyerID.setText(String.valueOf(buyerBO.genarateBuyerID()));
        } catch (Exception e) {
            Alert alert =new Alert(Alert.AlertType.ERROR,"Unable to generate buyer id");
            alert.show();
        }

        tbl_allBuyers.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("buyerID"));
        tbl_allBuyers.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
        tbl_allBuyers.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("address"));

        TableColumn<BuyerUtil, BuyerUtil> unfriendCol = new TableColumn<>("Delete");
        unfriendCol.setMinWidth(40);
        unfriendCol.setCellValueFactory(
                param -> new ReadOnlyObjectWrapper<>(param.getValue())
        );
        unfriendCol.setCellFactory(param -> new TableCell<BuyerUtil, BuyerUtil>() {
            private final Button deleteButton = new Button("Delete");

            @Override
            protected void updateItem(BuyerUtil buyerUtil, boolean empty) {

                super.updateItem(buyerUtil, empty);

                if (buyerUtil == null) {
                    setGraphic(null);
                    return;
                }

                setGraphic(deleteButton);
                deleteButton.setOnAction(
                        event -> {

                            ObservableList<BuyerUtil> items = tbl_allBuyers.getItems();

                            try {
                                boolean delete = buyerBO.delete(new BuyerDTO(buyerUtil.getBuyerID(), buyerUtil.getName(), buyerUtil.getAddress()));
                                if (delete){
                                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Buyer deleted");
                                    alert.show();
                                    items.remove(buyerUtil);
                                    tbl_allBuyers.refresh();
                                    txt_buyerID.setText(String.valueOf(buyerBO.genarateBuyerID()));
                                }

                            } catch (SQLException e) {
                                Alert alert = new Alert(Alert.AlertType.ERROR,"Unable to delete buyer");
                                alert.show();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                );
            }
        });
        tbl_allBuyers.getColumns().add(unfriendCol);

        ObservableList<BuyerUtil> buyers = tbl_allBuyers.getItems();
        try {
            List<BuyerDTO> read = buyerBO.read();
            for (BuyerDTO buyerDTO:read) {
                buyers.add(new BuyerUtil(buyerDTO.getBuyerID(),buyerDTO.getName(),buyerDTO.getAddress()));
            }
            tbl_allBuyers.refresh();
        } catch (SQLException e) {
            Logger.getLogger("com.anuja.controller").log(Level.ALL,null,e);
            Alert alert = new Alert(Alert.AlertType.ERROR,"Unable to get buyers");
            alert.show();
        }

        tbl_allBuyers.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<BuyerUtil>() {
            @Override
            public void changed(ObservableValue<? extends BuyerUtil> observable, BuyerUtil oldValue, BuyerUtil newValue) {
                btn_saveBuyer.setVisible(false);
                btn_updateBuyer.setVisible(true);

                txt_buyerID.setText(String.valueOf(tbl_allBuyers.getSelectionModel().getSelectedItem().getBuyerID()));
                txt_buyerName.setText(tbl_allBuyers.getSelectionModel().getSelectedItem().getName());
                txt_buyerAddress.setText(tbl_allBuyers.getSelectionModel().getSelectedItem().getAddress());
            }
        });

    }

    public void saveBuyer(ActionEvent actionEvent) {
        if (txt_buyerID.getText().length()==0 || txt_buyerAddress.getText().length()==0 || txt_buyerName.getText().length()==0){
            Alert alert = new Alert(Alert.AlertType.ERROR,"Fill all feilds");
            alert.show();
        }else {
            try {
                buyerBO.insert(new BuyerDTO(Integer.parseInt(txt_buyerID.getText()),txt_buyerName.getText(),txt_buyerAddress.getText()));

                ObservableList<BuyerUtil> buyers = tbl_allBuyers.getItems();
                buyers.clear();
                List<BuyerDTO> read = buyerBO.read();
                for (BuyerDTO buyerDTO:read) {
                    buyers.add(new BuyerUtil(buyerDTO.getBuyerID(),buyerDTO.getName(),buyerDTO.getAddress()));
                }
                tbl_allBuyers.refresh();
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Buyer inserted");
                alert.show();
            } catch (SQLException e) {
                Logger.getLogger("com.anuja.controller").log(Level.ALL,null,e);
                Alert alert = new Alert(Alert.AlertType.ERROR,"Caanot insert buyer");
                alert.show();
                e.printStackTrace();
            }
            txt_buyerID.setText(String.valueOf(Integer.parseInt(txt_buyerID.getText())+1));
        }

    }

    public void updateBuyer(ActionEvent actionEvent) {
        if (txt_buyerName.getText().length()==0 || txt_buyerAddress.getText().length()==0 || txt_buyerID.getText().length()==0){
            Alert alert = new Alert(Alert.AlertType.ERROR,"Fill all feilds");
            alert.show();
        }else {
            try {
                buyerBO.update(new BuyerDTO(Integer.parseInt(txt_buyerID.getText()),txt_buyerName.getText(),txt_buyerAddress.getText()));

                ObservableList<BuyerUtil> buyers = tbl_allBuyers.getItems();
                List<BuyerDTO> read = buyerBO.read();
                for (BuyerDTO buyerDTO:read) {
                    if (tbl_allBuyers.getSelectionModel().getSelectedItem().getBuyerID() == buyerDTO.getBuyerID()){
                        buyers.remove(new BuyerUtil(buyerDTO.getBuyerID(),buyerDTO.getName(),buyerDTO.getAddress()));
                        buyers.add(new BuyerUtil(buyerDTO.getBuyerID(),buyerDTO.getName(),buyerDTO.getAddress()));
                    }
                    // buyers.add(new BuyerUtil(buyerDTO.getBuyerID(),buyerDTO.getName(),buyerDTO.getAddress()));
                }
                tbl_allBuyers.refresh();
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Buyer updated");
                alert.show();
            } catch (SQLException e) {
                Logger.getLogger("com.anuja.controller").log(Level.ALL,null,e);
                Alert alert = new Alert(Alert.AlertType.ERROR,"Caanot update buyer");
                alert.show();
                e.printStackTrace();
            }
        }
    }

    public void goDashboard(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/Views/Dashboard.fxml"));
        Scene scene = new Scene(root);

        Stage stage = (Stage) btn_back.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Liquor Stock Management");
    }
}
