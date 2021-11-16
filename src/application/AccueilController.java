/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import test.PartieGUI;
import java.awt.Button;
import javafx.scene.input.MouseEvent;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

/**
 *
 * @author clementinebleuze
 */
public class AccueilController implements Initializable{
    @FXML
    private Pane fond;
    @FXML
    private Button buttonStart;
    @FXML
    private Label labelHello;
    
    @Override
    public void initialize(URL url,ResourceBundle rb){
        System.out.println("Le contrôleur initialise la vue");
    }
    
    public void mouseClicked(MouseEvent me){
        System.out.println("cliqué!");
        PartieGUI.main(new String[]{""});
    }
}
