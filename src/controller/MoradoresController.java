/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import gui.Moradores;
import gui.MoradoresAdicionar;
import gui.Router;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    
            moradoresGUI.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                 if(evt.getSource() == moradoresGUI.getjButtonApagarNão()){
                    //fechar o popup
                 }
                 if(evt.getSource() == moradoresGUI.getjButtonApagarSim()){
                    //apagar o morador selecionado e se não tiver morador selecionado, gere um erro
                 }
                 if(evt.getSource() == moradoresGUI.getjButtonDarBaixa()){
                    //coloque o boleto como pago
                 }
                 if(evt.getSource() == moradoresGUI.getjButtonEmitirBoleto()){
                    //nome obvio
                 }
                 if(evt.getSource() == moradoresGUI.getjButtonEmitirSegundaVia()){
                    //nome obvio
                 }
                 if(evt.getSource() == moradoresGUI.getjButtonReturn()){
                    //volte para a tela do main menu, tem implementado em outras telas
                 }
                 if(evt.getSource() == moradoresGUI.getjButtonMonth01()){
                    //Mostre as informacoes do mês 1 e o ano respectivo
                 }
                 if(evt.getSource() == moradoresGUI.getjButtonMonth02()){
                    //Mostre as informacoes do mês 2 e o ano respectivo
                 }
                 if(evt.getSource() == moradoresGUI.getjButtonMonth03()){
                    //Mostre as informacoes do mês 3 e o ano respectivo
                 }
                 if(evt.getSource() == moradoresGUI.getjButtonMonth04()){
                    //Mostre as informacoes do mês 4 e o ano respectivo
                 }
                 if(evt.getSource() == moradoresGUI.getjButtonMonth05()){
                    //Mostre as informacoes do mês 5 e o ano respectivo
                 }
                 if(evt.getSource() == moradoresGUI.getjButtonMonth06()){
                    //Mostre as informacoes do mês 6 e o ano respectivo
                 }
                 if(evt.getSource() == moradoresGUI.getjButtonMonth07()){
                    //Mostre as informacoes do mês 7 e o ano respectivo
                 }
                 if(evt.getSource() == moradoresGUI.getjButtonMonth08()){
                    //Mostre as informacoes do mês 8 e o ano respectivo
                 }
                 if(evt.getSource() == moradoresGUI.getjButtonMonth09()){
                    //Mostre as informacoes do mês 9 e o ano respectivo
                 }
                 if(evt.getSource() == moradoresGUI.getjButtonMonth10()){
                    //Mostre as informacoes do mês 10 e o ano respectivo
                 }
                 if(evt.getSource() == moradoresGUI.getjButtonMonth11()){
                    //Mostre as informacoes do mês 11 e o ano respectivo
                 }
                 if(evt.getSource() == moradoresGUI.getjButtonMonth12()){
                    //Mostre as informacoes do mês 12 e o ano respectivo
                 }
            }
        });
        
        moradoresAddGUI.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                 if(evt.getSource() == moradoresAddGUI.getjButtonCancelar()){
                    //voltar para a tela moradores
                 }
                 if(evt.getSource() == moradoresAddGUI.getjButtonSalvar()){
                    //implementar o salvar dos dados colocados nos jTextFields no banco de dados como um novo morador
                 }
            }
        });
            
        //implemente a seleção da lista que será gerada a partir do banco de dados
        //e use o ListSelectionListener para passar as informações para o info e a outra aba
        
        
    }
        
    
}
