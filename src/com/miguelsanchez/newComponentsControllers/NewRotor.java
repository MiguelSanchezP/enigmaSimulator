package com.miguelsanchez.newComponentsControllers;

import com.miguelsanchez.components.Rotor;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.LinkedList;

public class NewRotor {
    @FXML
    private TextField TFName;
    @FXML
    private TextArea TAWiring;
    @FXML
    private RadioButton RBSpecialName, RBNumbers, RBConfigurationAsLetters, RBActiveRotor, RBConfigureLater;
    @FXML
    private Button BRandomConfiguration;
    @FXML
    private Label LWiring;

    public Rotor newRotor() {
        return new Rotor(TFName.getText(), TAWiring.getText(), RBSpecialName.isSelected(), RBNumbers.isSelected(),
                RBActiveRotor.isSelected(), RBConfigureLater.isSelected(), RBConfigurationAsLetters.isSelected());
    }

    public void loadRotor (LinkedList<Rotor> list, int pos) {
        TFName.setText(list.get(pos).getName());
        TAWiring.setText(list.get(pos).getWiring());
        RBSpecialName.setSelected(list.get(pos).isSpecialName());
        RBNumbers.setSelected(list.get(pos).isNumbers());
        RBActiveRotor.setSelected(list.get(pos).isActiveRotor());
        RBConfigurationAsLetters.setSelected(list.get(pos).isConfigurationAsLetters());
        RBConfigureLater.setSelected(list.get(pos).isConfigureLater());
        if (RBConfigureLater.isSelected()) {
            TFName.setDisable(true);
            TAWiring.setDisable(true);
            RBSpecialName.setDisable(true);
            RBNumbers.setDisable(true);
            RBConfigurationAsLetters.setDisable(true);
            RBActiveRotor.setDisable(true);
            RBConfigureLater.setDisable(true);
        }else{
            if(RBSpecialName.isSelected()) {
                TFName.setDisable(false);
            }
            TAWiring.setDisable(false);
            RBSpecialName.setDisable(false);
            RBNumbers.setDisable(false);
            RBConfigurationAsLetters.setDisable(false);
            RBActiveRotor.setDisable(false);
            RBConfigureLater.setSelected(false);
        }
    }

    public void handleRBSpecialName () {
        if (RBSpecialName.isSelected()) {
            TFName.setDisable(false);
        }else{
            TFName.setDisable(true);
        }
    }

    public void handleRBConfigureLater () {
        if (RBConfigureLater.isSelected()) {
            TFName.setDisable(true);
            TAWiring.setDisable(true);
            RBSpecialName.setDisable(true);
            RBNumbers.setDisable(true);
            RBConfigurationAsLetters.setDisable(true);
            RBActiveRotor.setDisable(true);
            BRandomConfiguration.setDisable(true);
            LWiring.setDisable(true);
        }else{
            if(RBSpecialName.isSelected()) {
                TFName.setDisable(false);
            }
            TAWiring.setDisable(false);
            RBSpecialName.setDisable(false);
            RBNumbers.setDisable(false);
            RBConfigurationAsLetters.setDisable(false);
            RBActiveRotor.setDisable(false);
            BRandomConfiguration.setDisable(false);
            LWiring.setDisable(false);
        }
    }
}
