package com.miguelsanchez;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ResourceBundle;

import static com.miguelsanchez.auxiliars.AllLettersPane.generateTheAllLettersPane;

public class NewPlugboardController implements Initializable {

//    static GridPane configureTheGridPane () {
//        GridPane PlugConfigurationPane = new GridPane();
//        RadioButton tempRB1 = new RadioButton();
//        tempRB1.setSelected(true);
//        tempRB1.setText("Double direction wire");
//        Tooltip tooltipRB1 = new Tooltip();
//        tooltipRB1.setText("Button Up: the wire goes from A to B and from B to A; Button Down: the wire only goes from A to B");
//        tempRB1.setTooltip(tooltipRB1);
//        PlugConfigurationPane.add(tempRB1, 0, 0);
//        GridPane gp = getAllLettersPane();
//        System.out.println(gp.toString());
//        PlugConfigurationPane.add(gp, 0, 1);
//        RadioButton tempRB2 = new RadioButton();
//        tempRB2.setText("Configure Later");
//        Tooltip tooltipRB2 = new Tooltip ();
//        tooltipRB2.setText("Button Up: Configure the plugboard later; Button Down: Configure the plugboard now");
//        tempRB2.setTooltip (tooltipRB2);
//        PlugConfigurationPane.add(tempRB2, 0, 2);
//        return PlugConfigurationPane;
//    }

    @FXML
    private GridPane PlugConfigurationPane;

    @Override
    public void initialize (URL location, ResourceBundle resources) {
        configureTheGridPane();
    }
    
    @FXML
    void configureTheGridPane () {
        GridPane gp = generateTheAllLettersPane();
        GridPane gp2 = PlugConfigurationPane;
        System.out.println(gp);
        System.out.println(gp2);
    }
}
