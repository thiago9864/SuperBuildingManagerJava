/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import gui.Router;
import gui.SindicoGUI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        
        sindicoGUI.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                 if(evt.getSource() == sindicoGUI.getjButtonSalvar()){
                    //Salva as informações do jTextFields no banco de dados como informações dos sindicos
                 }
                 if(evt.getSource() == sindicoGUI.getjButtonVoltar()){
                    //Voltar para a tela do Main Menu
                    Router.getInstance().voltar();
                 }
            }
        });
        
        //Implemente uma função para sempre que iniciar a tela pegar as informações relativas ao sindico
        //Que estão no banco de dados e colocar nos jTextFields para a pessoa modificar caso necessario
    }
}
