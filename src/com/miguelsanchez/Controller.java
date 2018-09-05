package com.miguelsanchez;

import com.miguelsanchez.components.Alphabet;
import com.miguelsanchez.components.Machine;
import com.miguelsanchez.newComponentsControllers.NewAlphabet;
import com.miguelsanchez.newComponentsControllers.NewMachine;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

public class Controller {

    @FXML
    private BorderPane mainBorderPane;

    private static String [] alphabetComponents = Alphabet.getAlphabet("Standard").getComponents();

    public void createANewMachine() {
        boolean newMachineCancel = true;
        Machine tempMachine = new Machine();
        while (newMachineCancel) {
            Dialog<ButtonType> machineDialog = new Dialog<>();
            machineDialog.initOwner(mainBorderPane.getScene().getWindow());
            machineDialog.setTitle("EMS: Create a new machine");
            machineDialog.setHeaderText("Use this dialog to create a new Enigma Machine");
            FXMLLoader machineFxmlLoader = new FXMLLoader();
            machineFxmlLoader.setLocation(getClass().getResource("./newComponentsDialogs/NewMachine.fxml"));
            try {
                machineDialog.getDialogPane().setContent(machineFxmlLoader.load());
                NewMachine machine = machineFxmlLoader.getController();
                machine.setMachine(tempMachine);
            } catch (IOException e) {
                System.out.println("Couldn't load the dialog");
                e.printStackTrace();
                return;
            }
            machineDialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
            machineDialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
            Optional<ButtonType> result = machineDialog.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                NewMachine machine = machineFxmlLoader.getController();
                tempMachine = machine.getResults();
                newMachineCancel = false;
                if (tempMachine.getName().isEmpty() || tempMachine.getDescription().isEmpty()) {
                    Optional<ButtonType> result2 = emptyFieldsAlert();
                    if (result2.isPresent() && result2.get() == ButtonType.CANCEL) {
                        newMachineCancel = true;
                    }
                    if (result2.isPresent() && result2.get() == ButtonType.OK) {
                        if (tempMachine.getName().isEmpty()) {
                            tempMachine.setName("Unnamed machine");
                        }
                        if (tempMachine.getDescription().isEmpty()) {
                            tempMachine.setDescription("Empty description");
                        }
                    }
                }
                if (tempMachine.getTotalRotors() < tempMachine.getOperatingRotors()) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Impossible situation");
                    alert.setHeaderText("Error in the quantity of rotors selection");
                    alert.setContentText("There are more rotors operating than the total\nGo back and change the values?");
                    alert.showAndWait();
                    newMachineCancel = true;
                }
                if (!tempMachine.isNewAlphabet() && tempMachine.getAlphabet().getName().equals("NoAlphabet")) {
                    Optional<ButtonType> result2 = alphabetSelectionAlert();
                    if (result2.isPresent() && result2.get() == ButtonType.OK) {
                        tempMachine.setAlphabet(Alphabet.getAlphabet("Standard"));
                    } else {
                        newMachineCancel = true;
                    }
                }
            } else {
                if (cancelConfirmation()) {
                    return;
                }
            }
        }
            //continue with the other menus in the following order:
            //on the new machine add the temporal names?
            //0.- Alphabets done
            //1.-Plugboard process
            //2.-Rotation Configuration
            //3.-Rotors (create the alert for non active rotors creating a variable for the temporal list)
            //4.-Reflectors
            //finish the machine

            if (tempMachine.isNewAlphabet()) {
                Alphabet tempAlphabet = new Alphabet();
                boolean newAlphabetCancel = true;
                while (newAlphabetCancel) {
                    Dialog<ButtonType> alphabetDialog = new Dialog<>();
                    alphabetDialog.setTitle("EMS: Create a new Alphabet");
                    alphabetDialog.setHeaderText("Use this dialog to create a new Alphabet");
                    FXMLLoader alphabetFxmlLoader = new FXMLLoader();
                    alphabetFxmlLoader.setLocation(getClass().getResource("./newComponentsDialogs/NewAlphabet.fxml"));
                    try {
                        alphabetDialog.getDialogPane().setContent(alphabetFxmlLoader.load());
                        NewAlphabet alphabet = alphabetFxmlLoader.getController();
                        alphabet.setAlphabet(tempAlphabet);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    alphabetDialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
                    alphabetDialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
                    Optional<ButtonType> result = alphabetDialog.showAndWait();
                    if (result.isPresent() && result.get() == ButtonType.OK) {
                        newAlphabetCancel = false;
                        NewAlphabet alphabet = alphabetFxmlLoader.getController();
                        tempAlphabet = alphabet.getResults();
                        if ((tempAlphabet.getName().isEmpty() || tempAlphabet.getTempComponents().isEmpty()) || (tempAlphabet.isExistingAlphabet() && tempAlphabet.getExistingAlphabetName().equals("Select"))) {
                            newAlphabetCancel = true;
                            if (tempAlphabet.getName().isEmpty() || tempAlphabet.getTempComponents().isEmpty()) {
                                Alert alert = new Alert(Alert.AlertType.ERROR);
                                alert.setTitle("Empty Fields");
                                alert.setHeaderText("There were some empty fields");
                                alert.setContentText("Fill them in order to continue");
                                alert.showAndWait();
                            } else {
                                Alert alert = new Alert(Alert.AlertType.ERROR);
                                alert.setTitle("Select an alphabet");
                                alert.setHeaderText("There was no alphabet selected");
                                alert.setContentText("Are you sure you want to continue?\nPlease select one in order to continue");
                                alert.showAndWait();
                            }
                        } else if (Alphabet.getAlphabetsOLNames().contains(tempAlphabet.getName())) {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Name Already Used");
                            alert.setHeaderText("There is an alphabet with the same name");
                            alert.setContentText("Press OK and change the name in order to continue");
                            alert.showAndWait();
                            newAlphabetCancel=true;
                        }else if (alphabetRepeated(tempAlphabet)) {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.getButtonTypes().add(ButtonType.CANCEL);
                            alert.setTitle("Configuration Already Defined");
                            alert.setHeaderText("There is an alphabet that has the same configuration");
                            alert.setContentText("Do you want to use this alphabet? (Press OK to proceed\nor cancel to go back and make modifications)");
                            Optional<ButtonType> result1 = alert.showAndWait();
                            if (result1.isPresent() && result1.get().equals(ButtonType.OK)) {
                                tempMachine.setAlphabet(getRepeatedAlphabet(tempAlphabet));
                            } else {
                                newAlphabetCancel = true;
                            }
                        }else if (repeatedInside(tempAlphabet)) {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Two Values Repeated");
                            alert.setHeaderText("There are at least two values repeated");
                            alert.setContentText("Press OK and change/delete one of those values in order to continue");
                            alert.showAndWait();
                            newAlphabetCancel=true;
                        }else{
                            Alphabet.addAlphabet(tempAlphabet);
                        }
                    } else {
                        if (cancelConfirmation()) {
                            return;
                        }
                    }
                }
                alphabetComponents = tempAlphabet.getComponents();
                tempMachine.setAlphabet(tempAlphabet);
            }else{
                alphabetComponents = tempMachine.getAlphabet().getComponents();
            }

        boolean plugCancel = true;
        while (plugCancel) {
            Dialog<ButtonType> plugboardDialog = new Dialog<>();
            plugboardDialog.setTitle ("EMS: Create a new Plugboard");
            plugboardDialog.setHeaderText("Use this dialog to create a new Plugboard");
            FXMLLoader plugboardFxmlLoader = new FXMLLoader();
            plugboardFxmlLoader.setLocation(getClass().getResource("./newComponentsDialogs/NewPlugboard.fxml"));
            try {
                plugboardDialog.getDialogPane().setContent(plugboardFxmlLoader.load());
            }catch (IOException e) {
                e.printStackTrace();
            }
            plugboardDialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
            Optional<ButtonType> result = plugboardDialog.showAndWait();
        }
/*
        //STARTING OF THE ROTORS
        LinkedList<Rotor> tempRotors = new LinkedList<>();
        int currentRotor = 0;
Ç
        while (tempRotors.size() != tempMachine.getTotalRotors()) {
            Dialog<ButtonType> newRotorDialog = new Dialog<>();
            newRotorDialog.setTitle("Creation of a rotor");
            newRotorDialog.setHeaderText("Use this dialog to create/edit the rotor " + (currentRotor + 1) + " out of " + tempMachine.getTotalRotors());
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("./newComponentsDialogs/NewRotor.fxml"));
            try {
                newRotorDialog.getDialogPane().setContent(fxmlLoader.load());
                if (currentRotor != tempRotors.size()) {
                    NewRotor controller = fxmlLoader.getController();
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
                NewRotor controller = fxmlLoader.getController();
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
                    Alphabet.addAlphabet(tempAlphabet);
                    newAlphabetCancel = false;
                if (tempRotors.size() == (currentRotor + 1)) {
                    NewRotor controller = fxmlLoader.getController();
                    tempRotors.add(controller.newRotor());
                }
            } else {
                if (tempRotors.size() == (currentRotor - 1)) {
                    NewRotor controller = fxmlLoader.getController();
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
*/
    }

    private Optional<ButtonType> emptyFieldsAlert () {
        Alert alert = new Alert (Alert.AlertType.WARNING);
        alert.setTitle("Empty Fields");
        alert.setHeaderText("At least one of the fields was empty");
        alert.setContentText("Are you sure you want to continue?\nThe empty fields will be completed automatically");
        alert.getButtonTypes().add(ButtonType.CANCEL);
        return alert.showAndWait();
    }

    private Optional<ButtonType> alphabetSelectionAlert () {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.getButtonTypes().add(ButtonType.CANCEL);
        alert.setTitle ("Select an alphabet");
        alert.setHeaderText ("There was no alphabet selected");
        alert.setContentText ("Are you sure you want to continue?\nIf you press ok, the standard alphabet will be\nautomatically selected");
        return alert.showAndWait();
    }

    private boolean cancelConfirmation () {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Close the dialog");
        alert.setHeaderText("You are about to close the dialog");
        alert.setContentText("Are you sure you want to close it?\nIf you press ok, the creation will be deleted");
        Optional<ButtonType> result = alert.showAndWait();
        return (result.isPresent() && result.get() == ButtonType.OK);
    }

    public static String[] getAlphabetComponents () {
        return alphabetComponents;
    }

    private static boolean alphabetRepeated (Alphabet a) {
        ArrayList<String> components1 = new ArrayList<>();
        Collections.addAll(components1, a.getComponents());
        Collections.sort(components1);
        for (Alphabet a1 : Alphabet.getAlphabetsOL()) {
            ArrayList<String> componentsDef = new ArrayList<>();
            Collections.addAll(componentsDef, a1.getComponents());
            Collections.sort(componentsDef);
            if (components1.equals(componentsDef)) {
                return true;
            }
        }
        return false;
    }
    private static Alphabet getRepeatedAlphabet (Alphabet a) {
        ArrayList<String> components1 = new ArrayList<>();
        Collections.addAll(components1, a.getComponents());
        Collections.sort(components1);
        for (Alphabet a1 : Alphabet.getAlphabetsOL()) {
            ArrayList<String> componentsDef = new ArrayList<>();
            Collections.addAll(componentsDef, a1.getComponents());
            Collections.sort(componentsDef);
            if (components1.equals(componentsDef)) {
                return a1;
            }
        }
        return null;
    }

    private static boolean repeatedInside (Alphabet a) {
        ArrayList<String> allvalues = new ArrayList<>();
        for (String s : a.getComponents()) {
            if (allvalues.contains(s)) {
                return true;
            }
            allvalues.add(s);
        }
        return false;
    }
}