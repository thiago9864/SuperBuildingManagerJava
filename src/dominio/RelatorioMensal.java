/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

import java.util.ArrayList;

/**
 *
 * @author thiagoalmeida
 */
public class RelatorioMensal {
    private ArrayList<ObjetoFinanceiro> infoFin;
    private String infoData;

    public ArrayList<ObjetoFinanceiro> getInfoFin() {
        return infoFin;
    }

    public void setInfoFin(ArrayList<ObjetoFinanceiro> infoFin) {
        this.infoFin = infoFin;
    }

    public String getInfoData() {
        return infoData;
    }

    public void setInfoData(String infoData) {
        this.infoData = infoData;
    }
    
    

    public void gerarPFD(){
        
    }
    public void verHistorico(int idOrcamento, int mes, int ano){

    }

}
