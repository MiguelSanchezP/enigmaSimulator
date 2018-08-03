package com.miguelsanchez.auxiliars;

import com.miguelsanchez.components.Alphabet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;

public class FillComboBox {
    public static void alphabetsFill (ComboBox<String> cb) {
        cb.setItems(Alphabet.getAlphabetsOL());
    }

    public static void alphabetsContentFill (ComboBox<String> cb, String[] s) {
        ObservableList<String> components = FXCollections.observableArrayList();
        components.addAll(s);
        cb.setItems(components);
    }
}
