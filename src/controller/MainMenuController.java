/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import gui.MainMenu;
import gui.Router;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author thiagoalmeida
 */
public class MainMenuController {
     MainMenu mainMenuGUI;

    public MainMenuController() {
        mainMenuGUI = new MainMenu();
        mainMenuGUI.setTAG("MainMenu");
        
        //adiciona gui ao gerenciador de navegacao
        Router.getInstance().addJPanel(mainMenuGUI);
        
        mainMenuGUI.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                 if(evt.getSource() == mainMenuGUI.getBtnOpcoes()){
                    Router.getInstance().abrir("Opcoes");
                 }
                 if(evt.getSource() == mainMenuGUI.getBtnOrcamento()){
                     Router.getInstance().abrir("Orcamento");
                 }
                 if(evt.getSource() == mainMenuGUI.getBtnMoradores()){
                     Router.getInstance().abrir("Moradores");
                 }
            }
        });
    }
     
     
}
