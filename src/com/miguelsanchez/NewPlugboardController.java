package com.miguelsanchez;

import com.miguelsanchez.auxiliars.AddAllLettersOnGP;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.GridPane;

public class NewPlugboardController {
    @FXML
    private GridPane PlugConfigurationPane, AllLettersPane;

    private FXMLLoader myfxml = new FXMLLoader(getClass().getResource("./auxiliars/AllLettersPane.fxml"));

    void addAllLetters () {
        AddAllLettersOnGP myPane = new AddAllLettersOnGP(PlugConfigurationPane, 0, 1);
    }

}
