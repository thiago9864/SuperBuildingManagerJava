/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import gui.Opcoes;
import gui.Router;

/**
 *
 * @author thiagoalmeida
 */
public class OpcoesController {
    private Opcoes opcoesGUI;

    public OpcoesController() {
        //cria gui
        opcoesGUI = new Opcoes();
        opcoesGUI.setTAG("Opcoes");
        
        //adiciona gui ao gerenciador de navegacao
        Router.getInstance().addJPanel(opcoesGUI);
    }
}
