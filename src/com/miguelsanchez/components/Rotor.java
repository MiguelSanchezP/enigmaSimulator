package com.miguelsanchez.components;

public class Rotor {
    private String name;
    private String wiring;
    private boolean isSpecialName;
    private boolean isNumbers;
    private boolean isActiveRotor;
    private boolean isConfigureLater;
    private boolean isConfigurationAsLetters;

    public Rotor (String name, String wiring, boolean isSpecialName, boolean isNumbers, boolean isActiveRotor, boolean isConfigureLater, boolean isConfigurationAsLetters) {
        this.name = name;
        this.wiring = wiring;
        this.isSpecialName = isSpecialName;
        this.isNumbers = isNumbers;
        this.isActiveRotor = isActiveRotor;
        this.isConfigureLater = isConfigureLater;
        this.isConfigurationAsLetters = isConfigurationAsLetters;
    }

    public Rotor () {
        this.name = "";
        this.wiring = "";
        this.isSpecialName = false;
        this.isNumbers = false;
        this.isActiveRotor = false;
        this.isConfigureLater = false;
        this.isConfigurationAsLetters = false;
    }

    public String getName () {
        return name;
    }
    public String getWiring () {
        return wiring;
    }
    public boolean isSpecialName () {
        return isSpecialName;
    }
    public boolean isNumbers () {
        return isNumbers;
    }
    public boolean isActiveRotor () {
        return isActiveRotor;
    }
    public boolean isConfigureLater () {
        return isConfigureLater;
    }
    public boolean isConfigurationAsLetters () {
        return isConfigurationAsLetters;
    }
    public void setConfigureLater (boolean isConfigureLater) {
        this.isConfigureLater = isConfigureLater;
    }
}
