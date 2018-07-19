package com.miguelsanchez.auxiliars;

import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;

public class AddAllLettersOnGP {
    @FXML
    private GridPane AllLettersPane;
    public AddAllLettersOnGP (GridPane gp, int c, int r) {
        gp.add(AllLettersPane, c, r);
    }
}
