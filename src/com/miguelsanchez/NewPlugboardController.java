package com.miguelsanchez;

import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;

import javafx.event.EventHandler;

import static com.miguelsanchez.auxiliars.AllLettersPane.getAllLettersPane;
import static com.miguelsanchez.auxiliars.AllLettersPane.proccessKey;

public class NewPlugboardController {

    @FXML
    private GridPane PlugConfigurationPane;
    @FXML
    private RadioButton RBConfigureLater, RBDoubleWire;

    private boolean doubleWire;

    void configureTheGridPane () {
        PlugConfigurationPane.add(getAllLettersPane(), 0, 1);
    }

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

    @FXML
    private void handleRBDoubleWire () {
        doubleWire = RBDoubleWire.isSelected();
    }

    String t;
    @FXML
    private void handleKeyPressed () {
        EventHandler keyEvent = new EventHandler <KeyEvent> () {
            @Override
            public void handle (KeyEvent event) {
                t = event.getCharacter();
            }
        };
        proccessKey(doubleWire, PlugConfigurationPane, t);
    }
}
