package com.miguelsanchez.auxiliars;

import java.util.ArrayList;

public class Alphabet {
    private String name;
    private String[] components;
    private static ArrayList<Alphabet> alphabets = new ArrayList<Alphabet>() {{
        add(new Alphabet ("Standard", "A-B-C-D-E-F-G-H-I-J-K-L-M-N-o-P-Q-R-S-T-U-V-W-X-Y-Z"));
    }};

    public Alphabet(String name, String components) {
        this.name = name;
        this.components = components.split("-");
    }

    public String getName () {
        return this.name;
    }
    public String[] getComponents () {
        return components;
    }
    public static ArrayList<Alphabet> getAlphabets () {
        return alphabets;
    }
    public static ArrayList<String> getAlphabetNames () {
        ArrayList<String> allNames = new ArrayList<>();
        for (int i : alphabets) {
            allNames.add(alphabets.get(i).getName());
        }
    }
    public Alphabet getAlphabet (int i) {
        return alphabets.get(i);
    }
    public static Alphabet getAlphabet (String name) {
        for (Alphabet alphabet : alphabets) {
            if (alphabet.getName().equals(name)) {
                return alphabet;
            }
        }
        return alphabets.get(0);
    }
}
