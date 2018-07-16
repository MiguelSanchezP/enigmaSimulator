package com.miguelsanchez;

import com.miguelsanchez.components.Machine;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class NewMachineController {
    @FXML
    private RadioButton RBReflector, RBPlugboard, RBConfigureLater;
    @FXML
    private Spinner SReflectors, SRotors, SOperatingRotors;
    @FXML
    private Label LQuantityReflectors, LRotationRotors;
    @FXML
    private TextField TFName;
    @FXML
    private TextArea TADescription;
    @FXML
    private Button BConfigureRotation;

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
        String rotationConfiguration = handleBConfigurationRotation();
        boolean configureLater = RBConfigureLater.isSelected();
        boolean plugboardPresent = RBPlugboard.isSelected();
        return new Machine(name, description, reflectorPresent, rotationConfiguration, totalRotors, operatingRotors, totalReflectors, plugboardPresent, configureLater);
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

    @FXML
    public void handleRBConfigureLater() {
        if (RBConfigureLater.isSelected()) {
            LRotationRotors.setDisable(true);
        } else {
            LRotationRotors.setDisable(false);
        }
    }

    void setMachine(Machine machine) {
        TFName.setText(machine.getName());
        TADescription.setText(machine.getDescription());
        RBReflector.setSelected(machine.isReflectorPresent());
        RBConfigureLater.setSelected(machine.isConfigureLater());
        RBPlugboard.setSelected(machine.isBidirectionalEncryption());
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
        if (machine.isConfigureLater()) {
            LRotationRotors.setDisable(true);
        } else {
            LRotationRotors.setDisable(false);
        }
    }

    public String handleBConfigurationRotation () {

    return null;
    }
}