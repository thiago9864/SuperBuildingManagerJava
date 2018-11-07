/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import gui.Orcamento;
import gui.OrcamentoAtualizar;
import gui.Router;

/**
 *
 * @author thiagoalmeida
 */
public class OrcamentoController {
    private Orcamento orcamentoGUI;
    private OrcamentoAtualizar orcamentoAtualizarGUI;

    public OrcamentoController() {
        //cria gui
        orcamentoGUI = new Orcamento();
        orcamentoGUI.setTAG("Orcamento");
        
        orcamentoAtualizarGUI = new OrcamentoAtualizar();
        orcamentoAtualizarGUI.setTAG("OrcamentoAtualizar");
        
        //adiciona gui ao gerenciador de navegacao
        Router.getInstance().addJPanel(orcamentoGUI);
        Router.getInstance().addJPanel(orcamentoAtualizarGUI);
    }
}
