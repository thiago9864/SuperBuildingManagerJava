/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import gui.Moradores;
import gui.MoradoresAdicionar;
import gui.Router;

/**
 *
 * @author thiagoalmeida
 */
public class MoradoresController {
    private Moradores moradoresGUI;
    private MoradoresAdicionar moradoresAddGUI;

    public MoradoresController() {
        //cria gui
        moradoresGUI = new Moradores();
        moradoresGUI.setTAG("Moradores");
        
        moradoresAddGUI = new MoradoresAdicionar();
        moradoresAddGUI.setTAG("MoradoresAdicionar");
        
        //adiciona gui ao gerenciador de navegacao
        Router.getInstance().addJPanel(moradoresGUI);
        Router.getInstance().addJPanel(moradoresAddGUI);
    }
}
