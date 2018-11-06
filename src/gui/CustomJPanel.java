/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;
import javax.swing.JPanel;

/**
 *
 * @author thiagoalmeida
 */
public class CustomJPanel extends JPanel {
    private String TAG = "";
    
    public CustomJPanel(){}

    public String getTAG() {
        return TAG;
    }

    public void setTAG(String TAG) {
        this.TAG = TAG;
    }
    
    public boolean compareTag(String TAG){
        return this.TAG.toUpperCase().equals(TAG.toUpperCase());
    }
}
