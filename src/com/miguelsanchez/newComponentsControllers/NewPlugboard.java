package com.miguelsanchez.newComponentsControllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.ArrayList;
import java.util.HashMap;

import static com.miguelsanchez.Controller.getAlphabetComponents;
import static com.miguelsanchez.auxiliars.FillComboBox.alphabetsContentFill;

public class NewPlugboard {

    @FXML
    private RadioButton RBConfigureLater, RBDoubleWire;
    @FXML
    private ComboBox<String> CBFirstCharacter, CBSecondCharacter;
    @FXML
    private Button BConfirmation;
    @FXML
    private Label LInformation;
    @FXML
    private TextArea TAConfiguredLetters;
    @FXML
    private ToggleButton BForce;

    private HashMap<String, String> plugConfiguration = new HashMap<>();
    private ArrayList<String> letters = new ArrayList<>();

    public void initialize() {
        alphabetsContentFill(CBFirstCharacter, getAlphabetComponents());
        alphabetsContentFill(CBSecondCharacter, getAlphabetComponents());
        CBFirstCharacter.setPromptText("Select");
        CBSecondCharacter.setPromptText("Select");
        TAConfiguredLetters.setText("Configured characters (double wire):\n");
    }


    @FXML
    private void handleRBConfigureLater() {
        if (RBConfigureLater.isSelected()) {
            RBDoubleWire.setDisable(true);
            CBFirstCharacter.setDisable(true);
            CBSecondCharacter.setDisable(true);
            BConfirmation.setDisable(true);
            LInformation.setDisable(true);
            TAConfiguredLetters.setDisable(true);
        }
    }

    @FXML
    private void handleBConfirmation() {
        if (RBDoubleWire.isSelected()) {//finish the other cases
            String cA = CBFirstCharacter.getValue();
            String cB = CBSecondCharacter.getValue();
            if (plugConfiguration.containsKey(cA)) {
                if (BForce.isSelected()) {
                    forceValue(plugConfiguration.get(cA), cA, cB);
                }else {
                    LInformation.setText("First letter already configured in the pair (" + plugConfiguration.get(cA) + "," + cA + ")");
                }
            }else if (plugConfiguration.containsKey(cB)) {
                if (BForce.isSelected()) {
                    forceValue(plugConfiguration.get(cB), cB, cA);
                }
                LInformation.setText("Second letter already used in the pair (" + plugConfiguration.get(cB) + "," + cB + ")");
            }else if ((cA!=null) && cA.equals(cB) || (cB!=null) && cB.equals(cA)) {
                LInformation.setText("Cannot set a wire to its same position");
            } else {
                plugConfiguration.put(cA, cB);
                plugConfiguration.put(cB, cA);
                LInformation.setText("Value added successfully");
                letters.add(cA);
                letters.add(cB);
                TAConfiguredLetters.setText("Configured characters (double wire):\n" + toString(letters));
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
        StringBuilder sb = new StringBuilder();
        sb.setLength(0);
        int value = 0;
        for (String s : myList) {
            sb.append(s);
            value+=1;
            if (value%2 == 0) {
                sb.append(" / ");
            }else{
                sb.append("-");
            }
        }
        return sb.toString();
    }

    private void forceValue (String prevVal, String constVar, String newVar) {
        if (plugConfiguration.containsKey(newVar)) {
            remove (newVar, plugConfiguration.get(newVar));
        }
        plugConfiguration.remove(prevVal, constVar);
        plugConfiguration.remove(constVar, prevVal);
        plugConfiguration.put(constVar, newVar);
        plugConfiguration.put(newVar, constVar);
        letters.remove(prevVal);
        letters.remove(constVar);
        letters.add(constVar);
        letters.add(newVar);
        TAConfiguredLetters.setText("Configured characters (double wire):\n" + toString(letters));
        LInformation.setText("Value changed successfully");
        BForce.setSelected(false);
    }

    private void remove (String val1, String val2) {
        plugConfiguration.remove(val1, val2);
        plugConfiguration.remove(val2, val1);
        letters.remove(val1);
        letters.remove(val2);
    }
}
