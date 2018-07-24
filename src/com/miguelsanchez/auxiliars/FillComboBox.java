package com.miguelsanchez.auxiliars;

import com.miguelsanchez.components.Alphabet;
import javafx.scene.control.ComboBox;

public class FillComboBox {
    public static void alphabetsFill (ComboBox<String> cb) {
        cb.setItems(Alphabet.getAlphabetsOL());
    }
}
