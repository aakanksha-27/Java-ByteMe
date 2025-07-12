package com.aakanksha.ap_assignment_4;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayDeque;
import java.util.Queue;

public class PendingOrders implements Serializable {
    @FXML
    public void onMenuClick(ActionEvent event) {
        changeScene(event, "menu.fxml");
    }

    @FXML
    public void onBackClick(ActionEvent event) {
        changeScene(event, "homescreen.fxml");
    }

    private void changeScene(ActionEvent event, String fxml) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(fxml));
            Stage curr = (Stage)((Node)event.getSource()).getScene().getWindow();
            curr.setScene(new Scene(root));
            curr.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private TableView<Order> PpendingOrders;

    @FXML
    private TableColumn<Order,Integer> PorderID;

    @FXML
    private TableColumn<Order,String> Pitem;

    @FXML
    private TableColumn<Order,String> Pstatus;

    @FXML
    public void initialize() {
        PorderID.setCellValueFactory(new PropertyValueFactory<>("orderID"));
        Pitem.setCellValueFactory(cellData -> {
            Item item = cellData.getValue().getItem();
            return new SimpleStringProperty(item.getName());
        });
        Pstatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        Queue<Order> orders = new ArrayDeque<>();
        orders.addAll(Database.getVIPOrders());
        orders.addAll(Database.getnonVIPOrders());
        ObservableList<Order> table = FXCollections.observableArrayList(orders);
        PpendingOrders.setItems(table);
    }
}