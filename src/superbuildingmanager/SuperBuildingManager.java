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
        // TODO code application logic here
        
        DBManager.getInstance().connect();
        
        JFrame jFrame = new JFrame("Super Building Manager");
        //jFrame.setLayout(new GridLayout(1,0));
        jFrame.setSize(500, 500);        
        
        Login loginPanel = new Login();
        Menu menuPanel = new Menu();
       
        //loginPanel.setLayout(new GridLayout(1, 0));
        //menuPanel.setLayout(new GridLayout(1, 0));
        
        loginPanel.setSize(500, 500);
        menuPanel.setSize(500, 500);
        
        
        loginPanel.setVisible(true);
        menuPanel.setVisible(false);
        
        jFrame.add(loginPanel);
        jFrame.add(menuPanel);
        
        //Ajusta automaticamente o tamanho da janela, alternativa ao setSize()
        //jFrame.pack();

        //Centraliza a janela
        jFrame.setLocationRelativeTo(null);

        jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jFrame.setVisible(true);
        
    }
    
}
