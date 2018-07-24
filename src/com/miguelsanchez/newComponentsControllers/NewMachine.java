package com.miguelsanchez.newComponentsControllers;

import com.miguelsanchez.components.Alphabet;
import com.miguelsanchez.components.Machine;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class NewMachine {
    @FXML
    private RadioButton RBReflector, RBPlugboard;
    @FXML
    private Spinner SReflectors, SRotors, SOperatingRotors;
    @FXML
    private Label LQuantityReflectors;
    @FXML
    private TextField TFName;
    @FXML
    private TextArea TADescription;
    @FXML
    private ComboBox CBAlphabets;
    @FXML
    private RadioButton RBNewAlphabet;

    public void initialize () {
        CBAlphabets.setItems(Alphabet.getAlphabetsOL());
    }

    public com.miguelsanchez.components.Machine getResults() {
        String name = TFName.getText().trim();
        String description = TADescription.getText().trim();
        int totalRotors = (int) SRotors.getValue();
        int operatingRotors = (int) SOperatingRotors.getValue();
        int totalReflectors;
        boolean reflectorPresent = RBReflector.isSelected();
        if (RBReflector.isSelected()) {
            totalReflectors = (int) SReflectors.getValue();
        } else {
            totalReflectors = 0;
        }
        boolean plugboardPresent = RBPlugboard.isSelected();
        boolean newAlphabet = RBNewAlphabet.isSelected();
        String selection = (String) CBAlphabets.getValue();
        if (selection == null) {
            selection = "7Bf*RtArj+aKWz53g_Jp";
        }
        Alphabet alphabet = Alphabet.getAlphabet(selection);
        return new com.miguelsanchez.components.Machine(name, description, reflectorPresent, totalRotors, operatingRotors, totalReflectors, plugboardPresent, newAlphabet, alphabet);
    }

    @FXML
    public void handleRBReflector() {
        setReflectorsPosition(RBReflector.isSelected());
    }

    public void setMachine(com.miguelsanchez.components.Machine machine) {
        TFName.setText(machine.getName());
        TADescription.setText(machine.getDescription());
        RBReflector.setSelected(machine.isReflectorPresent());
        RBPlugboard.setSelected(machine.isBidirectionalEncryption());
        int value = machine.getTotalRotors() - (int) SRotors.getValue();
        SRotors.increment(value);
        value = machine.getTotalReflectors() - (int) SReflectors.getValue();
        SReflectors.increment(value);
        value = machine.getOperatingRotors() - (int) SOperatingRotors.getValue();
        SOperatingRotors.increment(value);
        setReflectorsPosition(RBReflector.isSelected());
    }

    private void setReflectorsPosition (boolean t) {
        if (t) {
            SReflectors.setDisable(false);
            LQuantityReflectors.setDisable(false);
        }else{
            SReflectors.setDisable(true);
            LQuantityReflectors.setDisable(true);
        }
    }

    @FXML
    private void handleRBNewAlphabet () {
        CBAlphabets.setDisable(!RBNewAlphabet.isSelected());
    }
}