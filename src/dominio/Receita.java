/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

import java.util.Date;

/**
 *
 * @author thiagoalmeida
 */
public class Receita extends ObjetoFinanceiro {
    private Date dataRecebimento;
    private Boleto boleto;

    public Receita(Date dataRecebimento, float valor, String descricao) {
        super(valor, descricao);
        this.dataRecebimento = dataRecebimento;
    }

    public Date getDataRecebimento() {
        return dataRecebimento;
    }

    public void setDataRecebimento(Date dataRecebimento) {
        this.dataRecebimento = dataRecebimento;
    }

    public Boleto getBoleto() {
        return boleto;
    }

    public void setBoleto(Boleto boleto) {
        this.boleto = boleto;
    }
}
