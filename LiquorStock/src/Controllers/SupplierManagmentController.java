package Controllers;

import BO.BO;

import BO.BOFactory;
import BO.BOTypes;
import BO.custom.ItemBO;
import BO.custom.SupplierBO;
import DTO.ItemDTO;
import DTO.SupplierDTO;
import UtilityClasses.ItemUtil;
import UtilityClasses.SupplierUtil;
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

public class SupplierManagmentController {
    public TextField txt_supplierAddress;
    public TextField txt_supplierName;
    public TextField txt_supplierID;
    public TableView<ItemUtil> tbl_supplierItem;
    public Button btn_saveSupplier;
    public Button btn_updateSupplier;
    public TextField txt_itemName;
    public TextField txt_itemCode;
    public Button btn_updateItem;
    public Button btn_addItem;

    private SupplierBO supbo = BOFactory.getInstance().getBO(BOTypes.SUPPLIER);
    private ItemBO itembo = BOFactory.getInstance().getBO(BOTypes.ITEM);

    public void initialize() throws Exception {
        txt_supplierID.setEditable(false);
        txt_itemCode.setEditable(false);
        btn_saveSupplier.setVisible(true);
        btn_updateSupplier.setVisible(false);
        btn_addItem.setVisible(true);
        btn_updateItem.setVisible(false);

        tbl_supplierItem.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("itemID"));
        tbl_supplierItem.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("itemName"));

        TableColumn<ItemUtil, ItemUtil> unfriendCol = new TableColumn<>("Delete");
        unfriendCol.setMinWidth(40);
        unfriendCol.setCellValueFactory(
                param -> new ReadOnlyObjectWrapper<>(param.getValue())
        );
        unfriendCol.setCellFactory(param -> new TableCell<ItemUtil, ItemUtil>() {
            private final Button deleteButton = new Button("Delete");

            @Override
            protected void updateItem(ItemUtil itemUtil, boolean empty) {

                super.updateItem(itemUtil, empty);

                if (itemUtil == null) {
                    setGraphic(null);
                    return;
                }

                setGraphic(deleteButton);
                deleteButton.setOnAction(
                        event -> {


                            try {
                                itembo.delete(new ItemDTO(itemUtil.getItemID(),0,Integer.parseInt(txt_supplierID.getText()),itemUtil.getItemName()));
                                ObservableList<ItemUtil> items = tbl_supplierItem.getItems();
                                items.remove(itemUtil);
                                tbl_supplierItem.refresh();
                                txt_itemCode.setText(String.valueOf(itemUtil.getItemID()));
                            } catch (SQLException e) {
                                Alert alert = new Alert(Alert.AlertType.ERROR,"Item already exists");
                                alert.show();
                                Logger.getLogger("com.anuja.controller").log(Level.ALL,null,e);
                            }
                        }
                );
            }
        });
        tbl_supplierItem.getColumns().add(unfriendCol);

        try {
            txt_supplierID.setText(String.valueOf(supbo.genarateSupplierID()));
            txt_itemCode.setText(String.valueOf(itembo.genarateItemID()));
        } catch (Exception e) {
            e.printStackTrace();
            Logger.getLogger("com.anuja.controller").log(Level.ALL,null,e);
        }

        if (ViewAllSupplierController.data!=null){
            btn_updateSupplier.setVisible(true);
            btn_saveSupplier.setVisible(false);
            btn_addItem.setVisible(true);
            btn_updateItem.setVisible(false);

            SupplierUtil data = ViewAllSupplierController.data;
            txt_supplierID.setText(String.valueOf(data.getSupplierid()));
            txt_supplierName.setText(data.getSupplierName());
            txt_supplierAddress.setText(data.getAddress());

            List<ItemDTO> supplierItems = supbo.getSupplierItems(data.getSupplierid());
            ObservableList<ItemUtil> items = tbl_supplierItem.getItems();
            for (ItemDTO itemDTO:supplierItems) {
                items.add(new ItemUtil(itemDTO.getItemID(),itemDTO.getName()));
                System.out.println(itemDTO.getName());
            }
            tbl_supplierItem.refresh();

            tbl_supplierItem.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<ItemUtil>() {
                @Override
                public void changed(ObservableValue<? extends ItemUtil> observable, ItemUtil oldValue, ItemUtil newValue) {
                    btn_updateItem.setVisible(true);
                    btn_addItem.setVisible(false);
                    txt_itemCode.setText(String.valueOf(tbl_supplierItem.getSelectionModel().getSelectedItem().getItemID()));
                    txt_itemName.setText(tbl_supplierItem.getSelectionModel().getSelectedItem().getItemName());
                }
            });
            ViewAllSupplierController.data=null;
        }
    }

    public void addItemToSupplier(ActionEvent actionEvent) {
        if (txt_itemCode.getText().length() == 0 || txt_itemName.getText().length()==0){
            Alert alert = new Alert(Alert.AlertType.ERROR,"Enter all feilds");
            alert.show();
        }else {
            ObservableList items = tbl_supplierItem.getItems();
            items.add(new ItemUtil(Integer.parseInt(txt_itemCode.getText()),txt_itemName.getText()));
            for (int i=0;i<items.size();i++){
                ItemUtil itemUtil = (ItemUtil) items.get(i);
                if (Integer.parseInt(txt_itemCode.getText())+1!=itemUtil.getItemID()){
                    txt_itemCode.setText(String.valueOf(Integer.parseInt(txt_itemCode.getText())+1));
                    break;
                }else {
                    txt_itemCode.setText(String.valueOf(Integer.parseInt(txt_itemCode.getText())+1));
                }
            }
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Item added");
            alert.show();
            txt_itemName.setText("");
        }
    }

    public void saveSupplier(ActionEvent actionEvent) {
        if (txt_supplierID.getText().length() ==0 || txt_supplierName.getText().length() == 0 || txt_supplierAddress.getText().length() == 0){
            Alert alert = new Alert(Alert.AlertType.ERROR,"Enter all feilds");
            alert.show();
        }else {
            try {
                supbo.insert(new SupplierDTO(Integer.parseInt(txt_supplierID.getText()),txt_supplierName.getText(),txt_supplierAddress.getText()));
                ObservableList<ItemUtil> items = tbl_supplierItem.getItems();
                for (ItemUtil itemUtil:items) {
                    itembo.insert(new ItemDTO(itemUtil.getItemID(),-0,Integer.parseInt(txt_supplierID.getText()),itemUtil.getItemName()));
                }
            } catch (SQLException e) {
                Logger.getLogger("com.anuja.controller").log(Level.ALL,null,e);
                Alert alert = new Alert(Alert.AlertType.ERROR,"Supplier not aded");
                alert.show();
                e.printStackTrace();
            }
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Supplier added");
            alert.show();
            txt_supplierAddress.setText("");
            txt_supplierName.setText("");
            tbl_supplierItem.getItems().clear();
            try {
                txt_supplierID.setText(String.valueOf(supbo.genarateSupplierID()));
            } catch (Exception e) {
                Logger.getLogger("com.anuja.controller").log(Level.ALL,null,e);
                e.printStackTrace();
            }
        }
    }

    public void updateSupplier(ActionEvent actionEvent) {
        if (txt_supplierID.getText().length() ==0 || txt_supplierName.getText().length() == 0 || txt_supplierAddress.getText().length() == 0){
            Alert alert = new Alert(Alert.AlertType.ERROR,"Enter all feilds");
            alert.show();
        }
        else {
            try {
                supbo.update(new SupplierDTO(Integer.parseInt(txt_supplierID.getText()),txt_supplierName.getText(),txt_supplierAddress.getText()));
                ObservableList<ItemUtil> items = tbl_supplierItem.getItems();
                for (ItemUtil itemUtil:items) {
                    boolean update = itembo.update(new ItemDTO(itemUtil.getItemID(), 0, Integer.parseInt(txt_supplierID.getText()), itemUtil.getItemName()));
                    if (!update)
                        itembo.insert(new ItemDTO(itemUtil.getItemID(), 0, Integer.parseInt(txt_supplierID.getText()), itemUtil.getItemName()));
                }
            } catch (SQLException e) {
                Logger.getLogger("com.anuja.controller").log(Level.ALL,null,e);
                Alert alert = new Alert(Alert.AlertType.ERROR,"cannot update supplier");
                alert.show();
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Supplier added");
            alert.show();
            txt_supplierAddress.setText("");
            txt_supplierName.setText("");
            txt_itemName.setText("");
            try {
                txt_itemCode.setText(String.valueOf(itembo.genarateItemID()));
            } catch (Exception e) {
                Logger.getLogger("com.anuja.controller").log(Level.ALL,null,e);
                e.printStackTrace();
            }
            btn_saveSupplier.setVisible(true);
            btn_updateSupplier.setVisible(false);
            btn_updateItem.setVisible(false);
            btn_addItem.setVisible(true);
            tbl_supplierItem.getItems().clear();
            try {
                txt_supplierID.setText(String.valueOf(supbo.genarateSupplierID()));
            } catch (Exception e) {
                Logger.getLogger("com.anuja.controller").log(Level.ALL,null,e);
                e.printStackTrace();
            }

        }
    }

    public void toViewAllSuppliers(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/Views/ViewAllSuppliers.fxml"));
        Scene viewAllSuplier = new Scene(root);

        Stage stage = (Stage) btn_saveSupplier.getScene().getWindow();
        stage.setScene(viewAllSuplier);
    }

    public void goDashboard(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/Views/Dashboard.fxml"));
        Scene dashboard = new Scene(root);

        Stage stage = (Stage) txt_supplierAddress.getScene().getWindow();
        stage.setScene(dashboard);
        stage.setTitle("Liquor Stock Managment");
    }

    public void updateItem(ActionEvent actionEvent) {
        ItemUtil selectedItem = tbl_supplierItem.getSelectionModel().getSelectedItem();

        ObservableList items = tbl_supplierItem.getItems();
        items.add(new ItemUtil(Integer.parseInt(txt_itemCode.getText()),txt_itemName.getText()));
        System.out.println(txt_itemName.getText());
        tbl_supplierItem.getItems().remove(selectedItem);
        tbl_supplierItem.refresh();
    }
}
