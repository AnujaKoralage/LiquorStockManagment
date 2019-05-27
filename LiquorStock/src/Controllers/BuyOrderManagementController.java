package Controllers;

import BO.BOFactory;
import BO.BOTypes;
import BO.custom.*;
import DTO.*;
import UtilityClasses.*;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BuyOrderManagementController {
    public TextField txt_buyOrderId;
    public TextField txt_date;
    public TextField txt_onhandQty;
    public TextField txt_qty;
    public TextField txt_price;
    public ComboBox cmb_buyerId;
    public ComboBox cmb_liquorID;
    public TableView<BuyerOrderUtil> tbl_buyerOrder;
    public Button btn_addLiquor;
    public TextField txt_total;
    public Button btn_makeOrder;
    public Button btn_updateOrder;
    public Button btn_back;
    public Button btn_updateLiquor;
    public TextField txt_liquorName;
    public TextField txt_buyerName;

    private Buyer_grnBO buyer_grnBO = BOFactory.getInstance().getBO(BOTypes.BUYERGRN);
    private Buyer_item_detailsBO buyer_item_detailsBO = BOFactory.getInstance().getBO(BOTypes.BUYERGRNITEMS);
    private BuyerBO buyerBO = BOFactory.getInstance().getBO(BOTypes.BUYER);
    ItemBO itemBO =BOFactory.getInstance().getBO(BOTypes.ITEM);
    Item_priceBO item_priceBO =BOFactory.getInstance().getBO(BOTypes.ITEMPRICE);
    List<QuantityOnHand> quantityOnHands = new ArrayList<>();

    public void initialize(){
        txt_buyOrderId.setEditable(false);
        txt_buyerName.setEditable(false);
        txt_date.setEditable(false);
        txt_liquorName.setEditable(false);
        txt_onhandQty.setEditable(false);
        txt_price.setEditable(false);
        txt_total.setEditable(false);

        btn_addLiquor.setVisible(true);
        btn_updateLiquor.setVisible(false);
        btn_makeOrder.setVisible(true);
        btn_updateOrder.setVisible(false);

        tbl_buyerOrder.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("liquorID"));
        tbl_buyerOrder.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("liquorName"));
        tbl_buyerOrder.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("qty"));
        tbl_buyerOrder.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("price"));

        TableColumn<BuyerOrderUtil, BuyerOrderUtil> unfriendCol = new TableColumn<>("Delete");
        unfriendCol.setMinWidth(40);
        unfriendCol.setCellValueFactory(
                param -> new ReadOnlyObjectWrapper<>(param.getValue())
        );
        unfriendCol.setCellFactory(param -> new TableCell<BuyerOrderUtil, BuyerOrderUtil>() {
            private final Button deleteButton = new Button("Delete");

            @Override
            protected void updateItem(BuyerOrderUtil buyerOrderUtil, boolean empty) {

                super.updateItem(buyerOrderUtil, empty);

                if (buyerOrderUtil == null) {
                    setGraphic(null);
                    return;
                }

                setGraphic(deleteButton);
                deleteButton.setOnAction(
                        event -> {
                                ObservableList<BuyerOrderUtil> items = tbl_buyerOrder.getItems();
                                items.remove(buyerOrderUtil);
                                tbl_buyerOrder.refresh();
                                Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Liquor deleted");
                                alert.show();
                            for (QuantityOnHand quantityOnHand:quantityOnHands) {
                                if (quantityOnHand.getItemID() == buyerOrderUtil.getLiquorID()){
                                    quantityOnHand.setQty(quantityOnHand.getQty()+buyerOrderUtil.getQty());
                                    txt_onhandQty.setText(String.valueOf(quantityOnHand.getQty()));
                                }
                            }
                        }
                );
            }
        });
        tbl_buyerOrder.getColumns().add(unfriendCol);

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        txt_date.setText(dateFormat.format(date));

        try {
            txt_buyOrderId.setText(String.valueOf(buyer_grnBO.generateOrderID()));

            List<ItemDTO> read1 = itemBO.read();
            for (ItemDTO itemDTO:read1) {
                cmb_liquorID.getItems().add(itemDTO.getItemID());
            }

            for (ItemDTO itemDTO:read1) {
                quantityOnHands.add(new QuantityOnHand(itemDTO.getItemID(),itemBO.getTotalQty(itemDTO.getItemID())));
            }

            List<BuyerDTO> read = buyerBO.read();
            for (BuyerDTO buyerDTO:read) {
                cmb_buyerId.getItems().add(buyerDTO.getBuyerID());
            }

            cmb_buyerId.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
                @Override
                public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                    for (BuyerDTO buyerDTO:read) {
                        if (buyerDTO.getBuyerID() == Integer.parseInt(String.valueOf(cmb_buyerId.getSelectionModel().getSelectedItem()))){
                            txt_buyerName.setText(buyerDTO.getName());
                        }
                    }
                }
            });

            cmb_liquorID.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
                @Override
                public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                    for (ItemDTO itemDTO:read1) {
                        if (Integer.parseInt(String.valueOf(cmb_liquorID.getSelectionModel().getSelectedItem()))==itemDTO.getItemID()){
                            txt_liquorName.setText(itemDTO.getName());
                            for (QuantityOnHand quantityOnHand:quantityOnHands) {
                                if (quantityOnHand.getItemID() == itemDTO.getItemID())
                                    txt_onhandQty.setText(String.valueOf(quantityOnHand.getQty()));
                            }
                            try {
                                List<Item_priceDTO> read2 = item_priceBO.read();
                                for (Item_priceDTO item_priceDTO:read2) {
                                    if (item_priceDTO.getItemID() == itemDTO.getItemID())
                                        txt_price.setText(String.valueOf(item_priceDTO.getSellingPrice()));
                                }
                            } catch (SQLException e) {
                                Logger.getLogger("com.anuja.controller").log(Level.ALL,null,e);
                            }
                        }
                    }
                }
            });

        } catch (SQLException e) {
            e.printStackTrace();
        }

        tbl_buyerOrder.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<BuyerOrderUtil>() {
            @Override
            public void changed(ObservableValue<? extends BuyerOrderUtil> observable, BuyerOrderUtil oldValue, BuyerOrderUtil newValue) {
                btn_addLiquor.setVisible(false);
                btn_updateLiquor.setVisible(true);
                BuyerOrderUtil selectedItem = tbl_buyerOrder.getSelectionModel().getSelectedItem();

                Object obj = selectedItem.getLiquorID();
                cmb_liquorID.getSelectionModel().select(obj);
                txt_qty.setText(String.valueOf(selectedItem.getQty()));
            }
        });

        if (ViewAllBuyerOrdersController.selectedItem !=null){
            AllBuyerOrders selectedItem = ViewAllBuyerOrdersController.selectedItem;
            btn_updateOrder.setVisible(true);
            btn_makeOrder.setVisible(false);

            cmb_buyerId.getSelectionModel().select((Object)selectedItem.getOrderID());
            txt_buyOrderId.setText(String.valueOf(selectedItem.getOrderID()));

            List<Buyer_item_detailsDTO> read = null;
            try {
                read = buyer_item_detailsBO.read();
                for (Buyer_item_detailsDTO buyer_item_detailsDTO:read) {
                    if (buyer_item_detailsDTO.getBuyerGrnID() == selectedItem.getOrderID()){
                        List<ItemDTO> read1 = itemBO.read();
                        String itemName = null;
                        for (ItemDTO itemDTO:read1) {
                            if (itemDTO.getItemID() == buyer_item_detailsDTO.getItemID()){
                                itemName = itemDTO.getName();
                                break;
                            }
                        }
                        double itemPrice = 0;
                        List<Item_priceDTO> read2 = item_priceBO.read();
                        for (Item_priceDTO item_priceDTO:read2) {
                            if (item_priceDTO.getItemID() == buyer_item_detailsDTO.getItemID()){
                                itemPrice = item_priceDTO.getSellingPrice();
                            }
                        }
                        tbl_buyerOrder.getItems().add(new BuyerOrderUtil(buyer_item_detailsDTO.getItemID(),itemName,buyer_item_detailsDTO.getQty(),itemPrice));
                    }
                }

            } catch (SQLException e) {
                e.printStackTrace();
                Logger.getLogger("com.anuja.controller").log(Level.ALL,null,e);
            }
            ViewAllBuyerOrdersController.selectedItem =null;
        }
    }

    public void addLiquor(ActionEvent actionEvent) throws SQLException {
        if (txt_onhandQty.getText().length() ==0 || txt_qty.getText().length() == 0 || txt_price.getText().length()==0 || txt_liquorName.getText().length()==0){
            Alert alert = new Alert(Alert.AlertType.ERROR,"Enter all fields");
            alert.show();
        }else {
            ObservableList<BuyerOrderUtil> items = tbl_buyerOrder.getItems();
            int i=0;
            for (BuyerOrderUtil buyerOrderUtil:items) {
                if (buyerOrderUtil.getLiquorID()==Integer.parseInt(String.valueOf(cmb_liquorID.getSelectionModel().getSelectedItem()))){
                    i++;
                    buyerOrderUtil.setQty(Integer.parseInt(String.valueOf(buyerOrderUtil.getQty()+Integer.parseInt(txt_qty.getText()))));
                    for (QuantityOnHand quantityOnHand:quantityOnHands) {
                        if (quantityOnHand.getItemID() == Integer.parseInt(String.valueOf(cmb_liquorID.getSelectionModel().getSelectedItem()))){
                            if (quantityOnHand.getQty()>Integer.parseInt(txt_qty.getText())){
                                int newq = quantityOnHand.getQty()-Integer.parseInt(txt_qty.getText());
                                quantityOnHand.setQty(newq);
                                txt_onhandQty.setText(String.valueOf(newq));
                            }
                            else {
                                Alert alert = new Alert(Alert.AlertType.ERROR,"Quantity invalid");
                                alert.show();
                            }
                        }

                    }
                }
                tbl_buyerOrder.refresh();
            }
            if (i==0){
                if (Integer.parseInt(txt_onhandQty.getText())>Integer.parseInt(txt_qty.getText())){
                    tbl_buyerOrder.getItems().add(new BuyerOrderUtil(Integer.parseInt(String.valueOf(cmb_liquorID.getSelectionModel().getSelectedItem())),
                            txt_liquorName.getText(),Integer.parseInt(txt_qty.getText()),Double.parseDouble(txt_price.getText())));
                    for (QuantityOnHand quantityOnHand:quantityOnHands) {
                        if (quantityOnHand.getItemID() == Integer.parseInt(String.valueOf(cmb_liquorID.getSelectionModel().getSelectedItem()))){
                            int newq = quantityOnHand.getQty()-Integer.parseInt(txt_qty.getText());
                            quantityOnHand.setQty(newq);
                            txt_onhandQty.setText(String.valueOf(newq));
                        }

                    }
                }else {
                    Alert alert = new Alert(Alert.AlertType.ERROR,"Quantity invalid");
                    alert.show();
                }
            }
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Liquor added");
            alert.show();
        }
    }

    public void makeOrder(ActionEvent actionEvent) {
        if (txt_buyOrderId.getText().length()==0 || txt_date.getText().length()==0 || txt_buyerName.getText().length()==0){
            Alert alert = new Alert(Alert.AlertType.ERROR,"Enter all feilds");
            alert.show();
        }
        else {
            Connection connection = DBConnection.getInstnce().getConnection();
            try {
                connection.setAutoCommit(false);
                    boolean insert = buyer_grnBO.insert(new Buyer_GRNDTO(Integer.parseInt(txt_buyOrderId.getText()),
                            Integer.parseInt(cmb_buyerId.getSelectionModel().getSelectedItem().toString()),
                            txt_date.getText()));
                    if (!insert){
                        connection.rollback();
                        return;
                    }

                    ObservableList<BuyerOrderUtil> items = tbl_buyerOrder.getItems();
                    for (BuyerOrderUtil buyerOrderUtil:items) {
                        boolean insert1 = buyer_item_detailsBO.insert(new Buyer_item_detailsDTO(buyerOrderUtil.getLiquorID(), Integer.parseInt(txt_buyOrderId.getText()), buyerOrderUtil.getQty()));
                        if (!insert1){
                            connection.rollback();
                            return;
                        }
                    }
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Order completed");
                    alert.show();

                InputStream resourceAsStream = this.getClass().getResourceAsStream("/JasperReports/LiquorBUYGRN.jrxml");
                InputStream subresourceAsStream = this.getClass().getResourceAsStream("/JasperReports/subre.jrxml");
                JasperDesign load,loadsub = null;
                try {
                    load = JRXmlLoader.load(resourceAsStream);
                    loadsub = JRXmlLoader.load(subresourceAsStream);
                    JasperReport jasperReport = JasperCompileManager.compileReport(load);
                    JasperReport jaspersubReport = JasperCompileManager.compileReport(loadsub);

                    Map<String, Object> pram = new HashMap<>();
                    pram.put("grnID",txt_buyOrderId.getText());
                    pram.put("subReport",loadsub);

                    JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, pram, DBConnection.getInstnce().getConnection());
                    JasperViewer.viewReport(jasperPrint,false);
                } catch (JRException e) {
                    e.printStackTrace();
                }

            } catch (Throwable t) {
                Alert alert  = new Alert(Alert.AlertType.ERROR,"data not inserted");
                alert.show();
                Logger.getLogger("com.anuja.controller").log(Level.ALL,null,t);
                t.printStackTrace();
            }
            finally {
                try {
                    connection.setAutoCommit(true);
                } catch (SQLException e) {
                    Logger.getLogger("com.anuja.controller").log(Level.ALL,null,e);
                    e.printStackTrace();
                }
            }


            txt_liquorName.setText("");
            txt_price.setText("");
            txt_onhandQty.setText("");
            txt_qty.setText("");
            txt_buyerName.setText("");

            btn_addLiquor.setVisible(true);
            btn_updateLiquor.setVisible(false);
            btn_updateOrder.setVisible(false);
            btn_makeOrder.setVisible(true);
            try {
                txt_buyOrderId.setText(String.valueOf(buyer_grnBO.generateOrderID()));
            } catch (SQLException e) {
                e.printStackTrace();
                Logger.getLogger("com.anuja.controller").log(Level.ALL,null,e);
            }
            tbl_buyerOrder.getItems().clear();
        }
    }

    public void updateOrder(ActionEvent actionEvent) {
        if (txt_buyOrderId.getText().length()==0 || txt_date.getText().length()==0 || txt_buyerName.getText().length()==0){
            Alert alert= new Alert(Alert.AlertType.ERROR,"Enter all feilds");
            alert.show();
        }
        else {
            try {
                DBConnection.getInstnce().getConnection().setAutoCommit(false);
                buyer_grnBO.update(new Buyer_GRNDTO(Integer.parseInt(txt_buyOrderId.getText()),
                        Integer.parseInt(cmb_buyerId.getSelectionModel().getSelectedItem().toString()),
                        txt_date.getText()));
                ObservableList<BuyerOrderUtil> items = tbl_buyerOrder.getItems();
                for (BuyerOrderUtil buyerOrderUtil:items) {
                    buyer_item_detailsBO.update(new Buyer_item_detailsDTO(buyerOrderUtil.getLiquorID(),Integer.parseInt(txt_buyOrderId.getText()),buyerOrderUtil.getQty()));
                }
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Order update successfully");
                alert.show();

                InputStream resourceAsStream = this.getClass().getResourceAsStream("/JasperReports/LiquorBUYGRN.jrxml");
                InputStream subresourceAsStream = this.getClass().getResourceAsStream("/JasperReports/subre.jrxml");
                JasperDesign load,loadsub = null;

                    load = JRXmlLoader.load(resourceAsStream);
                    loadsub = JRXmlLoader.load(subresourceAsStream);
                    JasperReport jasperReport = JasperCompileManager.compileReport(load);
                    JasperReport jaspersubReport = JasperCompileManager.compileReport(loadsub);

                    Map<String, Object> pram = new HashMap<>();
                    pram.put("grnID",txt_buyOrderId.getText());
                    pram.put("subReport",loadsub);

                    JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, pram, DBConnection.getInstnce().getConnection());
                    JasperViewer.viewReport(jasperPrint,false);


            } catch (Throwable e) {
                Alert alert = new Alert(Alert.AlertType.ERROR,"Unable to update order");
                alert.show();
                e.printStackTrace();
                Logger.getLogger("com.anuja.controller").log(Level.ALL,null,e);
            }finally {
                try {
                    DBConnection.getInstnce().getConnection().setAutoCommit(true);
                } catch (SQLException e) {
                    e.printStackTrace();
                    Logger.getLogger("com.anuja.controller").log(Level.ALL,null,e);
                }
            }
            txt_liquorName.setText("");
            txt_price.setText("");
            txt_onhandQty.setText("");
            txt_qty.setText("");
            txt_buyerName.setText("");

            btn_addLiquor.setVisible(true);
            btn_updateLiquor.setVisible(false);
            btn_updateOrder.setVisible(false);
            btn_makeOrder.setVisible(true);
            try {
                txt_buyOrderId.setText(String.valueOf(buyer_grnBO.generateOrderID()));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void goDashboard(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/Views/Dashboard.fxml"));
        Scene scene = new Scene(root);

        Stage stage = (Stage) btn_addLiquor.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Liquor Stock Management");
    }

    public void updateLiquor(ActionEvent actionEvent) {
        if (txt_onhandQty.getText().length() ==0 || txt_qty.getText().length() == 0 || txt_price.getText().length()==0 || txt_liquorName.getText().length()==0){
            Alert alert = new Alert(Alert.AlertType.ERROR,"Enter all feilds");
            alert.show();
        }else {
            for (QuantityOnHand quantityOnHand:quantityOnHands) {
                if (quantityOnHand.getItemID() == tbl_buyerOrder.getSelectionModel().getSelectedItem().getLiquorID()){
                    if (tbl_buyerOrder.getSelectionModel().getSelectedItem().getQty()>=Integer.parseInt(txt_qty.getText())){
                        int addQty = tbl_buyerOrder.getSelectionModel().getSelectedItem().getQty()-Integer.parseInt(txt_qty.getText());

                        quantityOnHand.setQty(quantityOnHand.getQty()+addQty);
                    }
                    else if (tbl_buyerOrder.getSelectionModel().getSelectedItem().getQty()<Integer.parseInt(txt_qty.getText())){
                        int subQty = Integer.parseInt(txt_qty.getText())-tbl_buyerOrder.getSelectionModel().getSelectedItem().getQty();
                        quantityOnHand.setQty(quantityOnHand.getQty()-subQty);
                    }
                    txt_onhandQty.setText(String.valueOf(quantityOnHand.getQty()));
                    tbl_buyerOrder.refresh();
                    break;
                }
            }
            tbl_buyerOrder.getSelectionModel().getSelectedItem().setQty(Integer.parseInt(txt_qty.getText()));
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Liquor updated successfully");
            alert.show();

            txt_liquorName.setText("");
            txt_price.setText("");
            txt_onhandQty.setText("");
            txt_qty.setText("");
            btn_addLiquor.setVisible(true);
            btn_updateLiquor.setVisible(false);
        }
    }


    public void forButton(MouseEvent mouseEvent) {
        btn_updateLiquor.setVisible(false);
        btn_addLiquor.setVisible(true);
    }
}
