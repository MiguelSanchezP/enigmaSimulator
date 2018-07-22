package com.miguelsanchez;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;

import static com.miguelsanchez.auxiliars.AllLettersPane.*;
import static com.miguelsanchez.auxiliars.Alphabet.getAlphabet;

public class NewPlugboardController {

    @FXML
    private GridPane PlugConfigurationPane;
    @FXML
    private RadioButton RBConfigureLater, RBDoubleWire;

    private boolean doubleWire;

    void configureTheGridPane () {
        PlugConfigurationPane.add(getAllLettersPane(), 0, 1);
        PlugConfigurationPane.getChildren().get(2).setOnKeyReleased(checkForTheLetter);
    }

    @FXML
    private void handleRBConfigureLater () {
        if (RBConfigureLater.isSelected()) {
            PlugConfigurationPane.getChildren().get(0).setDisable(true);
            PlugConfigurationPane.getChildren().get(2).setDisable(true);
        }else{
            PlugConfigurationPane.getChildren().get(0).setDisable(false);
            PlugConfigurationPane.getChildren().get(2).setDisable(false);
        }
    }

    @FXML
    private void handleRBDoubleWire () {
        doubleWire = RBDoubleWire.isSelected();
    }

    private EventHandler<KeyEvent> checkForTheLetter = new EventHandler<KeyEvent>() {
        @Override
        public void handle(KeyEvent event) {
            String input = event.getText();
            System.out.println("The input is " + input);
            for (int i = 0; i<getAlphabet("Standard").getName().length(); i++) {
                System.out.println("I HAVE STARTED!");
                if (getAllTfs().get(i).getText().equals(input)) {
                    System.out.println("IM EXECUTING!!!");
                    String[] alphabetComponents = getAlphabet("Standard").getComponents();
                    String plainLetter = alphabetComponents[i];
                    int number = toNumber(input);
                    getAllTfs().get(number).setText(plainLetter);
                    updateLettersPane();
                    update();
                }
            }
        }
    };

    private void update () {
        PlugConfigurationPane.getChildren().remove(2);
        PlugConfigurationPane.add(getAllLettersPane(), 0, 1);
    }
}
