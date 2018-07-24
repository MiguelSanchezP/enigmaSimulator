package com.miguelsanchez.components;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;

public class Alphabet {
    private String name;
    private String[] components;
    private static ArrayList<Alphabet> alphabets = new ArrayList<Alphabet>() {{
        add(new Alphabet ("Standard", "A-B-C-D-E-F-G-H-I-J-K-L-M-N-o-P-Q-R-S-T-U-V-W-X-Y-Z"));
    }};
    private static String filename = "./Files/alphabets.txt";
    private static ObservableList<Alphabet> alphabetsOL;

    public Alphabet(String name, String components) {
        this.name = name;
        this.components = components.split("-");
    }
    private Alphabet (String name) {
        this.name = name;
    }

    private Alphabet (String name, String[] components) {
        this.name = name;
        this.components = components;
    }

    public String getName () {
        return this.name;
    }

    public String[] getComponents () {
        return components;
    }

//    public Alphabet getAlphabet (int i) {
//        return alphabets.get(i);
//    }
    public static Alphabet getAlphabet (String name) {
        for (Alphabet alphabet : alphabets) {
            if (alphabet.getName().equals(name)) {
                return alphabet;
            }
        }
        if (name.equals("7Bf*RtArj+aKWz53g_Jp")) {
            return new Alphabet ("NoAlphabet");
        }
        return alphabets.get(0);
    }

    public static String toString (String[] input) {
        String string = "";

        for (String s : input) {
            string += s;
            string += "-";
        }
        return string;
    }

    public static ObservableList<String> getAlphabetsOL () {
        ObservableList<String> tempList = FXCollections.observableArrayList();
        for (Alphabet a : alphabetsOL) {
            tempList.add(a.getName());
        }
        return tempList;
    }

    public static void storeAlphabets () throws IOException {
        Path path = Paths.get(filename);
        BufferedWriter bw = Files.newBufferedWriter(path);

//        try (BufferedWriter bw = Files.newBufferedWriter(path)){
        try {
            Iterator<Alphabet> alphabetsIterator = alphabetsOL.iterator();
            while(alphabetsIterator.hasNext()) {
                Alphabet alphabet = alphabetsIterator.next();
                bw.write(String.format("%s\t%s",
                        alphabet.getName(),
                        toString(alphabet.getComponents())));
                bw.newLine();
            }
//            for (Alphabet alphabet : alphabetsOL) {
//                bw.write(String.format("%s\t%s", alphabet.getName(), toString(alphabet.getComponents())));
//            }
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
                Alphabet alphabet = new Alphabet (name, components);
                alphabetsOL.add(alphabet);
            }
        }finally{
            br.close();
        }

    }
}
