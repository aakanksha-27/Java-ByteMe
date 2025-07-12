package com.aakanksha.ap_assignment_4;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.Serializable;

public class Homescreen implements Serializable {
    @FXML
    public void onMenuClick(ActionEvent event) {
        changeScene(event, "menu.fxml");
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
}
