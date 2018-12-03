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
    private Integer boleto_id;

    public Receita(Date dataRecebimento, Integer boleto, Integer id, Orcamento orcamento, TipoFinanca tipo_financa, float valor, String descricao) {
        super(id, orcamento, tipo_financa, valor, descricao);
        this.dataRecebimento = dataRecebimento;
        this.boleto_id = boleto_id;
    }
    
    public Date getDataRecebimento() {
        return dataRecebimento;
    }

    public void setDataRecebimento(Date dataRecebimento) {
        this.dataRecebimento = dataRecebimento;
    }

    public Integer getBoletoId() {
        return boleto_id;
    }

    public void setBoletoId(Integer boleto_id) {
        this.boleto_id = boleto_id;
    }
}
