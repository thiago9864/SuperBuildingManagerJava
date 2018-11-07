/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import gui.Condominio;
import gui.Router;

/**
 *
 * @author thiagoalmeida
 */
public class CondominioController {
    private Condominio condominioGUI;

    public CondominioController() {
        //cria gui
        condominioGUI = new Condominio();
        condominioGUI.setTAG("Condominio");
        
        //adiciona gui ao gerenciador de navegacao
        Router.getInstance().addJPanel(condominioGUI);
    }
    
    
}
