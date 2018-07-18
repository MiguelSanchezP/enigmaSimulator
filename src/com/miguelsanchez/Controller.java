package com.miguelsanchez;

import com.miguelsanchez.components.Machine;
import com.miguelsanchez.components.Rotor;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Optional;

public class Controller {

    @FXML
    private BorderPane mainBorderPane;

    public void createANewMachine() {
        boolean isCancel = true;
        Machine tempMachine = new Machine();
        while (isCancel) {
            Dialog<ButtonType> newDialog = new Dialog<>();
            newDialog.initOwner(mainBorderPane.getScene().getWindow());
            newDialog.setTitle("Create a new machine");
            newDialog.setHeaderText("Use this dialog to create a new Enigma machine");
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("newMachine.fxml"));
            try {
                newDialog.getDialogPane().setContent(fxmlLoader.load());
                NewMachineController controller = fxmlLoader.getController();
                controller.setMachine(tempMachine);
            } catch (IOException e) {
                System.out.println("Couldn't load the dialog");
                e.printStackTrace();
                return;
            }
            newDialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
            newDialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
            Optional<ButtonType> result = newDialog.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                NewMachineController controller = fxmlLoader.getController();
                tempMachine = controller.getResults();
                isCancel = false;
                if (tempMachine.getName().isEmpty() || tempMachine.getDescription().isEmpty() || (tempMachine.getRotationConfiguration().isEmpty() && !tempMachine.isConfigureLater())) {
                    Optional<ButtonType> result2 = emptyFieldsAlert();
                    if (result2.isPresent() && result2.get() == ButtonType.CANCEL) {
                        isCancel = true;
                    }
                }
                if (tempMachine.getTotalRotors() < tempMachine.getOperatingRotors()) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Impossible situation");
                    alert.setHeaderText("Error in the quantity of rotors selection");
                    alert.setContentText("There are more rotors operating than the total\nGo back and change the values?");
                    alert.showAndWait();
                    isCancel = true;
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Close the dialog");
                alert.setHeaderText("You are about to close the dialog");
                alert.setContentText("Are you sure you want to close it?\nIf you press ok, the creation will be deleted");
                Optional<ButtonType> result2 = alert.showAndWait();
                if (result2.isPresent() && result2.get() == ButtonType.OK) {
                    return;
                }
            }
        }
        LinkedList<Rotor> tempRotors = new LinkedList<>();
        int currentRotor = 0;

        while (tempRotors.size() != tempMachine.getTotalRotors()) {
            Dialog<ButtonType> newRotorDialog = new Dialog<>();
            newRotorDialog.setTitle("Creation of a rotor");
            newRotorDialog.setHeaderText("Use this dialog to create/edit the rotor " + (currentRotor + 1) + " out of " + tempMachine.getTotalRotors());
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("NewRotor.fxml"));
            try {
                newRotorDialog.getDialogPane().setContent(fxmlLoader.load());
                if (currentRotor != tempRotors.size()) {
                    NewRotorController controller = fxmlLoader.getController();
                    controller.loadRotor(tempRotors, currentRotor);
                }
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
            newRotorDialog.getDialogPane().getButtonTypes().add(ButtonType.NEXT);
            newRotorDialog.getDialogPane().getButtonTypes().add(ButtonType.PREVIOUS);
            newRotorDialog.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);
            Optional<ButtonType> result1 = newRotorDialog.showAndWait();
            if (result1.isPresent() && result1.get() == ButtonType.NEXT) {
                currentRotor += 1;
                NewRotorController controller = fxmlLoader.getController();
                Rotor temporalRotor = controller.newRotor();
                boolean cancelPressed = false;
                if (temporalRotor.getWiring().isEmpty() && !temporalRotor.isConfigureLater()) {
                    Optional<ButtonType> result2 = emptyFieldsAlert();
                    if (result2.isPresent() && result2.get() == ButtonType.OK) {
                        temporalRotor.setConfigureLater(true);
                    } else {
                        cancelPressed = true;
                    }
                }
                if (tempRotors.size() == (currentRotor - 1)) {
                    tempRotors.add((currentRotor - 1), temporalRotor);
                } else {
                    tempRotors.set((currentRotor - 1), temporalRotor);
                }
                if (cancelPressed) {
                    if (currentRotor > 0) {
                        currentRotor -= 1;
                    }
                }
            } else if (result1.isPresent() && result1.get() == ButtonType.PREVIOUS) {
                if (currentRotor > 0) {
                    currentRotor -= 1;
                }
                if (tempRotors.size() == (currentRotor + 1)) {
                    NewRotorController controller = fxmlLoader.getController();
                    tempRotors.add(controller.newRotor());
                }
            } else {
                if (tempRotors.size() == (currentRotor - 1)) {
                    NewRotorController controller = fxmlLoader.getController();
                    tempRotors.add(controller.newRotor());
                }
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Close the configuration");
                alert.setHeaderText("You are about to close the configuration of the rotors");
                alert.setContentText("Are you sure you want to close the window\nThe non completed rotors must be configured later");
                Optional<ButtonType> result2 = alert.showAndWait();
                if (result2.isPresent() && result2.get() == ButtonType.OK) {
                    int rotorsConfigured = tempMachine.getTotalRotors() - tempRotors.size();
                    for (int i = 0; i < rotorsConfigured; i++) {
                        Rotor rotor = new Rotor();
                        rotor.setConfigureLater(true);
                        tempRotors.add(rotor);
                    }
                }
            }
        }
        //continue with the other menus in the following order:
        //1.-Plugboard
        //2.-Rotation Configuration
        //3.-Rotors (create the alert for non active rotors creating a variable for the temporal list)
        //4.-Reflectors
        //finish the machine

    }

    private Optional<ButtonType> emptyFieldsAlert () {
        Alert alert = new Alert (Alert.AlertType.WARNING);
        alert.setTitle("Empty Fields");
        alert.setHeaderText("At least one of the fields was empty");
        alert.setContentText("Are you sure you want to continue?\nThe empty fields will be completed automatically");
        alert.getButtonTypes().add(ButtonType.CANCEL);
        return alert.showAndWait();
    }
}