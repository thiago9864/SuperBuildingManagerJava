/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import controller.*;
import gui.Router;
import javax.swing.JFrame;

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
       
        /****** cria janela principal onde vão ser abertas as gui *******/
        
        JFrame jFrame = new JFrame("Super Building Manager");
        
        //define tamanho inicial da janela
        jFrame.setSize(1024, 768);  
        
        
        /****** cria gerenciador de navegacao *******/
        
        //cria gerenciador de navegacao
        Router router = Router.getInstance();
        router.setWidthJanela(jFrame.getWidth());
        router.setHeightJanela(jFrame.getHeight());
        router.setjFrame(jFrame);
        
        /****** instancia controllers do aplicativo *******/
        
        LoginController loginController = new LoginController();
        MainMenuController mainMenuController = new MainMenuController();
       
        
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
