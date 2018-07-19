package com.miguelsanchez;

import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;

import static com.miguelsanchez.auxiliars.AllLettersPaneController.getAllLettersPane;

public class NewPlugboardController {
    @FXML
    private GridPane PlugConfigurationPane;
    private boolean firstTime = true;
    public void initialize () {
        if (firstTime) {
            PlugConfigurationPane.add(getAllLettersPane(), 0, 1);
            firstTime=false;
        }
    }
}
