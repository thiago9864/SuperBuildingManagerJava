/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import javax.swing.JFrame;

/**
 *
 * @author thiagoalmeida
 * Classe construida com padrão Singleton
 * Info: https://www.devmedia.com.br/padrao-de-projeto-singleton-em-java/26392
 * Essa clase faz o roteamento das telas no programa, cuidando de fazer a tela
 * selecionada pela tag visivel e as outras invisiveis
 */


public class Router {
	
    private static Router instance;
    private ArrayList<CustomJPanel> jpanelList = new ArrayList<>();
    private int widthJanela = 1024, heightJanela = 768;
    private Stack<CustomJPanel> pilha = new Stack<>();
    private JFrame jFrame;
    
    private Router(){}
    
    //singleton pattern
    public static Router getInstance(){
        if(instance == null){
            instance = new Router();
        }
        return instance;
    }

    public int getWidthJanela() {
        return widthJanela;
    }

    public void setWidthJanela(int widthJanela) {
        this.widthJanela = widthJanela;
    }

    public int getHeightJanela() {
        return heightJanela;
    }

    public void setHeightJanela(int heightJanela) {
        this.heightJanela = heightJanela;
    }

    public void setjFrame(JFrame jFrame) {
        this.jFrame = jFrame;
    }
    
    
    
    /**
     * Função que adiciona uma GUI ao gerenciador de navegação
     * Não permite adicionar duas vezes a mesma GUI
     * @param painel (a GUI)
     */
    public void addJPanel(CustomJPanel painel){
        
        for(CustomJPanel p : jpanelList){
            if(p.compareTag(painel.getTAG())){
                System.out.println("Não é permitido adicionar duas vezes a mesma gui");
                return;
            }
        }
        
        jFrame.add(painel);
        jpanelList.add(painel);
    }
    
    /**
     * Função que chamada, abre uma tela pelo nome da tela
     * @param tag 
     */
    public void abrir(String TAG){
        for(CustomJPanel p : jpanelList){
            //mantem dimensoes dos paineis a mesma da janela principal
            p.setSize(widthJanela, heightJanela);
            
            //procura painel com a mesma tag e faz ele visivel
            if(p.compareTag(TAG)){
                p.setVisible(true);
                //adiciona na pilha a janela aberta
                pilha.add(p);
            } else {
                p.setVisible(false);
            }
        }
    }
    
    /**
     * Função pra fazer a ação do botão de voltar
     */
    public void voltar(){
        
        //não volta se a pilha estiver vazia
        if(pilha.isEmpty()){
            return;
        }
        
        //se só tiver duas telas, essas duas vão ser Login e MainMenu
        //e não pode voltar mais além da MainMenu
        if(pilha.size() <= 2){
            return;
        }
        
        //retira da pilha a tela atual
        pilha.pop();
        
        //retira da pilha a tela pra voltar
        CustomJPanel telaAbrir = pilha.pop();       
        
        //abre a tela pra voltar
        abrir(telaAbrir.getTAG());
    }
   
}