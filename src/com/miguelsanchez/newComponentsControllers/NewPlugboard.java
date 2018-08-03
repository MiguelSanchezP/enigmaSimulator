package com.miguelsanchez.newComponentsControllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Callback;

import java.util.HashMap;

import static com.miguelsanchez.Controller.getAlphabetComponents;
import static com.miguelsanchez.auxiliars.FillComboBox.alphabetsContentFill;

public class NewPlugboard {

    @FXML
    private GridPane PlugConfigurationPane;
    @FXML
    private RadioButton RBConfigureLater, RBDoubleWire;
    @FXML
    private ComboBox <String> CBFirstCharacter, CBSecondCharacter;
    @FXML
    private Button BConfirmation;

    private HashMap<String, String> plugConfiguration = new HashMap<>();

    public void initialize () {
        alphabetsContentFill(CBFirstCharacter, getAlphabetComponents());
        alphabetsContentFill(CBSecondCharacter, getAlphabetComponents());
        CBFirstCharacter.setPromptText("A");
        CBSecondCharacter.setPromptText("A");
    }


    @FXML
    private void handleRBConfigureLater () {
        if (RBConfigureLater.isSelected()) {
            RBDoubleWire.setDisable(true);
        }else{
            PlugConfigurationPane.getChildren().get(0).setDisable(false);
            PlugConfigurationPane.getChildren().get(2).setDisable(false);
        }
    }
    @FXML
    private void handleBConfirmation () {
        if (RBDoubleWire.isSelected()) {
            String cA = CBFirstCharacter.getValue();
            String cB = CBSecondCharacter.getValue();
            plugConfiguration.put(cA, cB);
            plugConfiguration.put(cB, cA);
            CBSecondCharacter.getItems().remove(cA);
            CBSecondCharacter.getItems().remove(cB);
            CBFirstCharacter.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
                @Override
                public ListCell<String> call(ListView<String> param) {
                    return new ListCell<String>() {
                        @Override
                        protected void updateItem (String item, boolean empty) {
                            super.updateItem(item, empty);
                            if (item!=null) {
                                setText(item);
                                //This won't work for the first time but will be the one
                                //used in the next calls
                                getStyleClass().add("my-list-cell");
                                setTextFill(Color.RED);
                                //size in px
                                setFont(Font.font(16));
                            }
                        }
                    };
                }
            });
        }
    }
}
