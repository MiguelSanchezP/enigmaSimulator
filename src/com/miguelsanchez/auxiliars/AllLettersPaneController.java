package com.miguelsanchez.auxiliars;

import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;

public class AllLettersPaneController {
    @FXML
    private GridPane AllLettersPane;

    public void setOnGridPane (GridPane gp, int c, int r) {
        gp.add(AllLettersPane, c, r);
    }
}
