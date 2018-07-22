package com.miguelsanchez.auxiliars;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import java.util.ArrayList;

public class AllLettersPane {
    private static String alphabet = "A B C D E F G H I J K L M N O P Q R S T U V W X Y Z";
    private static String[] numberToLetter = alphabet.split(" ");
    private static ArrayList<TextField> alltfs = new ArrayList<>();

    private static GridPane generateTheAllLettersPane () {
        GridPane tempPane = new GridPane();
        boolean finish = false;
        boolean oddRound = true;
        int r = 0;
        int round = 0;
        while (!finish) {
            for (int c = 0; c < 10; c++) {
                String text = numberToLetter [round];
                if (oddRound) {
                    Label tempL = new Label();
                    tempL.setId((text + "L"));
                    text += ":";
                    tempL.setText(text);
                    tempPane.add(tempL, c, r);
                    oddRound = false;
                }else{
                    TextField tempTF = new TextField();
                    tempTF.setId((text+"T"));
                    tempTF.setPrefWidth(30);
                    tempTF.setPromptText(text);
                    alltfs.add(tempTF);
                    tempPane.add(tempTF, c, r);
                    oddRound = true;
                    round++;
                }
                if (round == numberToLetter.length) {
                    finish = true;
                    break;
                }
            }
            r++;
        }
        tempPane.setVgap(10.0);
        tempPane.setHgap(10.0);
        return tempPane;
    }

    private static GridPane allLettersPane = generateTheAllLettersPane();

    public static GridPane getAllLettersPane () {
        return allLettersPane;
    }

    public static int toNumber (String t) {
        for (int i = 0; i<numberToLetter.length; i++) {
            if (t.equals(numberToLetter[i])) {
                return i;
            }
        }
        return -1;
    }

    public static ArrayList<TextField> getAllTfs () {
        return alltfs;
    }
    public static void updateLettersPane () {
        boolean finish = false;
        boolean oddRound = true;
        int r = 0;
        int round = 0;
        while (!finish) {
            for (int c = 0; c < 10; c++) {
                if (!oddRound) {
                    allLettersPane.getChildren().remove(round);
                    allLettersPane.add(alltfs.get(round/2), c, r);
                    oddRound = true;
                    round++;
                }else{
                    round++;
                    oddRound = false;
                }
                if (round == numberToLetter.length) {
                    finish = true;
                    break;
                }
            }
            r++;
        }
    }
}
