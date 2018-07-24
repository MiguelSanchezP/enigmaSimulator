package com.miguelsanchez.newComponentsControllers;

import com.miguelsanchez.components.Alphabet;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import static com.miguelsanchez.auxiliars.FillComboBox.alphabetsFill;
import static com.miguelsanchez.components.Alphabet.getAlphabet;

public class NewAlphabet {
    @FXML
    private RadioButton RBSeparation, RBExistingAlphabet;
    @FXML
    private TextField TFSeparation;
    @FXML
    private ComboBox<String> CBExistingAlphabet;
    @FXML
    private TextArea TAAlphabet;

    private String text;

    public void initialize () {
        alphabetsFill (CBExistingAlphabet);
    }

    @FXML
    private void handleRBSeparation () {
        TFSeparation.setDisable(!RBSeparation.isSelected());
    }
    @FXML
    private void handleRBExistingAlphabet () {
        CBExistingAlphabet.setDisable(!RBExistingAlphabet.isSelected());
        if (RBExistingAlphabet.isSelected()) {
            text=TAAlphabet.getText();
            Alphabet tempAlphabet = getAlphabet(CBExistingAlphabet.getValue());
            TAAlphabet.setText(Alphabet.toString(tempAlphabet.getComponents()));
        }else{
            TAAlphabet.setText(text);
        }
    }
    @FXML
    private void handleCBExistingAlphabet () {
        text = TAAlphabet.getText();
        Alphabet tempAlphabet = getAlphabet(CBExistingAlphabet.getValue());
        TAAlphabet.setText(Alphabet.toString(tempAlphabet.getComponents()));
        RBSeparation.setSelected(false);
        handleRBSeparation();
    }
}
