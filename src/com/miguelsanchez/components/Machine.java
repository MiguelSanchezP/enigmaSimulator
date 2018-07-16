package com.miguelsanchez.components;

public class Machine {
    private String name;
    private String description;
    private boolean reflectorPresent;
    private int totalRotors;
    private int operatingRotors;
    private int totalReflectors;
    private boolean plugboardPresent;

    public Machine (String name, String description, boolean reflectorPresent, int totalRotors, int operatingRotors, int totalReflectors, boolean plugboardPresent) {
        this.name = name;
        this.description = description;
        this.reflectorPresent = reflectorPresent;
        this.totalRotors = totalRotors;
        this.operatingRotors = operatingRotors;
        this.totalReflectors = totalReflectors;
        this.plugboardPresent = plugboardPresent;
    }
     public Machine () {
        this.name = "";
        this.description = "";
        this.reflectorPresent = true;
        this.plugboardPresent = true;
        this.totalRotors = 5;
        this.operatingRotors = 3;
        this.totalReflectors = 2;
     }
    public String getName () {
        return name;
    }
    public String getDescription () {
        return description;
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
    public boolean isPlugboardPresent() {
        return plugboardPresent;
    }
}
