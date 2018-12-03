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
import persistencia.CondominioDAO;

/**
 *
 * @author thiagoalmeida
 */
public class CondominioController {
    private Condominio condominioGUI;
    private CondominioDAO condominioDAO;
    private dominio.Condominio condominio;

    public CondominioController() {
        //cria gui
        condominioGUI = new Condominio();
        condominioGUI.setTAG("Condominio");
        
        condominioDAO = new CondominioDAO();
        
        carregaCondominio();
        
        //adiciona gui ao gerenciador de navegacao
        Router.getInstance().addJPanel(condominioGUI);
    
        condominioGUI.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                 if(evt.getSource() == condominioGUI.getjButtonSalvar()){
                    //implementar o salvar dos dados, tem um exemplo no login
                    atualizarCondominio();
                 }
                 if(evt.getSource() == condominioGUI.getjButtonVoltar()){
                    //volta para a tela anterior, copiar das outras telas
                    Router.getInstance().voltar();
                 }
            }
        });
    }
    
    private void carregaCondominio(){
        condominio = condominioDAO.read(0);
        String bairro="", rua="", endereco[] = condominio.getEndereco().split(",");
        if(endereco.length > 1){
            bairro = condominio.getEndereco().split(",")[1];
            rua = condominio.getEndereco().split(",")[0];
        }
        condominioGUI.setTextjTextFieldBairro(bairro);
        condominioGUI.setTextjTextFieldCNPJ("");
        condominioGUI.setTextjTextFieldCidade(condominio.getCidade());
        condominioGUI.setTextjTextFieldEmail("");
        condominioGUI.setTextjTextFieldEstado(condominio.getEstado());
        condominioGUI.setTextjTextFieldNome(condominio.getNome());
        condominioGUI.setTextjTextFieldNumero(condominio.getNumero());
        condominioGUI.setTextjTextFieldRua(rua);
        condominioGUI.setTextjTextFieldTelefone(condominio.getTelefone());
    }

    
    private void atualizarCondominio(){
        condominio.setId(0);
        condominio.setCidade(condominioGUI.getTextjTextFieldCidade());
        condominio.setEstado(condominioGUI.getTextjTextFieldEstado());
        condominio.setNome(condominioGUI.getTextjTextFieldNome());
        condominio.setNumero(condominioGUI.getTextjTextFieldNumero());
        condominio.setEndereco(condominioGUI.getTextjTextFieldRua() + "," + condominioGUI.getTextjTextFieldBairro());
        condominio.setTelefone(condominioGUI.getTextjTextFieldTelefone());
        if(condominioDAO.update(condominio)){
            condominioGUI.criarMensagemInformacao("SUCESSO", "Dados alterados com sucesso");
        }else{
            condominioGUI.criarMensagemErro("ERRO", "Houve um problema ao atualizar os dados");
        }
    }
}
