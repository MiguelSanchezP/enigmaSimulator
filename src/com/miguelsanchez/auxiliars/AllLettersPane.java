package com.miguelsanchez.auxiliars;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class AllLettersPane {
    private static String alphabet = "A B C D E F G H I J K L M N O P Q R S T U V W X Y Z";
    private static String[] numberToLetter = alphabet.split(" ");

    public static GridPane generateTheAllLettersPane () {
        GridPane allLettersPane = new GridPane();
        boolean finish = false;
        boolean oddRound = true;
        int r = 0;
        int round = 0;
        while (!finish) {
            for (int c = 0; c < 10; c++) {
                String text = numberToLetter [((r*10+c)/2)];
                if (oddRound) {
                    text += ":";
                    Label tempL = new Label();
                    tempL.setText(text);
                    allLettersPane.add(tempL, c, r);
                    oddRound = false;
                }else{
                    TextField tempTF = new TextField();
                    tempTF.setPrefWidth(30);
                    tempTF.setPromptText(text);
                    allLettersPane.add(tempTF, c, r);
                    oddRound = true;
                    round++;
                }
                if (round == (numberToLetter.length*2)) {
                    finish = true;
                    break;
                }
            }
            r++;
        }
        return allLettersPane;
    }

}
