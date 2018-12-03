/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dominio.Boleto;
import dominio.Morador;
import dominio.StatusBoleto;
import gui.Condominio;
import gui.Moradores;
import gui.MoradoresAdicionar;
import gui.Router;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import persistencia.BoletoDAO;
import persistencia.MoradorDAO;
import persistencia.StatusBoletoDAO;
/**
 *
 * @author thiagoalmeida
 */
public class MoradoresController {
    private Moradores moradoresGUI;
    private MoradoresAdicionar moradoresAddGUI;
    private MoradorDAO moradorDAO;
    private BoletoDAO boletoDAO;
    private Boleto boletoAtual;
    private StatusBoletoDAO statusBoletoDAO;

    public MoradoresController() {
        //cria gui
        moradoresGUI = new Moradores();
        moradoresGUI.setTAG("Moradores");
        
        moradoresAddGUI = new MoradoresAdicionar();
        moradoresAddGUI.setTAG("MoradoresAdicionar");
        
        //adiciona gui ao gerenciador de navegacao
        Router.getInstance().addJPanel(moradoresGUI);
        Router.getInstance().addJPanel(moradoresAddGUI);
        
        moradorDAO = new MoradorDAO();
        boletoDAO = new BoletoDAO();
        statusBoletoDAO = new StatusBoletoDAO();
        
        carregaInformacoes();
    
            moradoresGUI.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                 if(evt.getSource() == moradoresGUI.getjButtonApagarNão()){
                    //fechar o popup
                 }
                 if(evt.getSource() == moradoresGUI.getjButtonApagarSim()){
                    //apagar o morador selecionado e se não tiver morador selecionado, gere um erro
                 }
                 if(evt.getSource() == moradoresGUI.getjButtonRemover()){
                     apagarMorador();
                 }
                 if(evt.getSource() == moradoresGUI.getjButtonDarBaixa()){
                    //coloque o boleto como pago
                    darBaixaBoleto();
                 }
                 if(evt.getSource() == moradoresGUI.getjButtonEmitirBoleto()){
                    //nome obvio
                    emitirBoleto();
                 }
                 if(evt.getSource() == moradoresGUI.getjButtonEmitirSegundaVia()){
                    //nome obvio
                 }
                 if(evt.getSource() == moradoresGUI.getjButtonReturn()){
                    //volte para a tela do main menu, tem implementado em outras telas
                    Router.getInstance().voltar();
                 }
                 if(evt.getSource() == moradoresGUI.getjButtonAdicionar()){
                     Router.getInstance().abrir("MoradoresAdicionar");
                 }
                 if(evt.getSource() == moradoresGUI.getjButtonMonth01()){
                    //Mostre as informacoes do mês 1 e o ano respectivo
                    exibeInfoBoleto(0);
                 }
                 if(evt.getSource() == moradoresGUI.getjButtonMonth02()){
                    //Mostre as informacoes do mês 2 e o ano respectivo
                    exibeInfoBoleto(1);
                 }
                 if(evt.getSource() == moradoresGUI.getjButtonMonth03()){
                    //Mostre as informacoes do mês 3 e o ano respectivo
                    exibeInfoBoleto(2);
                 }
                 if(evt.getSource() == moradoresGUI.getjButtonMonth04()){
                    //Mostre as informacoes do mês 4 e o ano respectivo
                    exibeInfoBoleto(3);
                 }
                 if(evt.getSource() == moradoresGUI.getjButtonMonth05()){
                    //Mostre as informacoes do mês 5 e o ano respectivo
                    exibeInfoBoleto(4);
                 }
                 if(evt.getSource() == moradoresGUI.getjButtonMonth06()){
                    //Mostre as informacoes do mês 6 e o ano respectivo
                    exibeInfoBoleto(5);
                 }
                 if(evt.getSource() == moradoresGUI.getjButtonMonth07()){
                    //Mostre as informacoes do mês 7 e o ano respectivo
                    exibeInfoBoleto(6);
                 }
                 if(evt.getSource() == moradoresGUI.getjButtonMonth08()){
                    //Mostre as informacoes do mês 8 e o ano respectivo
                    exibeInfoBoleto(7);
                 }
                 if(evt.getSource() == moradoresGUI.getjButtonMonth09()){
                    //Mostre as informacoes do mês 9 e o ano respectivo
                    exibeInfoBoleto(8);
                 }
                 if(evt.getSource() == moradoresGUI.getjButtonMonth10()){
                    //Mostre as informacoes do mês 10 e o ano respectivo
                    exibeInfoBoleto(9);
                 }
                 if(evt.getSource() == moradoresGUI.getjButtonMonth11()){
                    //Mostre as informacoes do mês 11 e o ano respectivo
                    exibeInfoBoleto(10);
                 }
                 if(evt.getSource() == moradoresGUI.getjButtonMonth12()){
                    //Mostre as informacoes do mês 12 e o ano respectivo
                    exibeInfoBoleto(11);
                 }
            }
        });
        
        moradoresAddGUI.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                 if(evt.getSource() == moradoresAddGUI.getjButtonCancelar()){
                    //voltar para a tela moradores
                    Router.getInstance().voltar();
                 }
                 if(evt.getSource() == moradoresAddGUI.getjButtonSalvar()){
                    //implementar o salvar dos dados colocados nos jTextFields no banco de dados como um novo morador
                     adicionarMorador();
                 }
            }
        });
            
        //implemente a seleção da lista que será gerada a partir do banco de dados
        //e use o ListSelectionListener para passar as informações para o info e a outra aba
        moradoresGUI.addListSelectionListener(new ListSelectionListener(){
            @Override
            public void valueChanged(ListSelectionEvent lse) {
                if(lse.getSource() == moradoresGUI.getjListMoradores()){
                    exibirMorador();
                }
            }
            
        });
        
    }
        
    private void carregaInformacoes(){
        ArrayList<Morador> moradores = moradorDAO.listMoradores();
        for(Morador m: moradores){
            moradoresGUI.addMorador(m.getNome());
        }
        int anoAtual = Calendar.getInstance().get(Calendar.YEAR);
        for (int i = 0; 2015 + i <= anoAtual; i++) {
            moradoresGUI.addAno(2015 + i);
        }
        
    }
    
    private void adicionarMorador(){
        Morador morador = new Morador();
        dominio.Condominio condominio = new dominio.Condominio(0);
        if(moradoresAddGUI.getTextjTextFieldAndar().isEmpty() || 
           moradoresAddGUI.getTextjTextFieldApartamento().isEmpty() || 
           moradoresAddGUI.getTextjTextFieldBloco().isEmpty() || 
           moradoresAddGUI.getTextjTextFieldCPF().isEmpty() || 
           moradoresAddGUI.getTextjTextFieldEmail().isEmpty() || 
           moradoresAddGUI.getTextjTextFieldNome().isEmpty() || 
           moradoresAddGUI.getTextjTextFieldTelefone().isEmpty()){
            moradoresAddGUI.criarMensagemErro("ERRO", "Preencha todos os campos.");
            return;
        }
        try{
            morador.setAndar(Integer.parseInt(moradoresAddGUI.getTextjTextFieldAndar()));
            morador.setApartamento(Integer.parseInt(moradoresAddGUI.getTextjTextFieldApartamento()));
            morador.setBloco(Integer.parseInt(moradoresAddGUI.getTextjTextFieldBloco()));
            morador.setCondominio(condominio);
            morador.setCpf(moradoresAddGUI.getTextjTextFieldCPF());
            morador.setEmail(moradoresAddGUI.getTextjTextFieldEmail());
            morador.setNome(moradoresAddGUI.getTextjTextFieldNome());
            morador.setTelefone(moradoresAddGUI.getTextjTextFieldTelefone());
        }catch(Exception e){
            moradoresAddGUI.criarMensagemErro("ERRO", "Preencha corretamente os campos.");
            return;
        }
        moradorDAO.create(morador);
        moradoresGUI.addMorador(morador.getNome());
    }
    
    private void apagarMorador(){
        if(moradoresGUI.getjListMoradores().isSelectionEmpty())
            moradoresGUI.criarMensagemErro("ERRO", "Selecione um morador");
        String nome = moradoresGUI.getjListMoradores().getSelectedValue();
        ArrayList<Morador> moradores = moradorDAO.listMoradores();
        for(Morador m: moradores){
            if(m.getNome().equals(nome)){
                moradorDAO.delete(m.getId());
                moradoresGUI.removerMorador(nome);
                return;
            }
        }
    }
    
    private void exibirMorador(){
        String nome = moradoresGUI.getjListMoradores().getSelectedValue();
        ArrayList<Morador> moradores = moradorDAO.listMoradores();
        for(Morador m: moradores){
            if(m.getNome().equals(nome)){
                String txt = "Nome: " + m.getNome() + "\n";
                txt += "CPF: " + m.getCpf() + "\n";
                txt += "Email: " + m.getEmail() + "\n";
                txt += "Telefone: " + m.getTelefone() + "\n";
                txt += "Bloco: " + m.getBloco() + "\n";
                txt += "Andar: " + m.getAndar() + "\n";
                txt += "Apartamento: " + m.getApartamento() + "\n";
                moradoresGUI.setTextInfoGeralMorador(txt);
                return;
            }
        }
    }
    
    private void exibeInfoBoleto(int mes){
        if(moradoresGUI.getjListMoradores().isSelectionEmpty()){
            moradoresGUI.criarMensagemErro("ERRO", "Selecione um morador");
            return;
        }
        String nome = moradoresGUI.getjListMoradores().getSelectedValue();
        int idMorador = 0;
        ArrayList<Morador> moradores = moradorDAO.listMoradores();
        for(Morador m: moradores){
            if(m.getNome().equals(nome)){
                idMorador = m.getId();
            }
        }
        Calendar c = Calendar.getInstance();
        ArrayList<Boleto> boletos = boletoDAO.list(idMorador);
        for(Boleto b: boletos){
            c.setTime(b.getDataVencimento());
            if(c.get(Calendar.MONTH)==mes && c.get(Calendar.YEAR)==moradoresGUI.getSelectedYear()){
                String txt = "Status: " + b.getStatusBoleto().getDescricao();
                moradoresGUI.setTextInfoPagamentosMorador(txt);
                boletoAtual = b;
                return;
            }
        }
        moradoresGUI.setTextInfoPagamentosMorador("Nada a exibir neste caixa.");
    }
    
    private void darBaixaBoleto(){
        if(boletoAtual!=null){
            StatusBoleto st = boletoAtual.getStatusBoleto();
            st.setDescricao("Pago");
            statusBoletoDAO.update(st);
        }else{
            moradoresGUI.criarMensagemErro("ERRO", "Selecione um boleto");
        }
    }
    
    private void emitirBoleto(){
        
    }
}
