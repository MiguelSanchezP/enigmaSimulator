package com.miguelsanchez.components;

import com.miguelsanchez.Controller;
import java.util.ArrayList;
import java.util.HashMap;

public class PlugBoard {
    private ArrayList<String> lettersSimple;
    private ArrayList<String> lettersDouble;
    private HashMap<String, String> configuredLettersSimple;
    private HashMap<String, String> configuredLettersDouble;
    private boolean isConfigureLater;
    private boolean isDouble;
    private String[] usedAlphabetComponents;

    public PlugBoard () {
        lettersSimple.clear();
        lettersDouble.clear();
        configuredLettersSimple.clear();
        configuredLettersDouble.clear();
        isConfigureLater = false;
        isDouble = false;
        usedAlphabetComponents = Controller.getAlphabetComponents();
    }
    public PlugBoard (ArrayList<String> lettersSimple, ArrayList<String> lettersDouble, HashMap<String, String> configuredLettersSimple,
                      HashMap<String, String> configuredLettersDouble, boolean isConfigureLater, boolean isDouble, String[] usedAlphabetComponents){
        this.lettersSimple = lettersSimple;
        this.lettersDouble = lettersDouble;
        this.configuredLettersSimple = configuredLettersSimple;
        this.configuredLettersDouble = configuredLettersDouble;
        this.isConfigureLater = isConfigureLater;
        this.isDouble = isDouble;
        this.usedAlphabetComponents = usedAlphabetComponents;
    }

    public boolean isConfigureLater () {
        return isConfigureLater;
    }
    public ArrayList<String> getLettersSimple () {
        return lettersSimple;
    }
    public ArrayList<String> getLettersDouble () {
        return lettersDouble;
    }
    public HashMap<String, String> getConfiguredLettersSimple() {
        return configuredLettersSimple;
    }
    public HashMap<String, String> getConfiguredLettersDouble () {
        return configuredLettersDouble;
    }
    public String[] getUsedAlphabetComponents () {
        return usedAlphabetComponents;
    }
    public boolean isDouble () {
        return isDouble;
    }
}
