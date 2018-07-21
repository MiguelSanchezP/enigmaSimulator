package com.miguelsanchez;

import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;

import static com.miguelsanchez.auxiliars.AllLettersPane.generateTheAllLettersPane;

public class NewPlugboardController {

    @FXML
    private GridPane PlugConfigurationPane;

    @FXML
    void configureTheGridPane () {
        PlugConfigurationPane.add(generateTheAllLettersPane(), 0, 1);
    }
}
