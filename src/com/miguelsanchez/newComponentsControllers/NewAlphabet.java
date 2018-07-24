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
        TAAlphabet.setText(Alphabet.toString(tempAlphabet.getComponents(), tempAlphabet.getRegex()));
        RBSeparation.setSelected(tempAlphabet.getRegex().equals("-"));
        TFSeparation.setText(tempAlphabet.getRegex());
        handleRBSeparation();
//        }
    }

    public Alphabet getResults () {
        String regex;
        if (RBSeparation.isSelected()) {
            regex = "-";
        }else{
            regex = TFSeparation.getText();
        }
        String name = TFName.getText();
        String components = TAAlphabet.getText();
        return new Alphabet(name, components, regex);
    }
}
