package com.miguelsanchez.newComponentsControllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import static com.miguelsanchez.Controller.getAlphabetComponents;
import static com.miguelsanchez.auxiliars.FillComboBox.alphabetsContentFill;

public class NewPlugboard {

    @FXML
    private GridPane PlugConfigurationPane;
    @FXML
    private RadioButton RBConfigureLater, RBDoubleWire;
    @FXML
    private ComboBox<String> CBFirstCharacter, CBSecondCharacter;
//    @FXML
//    private Button BConfirmation;
    @FXML
    private Label LInformation, LConfiguredLetters;

    private HashMap<String, String> plugConfiguration = new HashMap<>();
    private ArrayList<String> letters = new ArrayList<>();

    public void initialize() {
        alphabetsContentFill(CBFirstCharacter, getAlphabetComponents());
        alphabetsContentFill(CBSecondCharacter, getAlphabetComponents());
        CBFirstCharacter.setPromptText("Select");
        CBSecondCharacter.setPromptText("Select");
    }


    @FXML
    private void handleRBConfigureLater() {
        if (RBConfigureLater.isSelected()) {
            RBDoubleWire.setDisable(true);
        } else {
            PlugConfigurationPane.getChildren().get(0).setDisable(false);
            PlugConfigurationPane.getChildren().get(2).setDisable(false);
        }
    }

    @FXML
    private void handleBConfirmation() {
        if (RBDoubleWire.isSelected()) {//finish the other cases
            String cA = CBFirstCharacter.getValue();
            String cB = CBSecondCharacter.getValue();
            if (plugConfiguration.containsKey(cA)) {
                LInformation.setText("First letter already configured in the pair (" + plugConfiguration.get(cA) + "," + cA + ")");
            }else if (plugConfiguration.containsKey(cB)) {
                LInformation.setText("Second letter already used in the pair (" + plugConfiguration.get(cB) + "," + cB + ")");
            }else if ((cA!=null) && cA.equals(cB) || (cB!=null) && cB.equals(cA)) {
                LInformation.setText("Cannot set a wire to its same position");
            } else {
                plugConfiguration.put(cA, cB);
                plugConfiguration.put(cB, cA);
                LInformation.setText("Value added successfully");
                letters.add(cA);
                letters.add(cB);
                LConfiguredLetters.setText("Configured: " + toString(letters));
            }
        }
    }

    @FXML
    private void handleCBFirstCharacter() {
        String cA = CBFirstCharacter.getValue();
        if (plugConfiguration.containsKey(cA)) {
            CBSecondCharacter.setValue(plugConfiguration.get(cA));
            LInformation.setText("Value already defined");
        } else {
            CBSecondCharacter.setValue(cA);
        }
    }

    private String toString (ArrayList<String> myList) {
        Collections.sort(myList);
        StringBuilder sb = new StringBuilder();
        sb.setLength(0);
        for (String s : myList) {
            sb.append(s);
        }
        return sb.toString();
    }
}
