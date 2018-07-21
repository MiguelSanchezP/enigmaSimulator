package com.miguelsanchez.auxiliars;

import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import java.util.ArrayList;

public class AllLettersPane {
    private static String alphabet = "A B C D E F G H I J K L M N O P Q R S T U V W X Y Z";
    private static String[] numberToLetter = alphabet.split(" ");
    private static ArrayList<TextField> alltfs = new ArrayList<>();

    private static EventHandler <KeyEvent> setDoubleProperty = new EventHandler<KeyEvent>() {
        @Override
        public void handle(KeyEvent event) {
            for (int i = 0; i<numberToLetter.length; i++) {
                String text = alltfs.get(i).getText();
                String input = event.getCharacter();
                if (text!=null && text.equals(input)) {
                    int num = toNumber(text);
                    if (num != -1) {
                        alltfs.get(num).setText(text);
                    }else{
                        System.out.println("ERROR");
                    }
                }
            }
        }
    };

    private static void handlenewLetter (String k) {
        for (int i = 0; i<numberToLetter.length; i++) {
            String text = alltfs.get(i).getText();
            if (text!=null && text.equals(k)) {
                int num = toNumber(text);
                if (num != -1) {
                    allLettersPane.getChildren().remove(alltfs.get(num));
                    alltfs.get(num).setText(text);
                    int a = i/5;
                    allLettersPane.add(alltfs.get(num), (a), (2*i-a*10));
                }else{
                    System.out.println("ERROR");
                }
            }
        }
    }

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

    public static void proccessKey (boolean isDouble, GridPane gp, String k) {
        if (isDouble) {
            gp.getChildren().remove(allLettersPane);
            handlenewLetter (k);
            gp.add(allLettersPane, 0, 1);
        }
    }

    private static int toNumber (String t) {
        for (int i = 0; i<numberToLetter.length; i++) {
            if (t.equals(numberToLetter[i])) {
                return i;
            }
        }
        return -1;
    }
}
