package com.miguelsanchez;

import com.miguelsanchez.auxiliars.AllLettersPaneController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.GridPane;

public class NewPlugboardController {
    @FXML
    private GridPane PlugConfigurationPane;

    private FXMLLoader myfxml = new FXMLLoader(getClass().getResource("./auxiliars/AllLettersPane.fxml"));

    @FXML
    void addAllLetters () {
        AllLettersPaneController controller = myfxml.getController();
        PlugConfigurationPane.add(controller.getAllLettersPane(), 0, 1);
    }

}
