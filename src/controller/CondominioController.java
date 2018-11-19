/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import gui.Condominio;
import gui.Router;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    
        condominioGUI.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                 if(evt.getSource() == condominioGUI.getjButtonSalvar()){
                    //implementar o salvar dos dados, tem um exemplo no login
                 }
                 if(evt.getSource() == condominioGUI.getjButtonVoltar()){
                    //volta para a tela anterior, copiar das outras telas
                 }
            }
        });
    }
    
}
