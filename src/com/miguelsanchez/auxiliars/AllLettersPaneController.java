package com.miguelsanchez.auxiliars;

import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;

public class AllLettersPaneController {
    @FXML
    private static GridPane AllLettersPane;

    public static GridPane getAllLettersPane () {
        return AllLettersPane;
    }
}
