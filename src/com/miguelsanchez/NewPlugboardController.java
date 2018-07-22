package com.miguelsanchez;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;

import static com.miguelsanchez.auxiliars.AllLettersPane.*;
import static com.miguelsanchez.auxiliars.Alphabet.getAlphabet;

public class NewPlugboardController {

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
