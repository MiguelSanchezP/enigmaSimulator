package com.miguelsanchez.components;

public class Machine {
    private String name;
    private String description;
    private boolean reflectorPresent;
    private String rotationConfiguration;
    private int totalRotors;
    private int operatingRotors;
    private int totalReflectors;
    private boolean bidirectionalEncryption;
    private boolean configureLater;

    public Machine (String name, String description, boolean reflectorPresent, String rotationConfiguration, int totalRotors, int operatingRotors,
                    int totalReflectors, boolean bidirectionalEncryption, boolean configureLater) {
        this.name = name;
        this.description = description;
        this.reflectorPresent = reflectorPresent;
        this.rotationConfiguration = rotationConfiguration;
        this.totalRotors = totalRotors;
        this.operatingRotors = operatingRotors;
        this.totalReflectors = totalReflectors;
        this.bidirectionalEncryption = bidirectionalEncryption;
        this.configureLater = configureLater;
    }
     public Machine () {
        this.name = "";
        this.description = "";
        this.rotationConfiguration = "";
        this.reflectorPresent = true;
        this.bidirectionalEncryption = true;
        this.totalRotors = 5;
        this.operatingRotors = 3;
        this.totalReflectors = 2;
        this.configureLater = false;
     }
    public String getName () {
        return name;
    }
    public String getDescription () {
        return description;
    }
    public boolean isConfigureLater () {
        return configureLater;
    }
    public String getRotationConfiguration () {
        return rotationConfiguration;
    }
    public boolean isReflectorPresent () {
        return reflectorPresent;
    }
    public int getTotalRotors () {
        return totalRotors;
    }
    public int getOperatingRotors () {
        return operatingRotors;
    }
    public int getTotalReflectors () {
        return totalReflectors;
    }
    public boolean isBidirectionalEncryption () {
        return bidirectionalEncryption;
    }
}
