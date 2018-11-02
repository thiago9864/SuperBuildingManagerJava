/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package superbuildingmanager;

import javax.swing.JFrame;
import sqlite.DBManager;
import ui.*;

/**
 *
 * @author thiagoalmeida
 * 
 * Links pra pesquisar
 * https://netbeans.org/kb/docs/java/gui-functionality_pt_BR.html
 * https://netbeans.org/kb/docs/java/quickstart-gui_pt_BR.html
 * http://www.sqlitetutorial.net/sqlite-java/select/
 */
public class SuperBuildingManager {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        //conecta no banco
        DBManager.getInstance().connect();
        
        /****** cria telas do aplicativo *******/
        
        //incluir aqui ao criar uma tela nova
        Login loginPanel = new Login();
        MainMenu menuPanel = new MainMenu();
        
        //define as tags pro gerenciador de navegacao
        loginPanel.setTAG("Login");
        menuPanel.setTAG("MainMenu");
     
        /****** cria janela principal onde vão ser abertas as telas *******/
        
        JFrame jFrame = new JFrame("Super Building Manager");
        
        //define tamanho inicial da janela
        jFrame.setSize(1024, 768);  
        
      
        //adiciona as telas na janela principal
        //incluir aqui ao criar uma tela nova
        jFrame.add(loginPanel);
        jFrame.add(menuPanel);
        
        
        /****** cria gerenciador de navegacao *******/
        
        //cria gerenciador de navegacao
        Router router = Router.getInstance();
        router.setWidthJanela(jFrame.getWidth());
        router.setHeightJanela(jFrame.getHeight());
        
        //adiciona telas ao gerenciador de navegacao
        //incluir aqui ao criar uma tela nova
        router.addJPanel(loginPanel);
        router.addJPanel(menuPanel);
        
        //abre a tela de login primeiro
        router.abrir("Login");

        //Centraliza a janela
        jFrame.setLocationRelativeTo(null);
        
        //sinaliza pro programa ser encerrado quando fechar a janela principal
        jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        //faz a janela principal visível
        jFrame.setVisible(true);
        
    }
    
}
