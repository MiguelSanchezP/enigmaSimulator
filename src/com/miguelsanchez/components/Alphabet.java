package com.miguelsanchez.components;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;

public class Alphabet {
    private String name;
    private String[] components;
    private String tempComponents;
    private String regex;
    private boolean defaultSeparation;
    private boolean existingAlphabet;
    private String existingAlphabetName;
    private String text;
    private static String filename = "./Files/alphabets.txt";
    private static ObservableList<Alphabet> alphabetsOL;

    private Alphabet(String name, String components, String regex) {
        this.name = name;
        this.components = components.split(regex);
        this.regex = regex;
    }
    private Alphabet (String name) {
        this.name = name;
    }

    public Alphabet () {
        this.name = "";
        this.tempComponents = "";
        this.regex = "-";
        defaultSeparation = true;
        existingAlphabet = false;
        this.existingAlphabetName="Select";
        this.text="";
    }

    public Alphabet (String name, String tempComponents, String regex, boolean defaultSeparation, boolean existingAlphabet, String existingAlphabetName, String text) {
        this.name = name;
        this.tempComponents = tempComponents;
        this.components = tempComponents.split(regex);
        this.regex = regex;
        this.defaultSeparation = defaultSeparation;
        this.existingAlphabet = existingAlphabet;
        this.existingAlphabetName = existingAlphabetName;
        this.text=text;
    }

    public String getName () {
        return this.name;
    }

    public String[] getComponents () {
        return components;
    }

    public String getRegex () {
        return regex;
    }

    public boolean isDefaultSeparation () {
        return defaultSeparation;
    }

    public boolean isExistingAlphabet () {
        return existingAlphabet;
    }

    public String getExistingAlphabetName () {
        return existingAlphabetName;
    }

    public String getText () {
        return text;
    }

    public String getTempComponents () {
        return tempComponents;
    }

    public static Alphabet getAlphabet (String name) {
        for (Alphabet alphabet : alphabetsOL) {
            if (alphabet.getName().equals(name)) {
                return alphabet;
            }
        }
        if (name.equals("7Bf*RtArj+aKWz53g_Jp")) {
            return new Alphabet ("NoAlphabet");
        }
        return alphabetsOL.get(0);
    }

    public static String toString (String[] input, String regex) {
        StringBuilder sb = new StringBuilder();
        for (String s : input) {
            sb.append(s);
            sb.append(regex);
        }
        if (sb.toString().isEmpty()) {
            return "";
        }
        return sb.toString();
    }

    public static ObservableList<String> getAlphabetsOLNames() {
        ObservableList<String> tempList = FXCollections.observableArrayList();
        for (Alphabet a : alphabetsOL) {
            tempList.add(a.getName());
        }
        return tempList;
    }

    public static ObservableList<Alphabet> getAlphabetsOL () {
        return alphabetsOL;
    }

    public static void addAlphabet (Alphabet alphabet) {
        alphabetsOL.add(alphabet);
        try {
            storeAlphabets();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void storeAlphabets () throws IOException {
        Path path = Paths.get(filename);
        BufferedWriter bw = Files.newBufferedWriter(path);

        try {
            Iterator<Alphabet> alphabetsIterator = alphabetsOL.iterator();
            while(alphabetsIterator.hasNext()) {
                Alphabet alphabet = alphabetsIterator.next();
                bw.write(String.format("%s\t%s\t%s",
                        alphabet.getName(),
                        toString(alphabet.getComponents(), alphabet.getRegex()),
                        alphabet.getRegex()));
                bw.newLine();
            }
        } finally {
            bw.close();
        }
    }

    public static void loadAlphabets () throws IOException {
        alphabetsOL = FXCollections.observableArrayList();
        Path path = Paths.get(filename);
        BufferedReader br = Files.newBufferedReader(path);
        String input;

        try {
            while ((input = br.readLine()) != null) {
                String[] alphabetParts = input.split("\t");
                String name = alphabetParts [0];
                String components = alphabetParts[1];
                String regex = alphabetParts [2];
                Alphabet alphabet = new Alphabet (name, components, regex);
                alphabetsOL.add(alphabet);
            }
        }finally{
            br.close();
        }

    }
}
