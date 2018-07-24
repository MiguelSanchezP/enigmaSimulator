package com.miguelsanchez;

import com.miguelsanchez.components.Alphabet;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("MainWindow.fxml"));
        primaryStage.setTitle("Enigma Machine Simulator");
        primaryStage.setScene(new Scene(root, 900, 550));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void stop () throws Exception {
        try {
            Alphabet.storeAlphabets();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void init () throws Exception {
        try {
            Alphabet.loadAlphabets();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
