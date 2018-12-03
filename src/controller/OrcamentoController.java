/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import gui.Orcamento;
import gui.OrcamentoAtualizar;
import gui.Router;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import persistencia.OrcamentoDAO;

/**
 *
 * @author thiagoalmeida
 */
public class OrcamentoController {
    private Orcamento orcamentoGUI;
    private OrcamentoAtualizar orcamentoAtualizarGUI;
    private OrcamentoDAO orcamentoDAO;
    private Object dominio;

    public OrcamentoController() {
        //cria gui
        orcamentoGUI = new Orcamento();
        orcamentoGUI.setTAG("Orcamento");
        
        orcamentoAtualizarGUI = new OrcamentoAtualizar();
        orcamentoAtualizarGUI.setTAG("OrcamentoAtualizar");
        
        //adiciona gui ao gerenciador de navegacao
        Router.getInstance().addJPanel(orcamentoGUI);
        Router.getInstance().addJPanel(orcamentoAtualizarGUI);
        
        orcamentoDAO = new OrcamentoDAO();
        
        orcamentoGUI.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                 if(evt.getSource() == orcamentoGUI.getjButtonAtualizar()){
                    //Abrir a tela do OrcamentoAtualizar
                    Router.getInstance().abrir("OrcamentoAtualizar");
                 }
                 if(evt.getSource() == orcamentoGUI.getjButtonExportar()){
                    //Salvar um arquivo em pdf no computador
                    exportarPDF();                    
                 }
                 if(evt.getSource() == orcamentoGUI.getjButtonMudarMês()){
                    //Abrir o popup de mudar o mês
                    orcamentoGUI.mostrarDialog();
                    
                 }
                 if(evt.getSource() == orcamentoGUI.getjButtonOk()){
                    //Troca o mes do qual as informações estão sendo tiradas para o colocado no jComboBox
                    atualizaInfo();
                    
                 }
                 if(evt.getSource() == orcamentoGUI.getjButtonVoltar()){
                    //Voltar para o main menu, mesmo codigo das outras telas
                    Router.getInstance().voltar();
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
                    removerGastoReceita();
                    
                 }
                 if(evt.getSource() == orcamentoAtualizarGUI.getjButtonVoltar()){
                    //Voltar para a tela do orçamento, usa o mesmo codigo do button voltar do orçamentoGUI
                    Router.getInstance().voltar();
                 }
                 if(evt.getSource() == orcamentoAtualizarGUI.getjButtonSalvar()){
                    //Salvar os dados que estão nos jTextFields como um novo gasto ou uma nova receita
                    salvar();
                 }
            }
        });
        
        
        //Não esqueça de que todos os gastos e receitas devem aparecer nas duas listas
    }
    
    private void atualizaInfo(){
        orcamentoGUI.esconderDialog();
        orcamentoGUI.setMesAno(orcamentoGUI.getSelectedMonthInPopUp(), orcamentoGUI.getSelectedYearInPopUp());
        orcamentoAtualizarGUI.getjTextFieldDescrição().setText("Orçamento " + orcamentoGUI.getSelectedMonthInPopUp() + " " + orcamentoGUI.getSelectedYearInPopUp());
        ArrayList<dominio.Orcamento> orcamentos = orcamentoDAO.list(Integer.parseInt(orcamentoGUI.getSelectedMonthInPopUp()),
                Integer.parseInt(orcamentoGUI.getSelectedYearInPopUp()));
        String gastos="", receita="";
        float saldo = 0f;
        for(dominio.Orcamento o: orcamentos){
            gastos += "- R$ " + o.getCusto() + "\n";
            receita += "- R$ " + o.getRenda() + "\n";
            orcamentoAtualizarGUI.addGasto(o.getId(),o.getCusto());
            orcamentoAtualizarGUI.addReceita(o.getId(),o.getRenda());
            saldo += o.getSaldo();
        }
        orcamentoGUI.setGastosPreview(gastos);
        orcamentoGUI.setReceitaPreview(receita);
        orcamentoGUI.setBalançoPreview("R$ " + saldo);
    }
    
    private void salvar(){
        dominio.Orcamento orcamen = new dominio.Orcamento();
        String descricao, valor, dia, mes, ano;
        descricao = orcamentoAtualizarGUI.getjTextFieldDescrição().getText();
        valor = orcamentoAtualizarGUI.getjTextFieldValor().getText();
        dia = orcamentoAtualizarGUI.getjTextFieldDateDay().getText();
        mes = orcamentoAtualizarGUI.getjTextFieldDateMonth().getText();
        ano = orcamentoAtualizarGUI.getjTextFieldDateYear().getText();
        if(descricao.isEmpty() || valor.isEmpty() || dia.isEmpty() || mes.isEmpty() || ano.isEmpty()){
            orcamentoAtualizarGUI.criarMensagemErro("ERRO", "Preencha todos os campos.");
            return;
        }
        try{
            if(orcamentoAtualizarGUI.getjComboBoxReceitaGasto().getSelectedItem().equals("Receita")){
                orcamen.setRenda(Float.parseFloat(valor));
                orcamen.setAno(Integer.parseInt(ano));
                orcamen.setMes(Integer.parseInt(mes));
                orcamen.setSaldo(Float.parseFloat(valor));
            }else{
                orcamen.setCusto(Float.parseFloat(valor));
                orcamen.setAno(Integer.parseInt(ano));
                orcamen.setMes(Integer.parseInt(mes));
                orcamen.setSaldo(-Float.parseFloat(valor));
            }
        }catch(Exception e){
            orcamentoAtualizarGUI.criarMensagemErro("ERRO", "Preencha apenas números");
            return;
        }
    }
    
    private void removerGastoReceita(){
        try{
            if(!orcamentoAtualizarGUI.getjListGastos().isSelectionEmpty()){
                orcamentoDAO.delete(Integer.parseInt(orcamentoAtualizarGUI.getjListGastos().getSelectedValue().split(" ")[0]));
            }else if(!orcamentoAtualizarGUI.getjListReceitas().isSelectionEmpty()){
                orcamentoDAO.delete(Integer.parseInt(orcamentoAtualizarGUI.getjListReceitas().getSelectedValue().split(" ")[0]));
            }else{
                orcamentoAtualizarGUI.criarMensagemErro("ERRO", "Selecione uma opção");
            }
        }catch(Exception e){
            orcamentoAtualizarGUI.criarMensagemErro("ERRO", "Erro inesperado.");
        }
    }
    
    private void exportarPDF(){
        
        
        Document orcamento=new Document(PageSize.A4);
                    
        try {
            PdfWriter.getInstance(orcamento, new FileOutputStream("Orçamento.pdf"));
            orcamento.open();
            orcamento.add(new Paragraph (orcamentoGUI.getjLabelDataPreview().getText()));
            orcamento.add(new Paragraph (orcamentoGUI.getjLabelReceitaPreview().getText()));
            String spl[] = orcamentoGUI.getjTextAreaReceita().getText().split("\n");
            for(int i=0; i<spl.length;i++){
                orcamento.add(new Paragraph(spl[i]));
            }            
            orcamento.add(new Paragraph (orcamentoGUI.getjLabelGastosPreview().getText()));
            spl = orcamentoGUI.getjTextAreaGastos().getText().split("\n");
            for(int i=0; i<spl.length;i++){
                orcamento.add(new Paragraph(spl[i]));
            }
            
            orcamento.add(new Paragraph ("Balanço: " + orcamentoGUI.getjLabelBalançoValue().getText()));
            orcamento.add(new Paragraph ("Saldo Final: " + orcamentoGUI.getjLabelSaldoFinalValue().getText()));
                         
        } catch (FileNotFoundException | DocumentException ex) {
             System.out.println("Erro"+ex);
                                
        }finally{
            orcamento.close();
        }
        
    }
}
