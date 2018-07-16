package com.miguelsanchez;

import com.miguelsanchez.components.Machine;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class NewMachineController {
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
    Machine getResults() {
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
        return new Machine(name, description, reflectorPresent, totalRotors, operatingRotors, totalReflectors, plugboardPresent);
    }

    @FXML
    public void handleRBReflector() {
        if (RBReflector.isSelected()) {
            SReflectors.setDisable(false);
            LQuantityReflectors.setDisable(false);
        } else {
            SReflectors.setDisable(true);
            LQuantityReflectors.setDisable(true);
        }
    }

    void setMachine(Machine machine) {
        TFName.setText(machine.getName());
        TADescription.setText(machine.getDescription());
        RBReflector.setSelected(machine.isReflectorPresent());
        RBPlugboard.setSelected(machine.isPlugboardPresent());
        int value = machine.getTotalRotors() - (int) SRotors.getValue();
        SRotors.increment(value);
        value = machine.getTotalReflectors() - (int) SReflectors.getValue();
        SReflectors.increment(value);
        value = machine.getOperatingRotors() - (int) SOperatingRotors.getValue();
        SOperatingRotors.increment(value);
        if (RBReflector.isSelected()) {
            SReflectors.setDisable(false);
            LQuantityReflectors.setDisable(false);
        } else {
            SReflectors.setDisable(true);
            LQuantityReflectors.setDisable(true);
        }
    }
}