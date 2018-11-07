/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import gui.Router;
import gui.SindicoGUI;

/**
 *
 * @author thiagoalmeida
 */
public class SindicoController {
    private SindicoGUI sindicoGUI;

    public SindicoController() {
        //cria gui
        sindicoGUI = new SindicoGUI();
        sindicoGUI.setTAG("Sindico");
        
        //adiciona gui ao gerenciador de navegacao
        Router.getInstance().addJPanel(sindicoGUI);
    }
}
