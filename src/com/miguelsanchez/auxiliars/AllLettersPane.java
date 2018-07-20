package com.miguelsanchez.auxiliars;

import javafx.fxml.FXML;
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
                if (oddRound) {
                    String text = numberToLetter[(round/2)];
                    text += ":";
                    Label tempL = new Label();
                    tempL.setText(text);
                    allLettersPane.add(tempL, c, r);
                    oddRound = false;
                }else{
                    String text;
                    if (r>0) {
                        text = numberToLetter[(round/ 2)];
                    }else{
                        text = numberToLetter[(round/2)];
                    }
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
        System.out.println(allLettersPane);
        return allLettersPane;
    }

    @FXML
    public static void setAGridPane (GridPane gp) {
        boolean finish = false;
        boolean oddRound = true;
        int r = 0;
        int round = 0;
        while(!finish) {
            for (int c = 0; c<10; c++) {
                if (oddRound) {
                    String text = numberToLetter[(r*10+c)/2];
                    text +=":";
                    Label tempL = new Label();
                    tempL.setText(text);
                    gp.add(tempL, c, r);
                    oddRound = false;
                }else{
                    String text;
                    if (r>0) {
                        text = numberToLetter[((r * 10 + (c-1)) / 2)];
                    }else{
                        text = numberToLetter[((r*10+c)/2)];
                    }
                    TextField tempTF = new TextField();
                    tempTF.setPrefWidth(30);
                    tempTF.setPromptText(text);
                    gp.add(tempTF, c, r);
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

    }

    public static GridPane getAllLettersPane () {
        return generateTheAllLettersPane();
    }
}
