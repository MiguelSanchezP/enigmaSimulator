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
    private TextField TFSeparation, TFName;
    @FXML
    private ComboBox<String> CBExistingAlphabet;
    @FXML
    private TextArea TAAlphabet;

    private String text;

    public void initialize () {
        alphabetsFill (CBExistingAlphabet);
    }

    public void setAlphabet (Alphabet a) {
        RBSeparation.setSelected(a.isDefaultSeparation());
        TFSeparation.setText(a.getRegex());
        RBExistingAlphabet.setSelected(a.isExistingAlphabet());
        TFName.setText(a.getName());
        TAAlphabet.setText(a.getTempComponents());
        if (Alphabet.getAlphabetsOL().contains(a.getName())) {
            CBExistingAlphabet.setValue(a.getName());
        }
        handleRBSeparation();
        handleRBExistingAlphabet();
    }

    @FXML
    private void handleRBSeparation () {
        TFSeparation.setDisable(RBSeparation.isSelected());
    }
    @FXML
    private void handleRBExistingAlphabet () {
        CBExistingAlphabet.setDisable(!RBExistingAlphabet.isSelected());
        if (!RBExistingAlphabet.isSelected()) {
            TAAlphabet.setText(text);
        }
    }
    @FXML
    private void handleCBExistingAlphabet () {
        text=TAAlphabet.getText();
        Alphabet tempAlphabet = getAlphabet(CBExistingAlphabet.getValue());
        TAAlphabet.setText(tempAlphabet.getTempComponents());
        RBSeparation.setSelected(tempAlphabet.getRegex().equals("-"));
        TFSeparation.setText(tempAlphabet.getRegex());
        handleRBSeparation();
    }

    public Alphabet getResults () {
        String regex;
        boolean defaultSeparation = RBSeparation.isSelected();
        boolean existingAlphabet = RBExistingAlphabet.isSelected();
        if (RBSeparation.isSelected()) {
            regex = "-";
        }else{
            regex = TFSeparation.getText();
        }
        String name = TFName.getText().trim();
        String components = TAAlphabet.getText();
        return new Alphabet(name, components, regex, defaultSeparation, existingAlphabet);
    }
}
