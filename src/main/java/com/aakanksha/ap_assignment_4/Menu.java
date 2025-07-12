package com.aakanksha.ap_assignment_4;

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

public class Menu implements Serializable {
    @FXML
    public void onBackClick(ActionEvent event) {
        changeScene(event, "homescreen.fxml");
    }

    @FXML
    public void onPOrdersClick(ActionEvent event) {
        changeScene(event, "pending-orders.fxml");
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
    private TableView<Item> Imenu;

    @FXML
    private TableColumn<Item,String> Iname;

    @FXML
    private TableColumn<Item, Integer> Iprice;

    @FXML
    private TableColumn<Item, Boolean> Iavailability;

    @FXML
    public void initialize() {
        Iname.setCellValueFactory(new PropertyValueFactory<>("name"));
        Iprice.setCellValueFactory(new PropertyValueFactory<>("price"));
        Iavailability.setCellValueFactory(new PropertyValueFactory<>("available"));
        Queue<Item> items = new ArrayDeque<>();
        items.addAll(Database.getMenu());
        ObservableList<Item> table = FXCollections.observableArrayList(items);
        Imenu.setItems(table);
    }
}
