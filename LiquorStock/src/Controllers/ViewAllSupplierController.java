package Controllers;

import BO.BO;
import BO.BOFactory;
import BO.BOTypes;
import BO.custom.SupplierBO;
import DTO.SupplierDTO;
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

public class ViewAllSupplierController {
    public Button btn_back;
    public TableView<SupplierUtil> tbl_allSuppliers;
    public static SupplierUtil data=null;

    public void initialize(){
        tbl_allSuppliers.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("supplierid"));
        tbl_allSuppliers.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("supplierName"));
        tbl_allSuppliers.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("address"));

        ObservableList<SupplierUtil> items = tbl_allSuppliers.getItems();
        BO supplierBO = BOFactory.getInstance().getBO(BOTypes.SUPPLIER);

        TableColumn<SupplierUtil, SupplierUtil> unfriendCol = new TableColumn<>("Delete");
        unfriendCol.setMinWidth(40);
        unfriendCol.setCellValueFactory(
                param -> new ReadOnlyObjectWrapper<>(param.getValue())
        );
        unfriendCol.setCellFactory(param -> new TableCell<SupplierUtil, SupplierUtil>() {
            private final Button deleteButton = new Button("Delete");

            @Override
            protected void updateItem(SupplierUtil supplierUtil, boolean empty) {

                super.updateItem(supplierUtil, empty);

                if (supplierUtil == null) {
                    setGraphic(null);
                    return;
                }

                setGraphic(deleteButton);
                deleteButton.setOnAction(
                        event -> {

                            try {
                                ObservableList<SupplierUtil> items = tbl_allSuppliers.getItems();
                                supplierBO.delete(new SupplierDTO(supplierUtil.getSupplierid(),supplierUtil.getSupplierName(),supplierUtil.getAddress()));
                                items.remove(supplierUtil);
                                tbl_allSuppliers.refresh();
                            } catch (SQLException e) {
                                Logger.getLogger("com.anuja.controller").log(Level.ALL,null,e);
                                //e.printStackTrace();
                                Alert alert = new Alert(Alert.AlertType.ERROR,"cannot delete supplier");
                                alert.show();
                            }
                        }
                );
            }
        });
        tbl_allSuppliers.getColumns().add(unfriendCol);

        try {
            List<SupplierDTO> read = supplierBO.read();
            for (int i=0;i<read.size();i++){
                items.add(new SupplierUtil(read.get(i).getId(),read.get(i).getName(),read.get(i).getAddress()));
            }
            tbl_allSuppliers.refresh();
        } catch (SQLException e) {
            Logger.getLogger("com.anuja.controller").log(Level.ALL,null,e);
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR,"Cant fetch suppliers");
            alert.show();
        }

        tbl_allSuppliers.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<SupplierUtil>() {
            @Override
            public void changed(ObservableValue<? extends SupplierUtil> observable, SupplierUtil oldValue, SupplierUtil newValue) {
                SupplierUtil selectedItem = tbl_allSuppliers.getSelectionModel().getSelectedItem();

                Parent root = null;
                try {
                    data = selectedItem;
                    root = FXMLLoader.load(this.getClass().getResource("/Views/SupplierManagment.fxml"));
                } catch (IOException e) {
                    Logger.getLogger("com.anuja.controller").log(Level.ALL,null,e);
                    e.printStackTrace();
                    Alert alert = new Alert(Alert.AlertType.ERROR,"Cant load interface");
                    alert.show();
                }
                Scene manageSupplier = new Scene(root);

                Stage stage = (Stage) btn_back.getScene().getWindow();
                stage.setScene(manageSupplier);
            }
        });
    }

    public void backToSupplierManagment(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/Views/SupplierManagment.fxml"));
        Scene manageSupplier = new Scene(root);

        Stage stage = (Stage) btn_back.getScene().getWindow();
        stage.setScene(manageSupplier);

    }
}
