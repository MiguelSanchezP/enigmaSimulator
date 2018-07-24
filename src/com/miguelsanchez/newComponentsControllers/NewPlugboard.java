package com.miguelsanchez.newComponentsControllers;

import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.GridPane;

public class NewPlugboard {

    @FXML
    private GridPane PlugConfigurationPane;
    @FXML
    private RadioButton RBConfigureLater, RBDoubleWire;

    private boolean doubleWire;


    @FXML
    private void handleRBConfigureLater () {
        if (RBConfigureLater.isSelected()) {
            PlugConfigurationPane.getChildren().get(0).setDisable(true);
            PlugConfigurationPane.getChildren().get(2).setDisable(true);
        }else{
            PlugConfigurationPane.getChildren().get(0).setDisable(false);
            PlugConfigurationPane.getChildren().get(2).setDisable(false);
        }
    }


}
