package com.miguelsanchez.newComponentsControllers;
/*ADD the maximum letters available*/

import com.miguelsanchez.components.PlugBoard;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static com.miguelsanchez.Controller.getAlphabetComponents;
import static com.miguelsanchez.auxiliars.FillComboBox.alphabetsContentFill;

public class NewPlugboard {

    @FXML
    private RadioButton RBConfigureLater, RBDoubleWire;
    @FXML
    private ComboBox<String> CBFirstCharacter, CBSecondCharacter;
    @FXML
    private Button BConfirmation, BClear;
    @FXML
    private Label LInformation;
    @FXML
    private TextArea TAConfiguredLetters;
    @FXML
    private ToggleButton TBForce;

    private HashMap<String, String> plugConfigurationDouble = new HashMap<>();
    private ArrayList<String> lettersDouble = new ArrayList<>();
    private HashMap<String, String> plugConfigurationSimple = new HashMap<>();
    private ArrayList<String> lettersSimple = new ArrayList<>();

    public void initialize() {
        alphabetsContentFill(CBFirstCharacter, getAlphabetComponents());
        alphabetsContentFill(CBSecondCharacter, getAlphabetComponents());
        CBFirstCharacter.setPromptText("Select");
        CBSecondCharacter.setPromptText("Select");
        TAConfiguredLetters.setText("Configured characters (double wire):\n");
    }

    public void setPlugboard (PlugBoard pb) {
        RBConfigureLater.setSelected (pb.isConfigureLater());
        RBDoubleWire.setSelected(pb.isDouble());
        plugConfigurationSimple = pb.getConfiguredLettersSimple();
        plugConfigurationDouble = pb.getConfiguredLettersDouble();
        lettersSimple = pb.getLettersSimple();
        lettersDouble = pb.getLettersDouble();
        if (RBDoubleWire.isSelected()) {
            TAConfiguredLetters.setText("Configured characters (double wire):\n" + toString(lettersDouble));
        }else if (!RBConfigureLater.isSelected()){
            TAConfiguredLetters.setText("Configured characters (single wire):\n" + toString(lettersSimple));
        }
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
            TBForce.setDisable(true);
            BClear.setDisable(true);
        }else{
            RBDoubleWire.setDisable(false);
            CBFirstCharacter.setDisable(false);
            CBSecondCharacter.setDisable(false);
            BConfirmation.setDisable(false);
            LInformation.setDisable(false);
            TAConfiguredLetters.setDisable(false);
            TBForce.setDisable(false);
            BClear.setDisable(false);
        }
    }

    @FXML
    private void handleBConfirmation() {
        if (RBDoubleWire.isSelected()) {//finish the other cases --> in theory done
            String cA = CBFirstCharacter.getValue();
            String cB = CBSecondCharacter.getValue();
            if (plugConfigurationDouble != null) {
                if (plugConfigurationDouble.containsKey(cA)) {
                    if (TBForce.isSelected()) {
                        forceValue(plugConfigurationDouble.get(cA), cA, cB);
                    } else {
                        LInformation.setText("First character configured in the pair (" + plugConfigurationDouble.get(cA) + "," + cA + ")");
                    }
                } else if (plugConfigurationDouble.containsKey(cB)) {
                    if (TBForce.isSelected()) {
                        forceValue(plugConfigurationDouble.get(cB), cB, cA);
                    }
                    LInformation.setText("Second character configured in the pair (" + plugConfigurationDouble.get(cB) + "," + cB + ")");
                } else if ((cA != null) && cA.equals(cB) || (cB != null) && cB.equals(cA)) {
                    LInformation.setText("Cannot set a wire to its same position");
                } else {
                    plugConfigurationDouble.put(cA, cB);
                    plugConfigurationDouble.put(cB, cA);
                    LInformation.setText("Value added successfully");
                    lettersDouble.add(cA);
                    lettersDouble.add(cB);
                    lettersDouble.add(cB);
                    lettersDouble.add(cA);
                    TAConfiguredLetters.setText("Configured characters (double wire):\n" + toString(lettersDouble));
                }
            }
        }else {
            String cA = CBFirstCharacter.getValue();
            String cB = CBSecondCharacter.getValue();
            if (plugConfigurationSimple != null) {
                if (plugConfigurationSimple.containsKey(cA)) {
                    if (TBForce.isSelected()) {
                        forceValueSimple(cA, cB);
                    } else {
                        LInformation.setText("First character configured in the pair (" + plugConfigurationSimple.get(cA) + "," + cA + ")");
                    }
                } else if (plugConfigurationSimple.containsKey(cB)) {
                    if (TBForce.isSelected()) {
                        forceValueSimple(cB, cA);
                    } else {
                        LInformation.setText("Second character configured in the pair (" + plugConfigurationSimple.get(cB) + "," + cB + ")");
                    }
                } else if ((cA != null) && (cA.equals(cB)) || (cB != null) && (cB.equals(cA))) {
                    LInformation.setText("Cannot set a wire in its same position");
                } else {
                    plugConfigurationSimple.put(cA, cB);
                    lettersSimple.add(cA);
                    lettersSimple.add(cB);
                    LInformation.setText("Value added successfully");
                    TAConfiguredLetters.setText("Configured characters (simple wire):\n" + toString(lettersSimple));
                }
            }
        }
    }

    @FXML
    private void handleRBDoubleWire () {
        if (RBDoubleWire.isSelected()) {
            TAConfiguredLetters.setText("Configured characters (double wire):\n" + toString(lettersDouble));
        }else{
            TAConfiguredLetters.setText("Configured characters (simple wire):\n" + toString(lettersSimple));
        }
    }

    @FXML
    private void handleCBFirstCharacter() {
        String cAA = CBFirstCharacter.getValue();
        if (cAA != null) {
            if (RBDoubleWire.isSelected()) {
                if (plugConfigurationDouble != null) {
                    if (plugConfigurationDouble.containsKey(cAA)) {
                        CBSecondCharacter.setValue(plugConfigurationDouble.get(cAA));
                        LInformation.setText("Value already defined");
                    }
                } else {
                    CBSecondCharacter.setValue(cAA);
                }
            } else {
                if (plugConfigurationSimple != null) {
                    if (plugConfigurationSimple.containsValue(cAA)) {
                        CBSecondCharacter.setValue(plugConfigurationSimple.get(cAA));
                        LInformation.setText("Value already defined");
                    }
                } else {
                    CBSecondCharacter.setValue(cAA);
                }
            }
        }
    }

    @FXML
    private void handleBClear () {
        if (BClear.isArmed()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.getButtonTypes().add(ButtonType.CANCEL);
            alert.setTitle("Clear configuration");
            alert.setHeaderText("You are about to clear the configuration of the plugboard");
            alert.setContentText("Press OK to clear, Cancel to go back");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get().equals(ButtonType.OK)) {
                plugConfigurationDouble.clear();
                plugConfigurationSimple.clear();
                lettersDouble.clear();
                lettersSimple.clear();
                if (RBDoubleWire.isSelected()) {
                    TAConfiguredLetters.setText("Configured characters (double wire): ");
                }else{
                    TAConfiguredLetters.setText("Configured characters (single wire): ");
                }
            }
        }
    }

    private String toString (ArrayList<String> myList) {
        StringBuilder sb = new StringBuilder();
        sb.setLength(0);
        int value = 0;
        if (myList != null) {
            for (String s : myList) {
                sb.append(s);
                value += 1;
                if (value % 2 == 0) {
                    sb.append(" / ");
                } else {
                    sb.append("-");
                }
            }
        }
        return sb.toString();
    }

    private void forceValue (String prevVal, String constVar, String newVar) {
        if (plugConfigurationDouble.containsKey(newVar)) {
            remove (newVar, plugConfigurationDouble.get(newVar));
        }
        plugConfigurationDouble.remove(prevVal, constVar);
        plugConfigurationDouble.remove(constVar, prevVal);
        plugConfigurationDouble.put(constVar, newVar);
        plugConfigurationDouble.put(newVar, constVar);
        lettersDouble.remove(prevVal);
        lettersDouble.remove(constVar);
        lettersDouble.remove(constVar);
        lettersDouble.remove(prevVal);
        lettersDouble.add(constVar);
        lettersDouble.add(newVar);
        lettersDouble.add(newVar);
        lettersDouble.add(constVar);
        TAConfiguredLetters.setText("Configured characters (double wire):\n" + toString(lettersDouble));
        LInformation.setText("Value changed successfully");
        TBForce.setSelected(false);
    }

    private void remove (String val1, String val2) {
        plugConfigurationDouble.remove(val1, val2);
        plugConfigurationDouble.remove(val2, val1);
        lettersDouble.remove(val1);
        lettersDouble.remove(val2);
        lettersDouble.remove(val2);
        lettersDouble.remove(val1);
    }

    private void forceValueSimple (String a, String b) {
            if (plugConfigurationSimple.containsValue(b)) {
            System.out.println("Hello :)");
            int pos = lettersSimple.indexOf(b);
            lettersSimple.remove(b);
            lettersSimple.remove(pos-1);
            plugConfigurationSimple.remove(getKeyFromValue(plugConfigurationSimple, b), b);
        }
        plugConfigurationSimple.remove(a, plugConfigurationSimple.get(a));
        plugConfigurationSimple.put(a, b);
        int pos = lettersSimple.indexOf(a);
        lettersSimple.remove(pos+1);
        lettersSimple.remove(a);
        lettersSimple.add(a);
        lettersSimple.add(b);
        TAConfiguredLetters.setText("Configured characters (double wire): \n" + toString(lettersSimple));
        LInformation.setText("Value changed successfully");
        TBForce.setSelected(false);
    }

    private String getKeyFromValue (HashMap<String, String> map, String value) {
        for (Map.Entry<String, String> m : map.entrySet()) {
            if (m.getValue().equals(value)) {
                return m.getKey();
            }
        }
        return null;
    }
}
