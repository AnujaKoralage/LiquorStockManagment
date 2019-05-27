package Controllers;

import BO.BOFactory;
import BO.BOTypes;
import BO.custom.Impl.QueryBOImpl;
import BO.custom.ItemBO;
import BO.custom.Item_priceBO;
import BO.custom.QueryBO;
import DAO.DAO.custom.DAO.custom.Impl.QueryDAOImpl;
import DTO.ItemDTO;
import DTO.Item_priceDTO;
import DTO.SearchDTO;
import UtilityClasses.CheckStock;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CheckStocksController {
    public TableView<CheckStock> tbl_searchResults;
    public TextField txt_search;
    private ObservableList<CheckStock> old;

    public void initialize(){
       ItemBO itemBO = BOFactory.getInstance().getBO(BOTypes.ITEM);
       Item_priceBO item_priceBO = BOFactory.getInstance().getBO(BOTypes.ITEMPRICE);

       tbl_searchResults.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("liquorID"));
       tbl_searchResults.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("liquorName"));
       tbl_searchResults.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("qtyOnhand"));
       tbl_searchResults.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("sellingPrice"));
       tbl_searchResults.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("buyingPrince"));

        try {
            List<ItemDTO> itemDTOList = itemBO.read();
            List<Item_priceDTO> priceDTOList = item_priceBO.read();

            for (ItemDTO itemDTO : itemDTOList) {
                double sellingPrice = 0,buyingPrice =0;
                for (Item_priceDTO item_priceDTO:priceDTOList) {
                    if (item_priceDTO.getItemID() == itemDTO.getItemID()){
                        sellingPrice = item_priceDTO.getSellingPrice();
                        buyingPrice = item_priceDTO.getBuyingPrice();
                        break;
                    }
                }
                tbl_searchResults.getItems().add(new CheckStock(itemDTO.getItemID(),itemDTO.getName(),itemBO.getTotalQty(itemDTO.getItemID()),
                        sellingPrice,buyingPrice));
            }
            old = tbl_searchResults.getItems();

        } catch (SQLException e) {
            Logger.getLogger("com.anuja.controller").log(Level.ALL,null,e);
            e.printStackTrace();
        }

        txt_search.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                QueryBO queryDAO = new QueryBOImpl();
                try {
                    if (txt_search.getText().length() != 0){
                        List<SearchDTO> searchDTOS = queryDAO.searchLiquorID(Integer.parseInt(txt_search.getText()));
                        if (searchDTOS.size()>0){
                            List<CheckStock> checkStocks = new ArrayList<>();
                            for (SearchDTO searchDTO:searchDTOS) {
                                checkStocks.add(new CheckStock(searchDTO.getLiquorID(),searchDTO.getLiquorName(),
                                        itemBO.getTotalQty(searchDTO.getLiquorID()),searchDTO.getBuyingPrince(),searchDTO.getSellingPrice()));
                            }
                            tbl_searchResults.setItems(FXCollections.observableList(checkStocks));
                        }
                        else {
                            tbl_searchResults.setItems(old);
                        }
                    }
                    else {
                        tbl_searchResults.setItems(old);
                    }
                } catch (SQLException e) {
                    Logger.getLogger("com.anuja.controller").log(Level.ALL,null,e);
                    e.printStackTrace();
                }
            }
        });
    }

    public void goDashboard(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/Views/Dashboard.fxml"));
        Scene scene = new Scene(root);

        Stage stage = (Stage) txt_search.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Liquor Stock Management");
    }
}
