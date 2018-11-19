/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import gui.Opcoes;
import gui.Router;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        
        opcoesGUI.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                 if(evt.getSource() == opcoesGUI.getjButtonContatos()){
                    //abre uma tela com contatos importantes (vou criar uma tela nova
                 }
                 if(evt.getSource() == opcoesGUI.getjButtonInfoCondominio()){
                    //abre a tela condominio
                 }
                 if(evt.getSource() == opcoesGUI.getjButtonReturn()){
                    //volta pra tela anterior é só copiar o codigo de outra tela
                 }
                 if(evt.getSource() == opcoesGUI.getjButtonSuporte()){
                    //abre um popup com email de suporte para o programa
                 }
            }
        });
    }
}
