/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import gui.Orcamento;
import gui.OrcamentoAtualizar;
import gui.Router;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        
        orcamentoGUI.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                 if(evt.getSource() == orcamentoGUI.getjButtonAtualizar()){
                    //Abrir a tela do OrcamentoAtualizar
                 }
                 if(evt.getSource() == orcamentoGUI.getjButtonExportar()){
                    //Salvar um arquivo em pdf no computador
                 }
                 if(evt.getSource() == orcamentoGUI.getjButtonMudarMês()){
                    //Abrir o popup de mudar o mês
                 }
                 if(evt.getSource() == orcamentoGUI.getjButtonOk()){
                    //Troca o mes do qual as informações estão sendo tiradas para o colocado no jComboBox
                 }
                 if(evt.getSource() == orcamentoGUI.getjButtonVoltar()){
                    //Voltar para o main menu, mesmo codigo das outras telas
                 }
            }
        });
        //Devem ser colocadas todas as Receitas e Gastos nas jTextAreas respectivas
        //Isso deve ser feito ao iniciar da tela, para no momento que a pessoa abre, aparecer diretamente na tela
        //De preferencia aparecendo o mês atual que está no computador da pessoa
        
        orcamentoAtualizarGUI.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                 if(evt.getSource() == orcamentoAtualizarGUI.getjButtonRemover()){
                    //Remover o gasto ou a receita do banco de dados
                 }
                 if(evt.getSource() == orcamentoAtualizarGUI.getjButtonVoltar()){
                    //Voltar para a tela do orçamento, usa o mesmo codigo do button voltar do orçamentoGUI
                 }
                 if(evt.getSource() == orcamentoAtualizarGUI.getjButtonSalvar()){
                    //Salvar os dados que estão nos jTextFields como um novo gasto ou uma nova receita
                 }
            }
        });
        
        //Não esqueça de que todos os gastos e receitas devem aparecer nas duas listas
    }
}
