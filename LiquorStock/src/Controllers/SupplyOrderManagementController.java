package Controllers;

import BO.BO;
import BO.BOFactory;
import BO.BOTypes;
import BO.custom.*;
import DAO.DAOFactory;
import DAO.DAOTypes;
import DTO.*;
import UtilityClasses.ItemUtil;
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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SupplyOrderManagementController {
    public TextField txt_orderID;
    public TextField txt_date;
    public TextField txt_bottlePrice;
    public TextField txt_qty;
    public TextField txt_total;
    public ComboBox cmb_supplierId;
    public ComboBox cmb_liquorId;
    public TableView<SupplierOrderItemUtil> tbl_supplyOrdertbl;
    public Button btn_addLiquor;
    public Button btn_pendOrder;
    public Button btn_updateLiquor;
    public Button btn_updateOrder;
    public TextField txt_supplierName;
    public TextField txt_liquorName;

    private Supplier_order_grnBO grnbo = BOFactory.getInstance().getBO(BOTypes.SUPPLIERORDERDRN);
    private SupplierBO supbo = BOFactory.getInstance().getBO(BOTypes.SUPPLIER);
    private ItemBO itembo = BOFactory.getInstance().getBO(BOTypes.ITEM);
    private Item_priceBO item_priceBO = BOFactory.getInstance().getBO(BOTypes.ITEMPRICE);
    private Supplier_item_detailsBO supplier_item_detailsBO = BOFactory.getInstance().getBO(BOTypes.SUPPLIERITEMDETAILS);
    private Date date;

    public void initialize(){
        btn_addLiquor.setVisible(true);
        btn_updateLiquor.setVisible(false);
        btn_pendOrder.setVisible(true);
        btn_updateOrder.setVisible(false);
        txt_orderID.setEditable(false);
        txt_total.setEditable(false);
        txt_supplierName.setEditable(false);
        txt_bottlePrice.setEditable(false);

        tbl_supplyOrdertbl.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("liquorID"));
        tbl_supplyOrdertbl.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
        tbl_supplyOrdertbl.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("qty"));
        tbl_supplyOrdertbl.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("price"));

        TableColumn<SupplierOrderItemUtil, SupplierOrderItemUtil> unfriendCol = new TableColumn<>("Delete");
        unfriendCol.setMinWidth(40);
        unfriendCol.setCellValueFactory(
                param -> new ReadOnlyObjectWrapper<>(param.getValue())
        );
        unfriendCol.setCellFactory(param -> new TableCell<SupplierOrderItemUtil, SupplierOrderItemUtil>() {
            private final Button deleteButton = new Button("Delete");

            @Override
            protected void updateItem(SupplierOrderItemUtil supplierOrderItemUtil, boolean empty) {

                super.updateItem(supplierOrderItemUtil, empty);

                if (supplierOrderItemUtil == null) {
                    setGraphic(null);
                    return;
                }

                setGraphic(deleteButton);
                deleteButton.setOnAction(
                        event -> {



                            ObservableList<SupplierOrderItemUtil> items = tbl_supplyOrdertbl.getItems();
                            items.remove(supplierOrderItemUtil);
                            tbl_supplyOrdertbl.refresh();

                        }
                );
            }
        });
        tbl_supplyOrdertbl.getColumns().add(unfriendCol);

            try {
                txt_orderID.setText(String.valueOf(grnbo.generateSuppliergrnID()));
            } catch (Exception e) {
                Logger.getLogger("com.anuja.controller").log(Level.ALL,null,e);
                Alert alert = new Alert(Alert.AlertType.ERROR,"unable to generate orderID");
                alert.show();
                e.printStackTrace();
            }

            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
            Date date = new Date();
            txt_date.setText(dateFormat.format(date));

            try {
                List<SupplierDTO> read = supbo.read();

                ObservableList items = cmb_supplierId.getItems();
                for (SupplierDTO supplierDTO:read) {
                    items.add(supplierDTO.getId());
                }

                cmb_supplierId.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
                    @Override
                    public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                        cmb_liquorId.getItems().clear();
                        txt_bottlePrice.setText("");
                        txt_liquorName.setText("");
                        txt_total.setText("");
                        txt_qty.setText("");


                        cmb_liquorId.getSelectionModel().selectFirst();
                        cmb_liquorId.getItems().clear();
                        int selectedSupplier = (int) cmb_supplierId.getSelectionModel().getSelectedItem();
                        for (SupplierDTO supplierDTO:read) {
                            if (selectedSupplier==supplierDTO.getId()){
                                txt_supplierName.setText(supplierDTO.getName());
                            }
                        }

                        try {
                            List<ItemDTO> read1 = itembo.read();
                            for (ItemDTO itemDTO:read1) {
                                if (itemDTO.getSupID() == selectedSupplier){
                                    cmb_liquorId.getItems().add(itemDTO.getItemID());

                                }
                            }

                        } catch (SQLException e) {
                            Logger.getLogger("com.anuja.controller").log(Level.ALL,null,e);
                            e.printStackTrace();
                        }
                    }
                });

                cmb_liquorId.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
                    @Override
                    public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                        try {
                            List<ItemDTO> read1 = itembo.read();
                            int liquorID = (int) cmb_liquorId.getSelectionModel().getSelectedItem();
                            for (ItemDTO itemDTO:read1) {
                                if (itemDTO.getItemID() == liquorID){
                                    txt_liquorName.setText(itemDTO.getName());
                                    List<Item_priceDTO> read2 = item_priceBO.read();
                                    int i=0;
                                    for (Item_priceDTO item_priceDTO:read2) {
                                        if (item_priceDTO.getItemID()==itemDTO.getItemID()){
                                            txt_bottlePrice.setText(String.valueOf(item_priceDTO.getBuyingPrice()));
                                            break;
                                        }
                                        i++;
                                    }
                                    if (i==read2.size()){
                                        item_priceBO.insert(new Item_priceDTO(itemDTO.getItemID(),0,0));
                                        txt_bottlePrice.setEditable(true);
                                    }
                                }
                            }
                        } catch (SQLException e) {
                            Logger.getLogger("com.anuja.controller").log(Level.ALL,null,e);
                            e.printStackTrace();
                        }

                    }
                });

            } catch (SQLException e) {
                Logger.getLogger("com.anuja.controller").log(Level.ALL,null,e);
                Alert alert = new Alert(Alert.AlertType.ERROR,"unable to get suppliers");
                alert.show();
                e.printStackTrace();
            }

            tbl_supplyOrdertbl.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<SupplierOrderItemUtil>() {
                @Override
                public void changed(ObservableValue<? extends SupplierOrderItemUtil> observable, SupplierOrderItemUtil oldValue, SupplierOrderItemUtil newValue) {
                    btn_updateLiquor.setVisible(true);
                    btn_addLiquor.setVisible(false);

                    SupplierOrderItemUtil selectedItem = tbl_supplyOrdertbl.getSelectionModel().getSelectedItem();
                    txt_bottlePrice.setText(String.valueOf(selectedItem.getPrice()));
                    txt_liquorName.setText(selectedItem.getName());
                    txt_qty.setText(String.valueOf(selectedItem.getQty()));
                    cmb_liquorId.getSelectionModel().select((Object) selectedItem.getLiquorID());

                }
            });


        if (ViewAllSupplyOrdersController.grn != null){
            cmb_liquorId.setDisable(true);

            btn_updateOrder.setVisible(true);
            btn_pendOrder.setVisible(false);

            SupplierOrderUtil grn = ViewAllSupplyOrdersController.grn;
            txt_orderID.setText(String.valueOf(grn.getOrderID()));
            txt_date.setText(grn.getOrderDate());
            txt_supplierName.setText(grn.getSuplierName());
            cmb_supplierId.getSelectionModel().select(grn.getSupplierID()-1);

            try {
                List<Supplier_item_detailsDTO> supplier_item_detailsDTOS = supplier_item_detailsBO.read();
                List<ItemDTO> read1 = itembo.read();

                for (Supplier_item_detailsDTO supplierItemDetailsDTO:supplier_item_detailsDTOS) {
                    int liquorID = supplierItemDetailsDTO.getItemID();
                    if (supplierItemDetailsDTO.getSupGrnID() == grn.getOrderID()) {

                        for (ItemDTO itemDTO : read1) {
                            if (itemDTO.getItemID() == liquorID) {
                                String liqName = itemDTO.getName();
                                List<Item_priceDTO> read2 = item_priceBO.read();
                                for (Item_priceDTO item_priceDTO : read2) {
                                    if (item_priceDTO.getItemID() == itemDTO.getItemID()) {
                                        tbl_supplyOrdertbl.getItems().add(new SupplierOrderItemUtil(liquorID,
                                                itemDTO.getName(), supplierItemDetailsDTO.getQty(),
                                                item_priceDTO.getBuyingPrice()));
                                        tbl_supplyOrdertbl.refresh();
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }
            } catch (SQLException e) {
                Logger.getLogger("com.anuja.controller").log(Level.ALL,null,e);
                e.printStackTrace();
            }
            ViewAllSupplyOrdersController.grn = null;
        }
    }

    public void adLiquor(ActionEvent actionEvent) {
        int liquorid = (int) cmb_liquorId.getSelectionModel().getSelectedItem();
        int i=0;
        ObservableList<SupplierOrderItemUtil> items = tbl_supplyOrdertbl.getItems();
        for (SupplierOrderItemUtil supplierOrderItemUtil:items) {
            if (supplierOrderItemUtil.getLiquorID() == liquorid){
                supplierOrderItemUtil.setQty(Integer.parseInt(txt_qty.getText())+supplierOrderItemUtil.getQty());
                i++;
                tbl_supplyOrdertbl.refresh();
            }
        }
        if (i == 0) {
            tbl_supplyOrdertbl.getItems().add(new SupplierOrderItemUtil(liquorid,txt_liquorName.getText(),
                    Integer.parseInt(txt_qty.getText()),
                    Double.parseDouble(txt_bottlePrice.getText())));
        }
    }

    public void pendOrder(ActionEvent actionEvent) {
        if (txt_supplierName.getText().length()==0 || txt_liquorName.getText().length()==0 || txt_qty.getText().length()==0 || txt_bottlePrice.getText().length()==0){
            Alert alert = new Alert(Alert.AlertType.ERROR,"Enter all feilds");
            alert.show();
        }else {
            try {
                boolean insert = grnbo.insert(new Supplier_order_grnDTO(Integer.parseInt(txt_orderID.getText()), Integer.parseInt(String.valueOf(cmb_supplierId.getSelectionModel().getSelectedItem())), txt_date.getText()));
                ObservableList<SupplierOrderItemUtil> items = tbl_supplyOrdertbl.getItems();
                for (SupplierOrderItemUtil supplierOrderItemUtil:items) {
                    double newp = Double.parseDouble(txt_bottlePrice.getText()) + 300;
                    item_priceBO.update(new Item_priceDTO(supplierOrderItemUtil.getLiquorID(),Double.parseDouble(txt_bottlePrice.getText()),newp));
                    supplier_item_detailsBO.insert(new Supplier_item_detailsDTO(supplierOrderItemUtil.getLiquorID(),Integer.parseInt(txt_orderID.getText()),supplierOrderItemUtil.getQty()));
                }

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"GRN Created");
                alert.show();

            } catch (SQLException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR,"GRN Declined");
                alert.show();
                e.printStackTrace();
            }
        }

    }

    public void updateLiquor(ActionEvent actionEvent) {
        SupplierOrderItemUtil selectedItem = tbl_supplyOrdertbl.getSelectionModel().getSelectedItem();
        selectedItem.setLiquorID((Integer) cmb_liquorId.getSelectionModel().getSelectedItem());
        selectedItem.setName(txt_liquorName.getText());
        selectedItem.setQty(Integer.parseInt(txt_qty.getText()));
        selectedItem.setPrice(Double.parseDouble(txt_bottlePrice.getText()));
        tbl_supplyOrdertbl.refresh();

        btn_updateLiquor.setVisible(false);
        btn_addLiquor.setVisible(true);
    }

    public void goDashboard(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/Views/Dashboard.fxml"));
        Scene scene = new Scene(root);

        Stage stage = (Stage) btn_addLiquor.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Liquor Stock Management");
    }

    public void updateOrder(ActionEvent actionEvent) {
        if (txt_supplierName.getText().length()==0 || txt_liquorName.getText().length()==0 || txt_qty.getText().length()==0 || txt_bottlePrice.getText().length()==0){
            Alert alert = new Alert(Alert.AlertType.ERROR,"Enter all feilds");
            alert.show();
        }else {
            try {
                boolean update = grnbo.update(new Supplier_order_grnDTO(Integer.parseInt(txt_orderID.getText()), Integer.parseInt(String.valueOf(cmb_supplierId.getSelectionModel().getSelectedItem())), txt_date.getText()));
                ObservableList<SupplierOrderItemUtil> items = tbl_supplyOrdertbl.getItems();
                for (SupplierOrderItemUtil supplierOrderItemUtil:items) {
                    supplier_item_detailsBO.update(new Supplier_item_detailsDTO(supplierOrderItemUtil.getLiquorID(),Integer.parseInt(txt_orderID.getText()),supplierOrderItemUtil.getQty()));
                }
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"GRN Updated");
                alert.show();

            } catch (SQLException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR,"GRN Declined");
                alert.show();
                e.printStackTrace();
            }
        }
    }
}
