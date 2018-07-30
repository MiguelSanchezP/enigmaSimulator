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

    public Alphabet getResults () {
        String regex;
        boolean defaultSeparation = RBSeparation.isSelected();
        boolean existingAlphabet = RBExistingAlphabet.isSelected();
        String existingAlphabetName = CBExistingAlphabet.getValue();
        if (RBSeparation.isSelected()) {
            regex = "-";
        }else{
            regex = TFSeparation.getText();
        }
        String name = TFName.getText().trim();
        String tempComponents = TAAlphabet.getText();
        return new Alphabet (name, tempComponents, regex, defaultSeparation, existingAlphabet, existingAlphabetName, text);
    }

    public void setAlphabet (Alphabet a) {
        RBSeparation.setSelected(a.isDefaultSeparation());
        TFSeparation.setText(a.getRegex());
        RBExistingAlphabet.setSelected(a.isExistingAlphabet());
        TFName.setText(a.getName());
        TAAlphabet.setText(a.getTempComponents());
        CBExistingAlphabet.setValue(a.getExistingAlphabetName());
        text = a.getText();
        handleRBSeparation();
        CBExistingAlphabet.setDisable(!RBExistingAlphabet.isSelected());
    }

    @FXML
    private void handleRBSeparation () {
        TFSeparation.setDisable(RBSeparation.isSelected());
    }
    @FXML
    private void handleRBExistingAlphabet () {
        CBExistingAlphabet.setDisable(!RBExistingAlphabet.isSelected());
        if (RBExistingAlphabet.isSelected()) {
            text = TAAlphabet.getText();
        }else{
            TAAlphabet.setText(text);
        }
    }
    @FXML
    private void handleCBExistingAlphabet () {
        Alphabet tempAlphabet = getAlphabet(CBExistingAlphabet.getValue());
        text = TAAlphabet.getText();
        TAAlphabet.setText(Alphabet.toString(tempAlphabet.getComponents(), tempAlphabet.getRegex()));
        RBSeparation.setSelected(tempAlphabet.getRegex().equals("-"));
        TFSeparation.setText(tempAlphabet.getRegex());
        handleRBSeparation();
    }
}
