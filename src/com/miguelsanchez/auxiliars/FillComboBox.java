package com.miguelsanchez.auxiliars;

import com.miguelsanchez.components.Alphabet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;

import java.util.Collections;

public class FillComboBox {
    public static void alphabetsFill (ComboBox<String> cb) {
        ObservableList<String> list = Alphabet.getAlphabetsOLNames();
        Collections.sort(list);
        cb.setItems(list);
    }

    public static void alphabetsContentFill (ComboBox<String> cb, String[] s) {
        ObservableList<String> components = FXCollections.observableArrayList();
        Collections.sort(components);
        components.addAll(s);
        cb.setItems(components);
    }
}
