/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import persistencia.FactoryConnection;
import gui.Login;
import gui.Router;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JOptionPane;
import persistencia.SindicoDAO;

/**
 *
 * @author thiagoalmeida
 */
public class LoginController {
    private Login loginGUI;
    private SindicoDAO sindicoDAO;

    public LoginController() {
        //cria gui
        loginGUI = new Login();
        loginGUI.setTAG("Login");
        
        sindicoDAO = new SindicoDAO();
        
        //adiciona gui ao gerenciador de navegacao
        Router.getInstance().addJPanel(loginGUI);
        
        loginGUI.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                 if(evt.getSource() == loginGUI.getBtnEntrar()){
                     fazerLogin(
                        loginGUI.getTxtLogin().getText(), 
                        String.valueOf(loginGUI.getTxtSenha().getPassword())
                     );
                 }
            }
        });
        
        loginGUI.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
               
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER) {
                     fazerLogin(
                        loginGUI.getTxtLogin().getText(), 
                        String.valueOf(loginGUI.getTxtSenha().getPassword())
                     );
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                
            }
            
        });
    }
    
    private void fazerLogin(String usuario, String senha){

        //se algum campo estiver vazio, nem checa o login
        if(usuario.isEmpty() || senha.isEmpty()){
            loginGUI.criarMensagemErro("Erro", "Digite os dados de login");
            return;
        }

        //acessa o banco
        boolean check = sindicoDAO.checarCredenciais(
                usuario, 
                senha
        );
        
        if(check){
            //esta logado
            //muda pra tela MainMenu
            Router.getInstance().abrir("MainMenu");
        } else {
            //login ou senha errados
            loginGUI.criarMensagemErro("Erro", "Usuario ou senha incorretos");
        }
        
    }
    
}
