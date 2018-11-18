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
public class Gasto extends ObjetoFinanceiro {
    private Date dataPagamento;

    public Gasto(Date dataPagamento, Integer id, Orcamento orcamento, TipoFinanca tipo_financa, float valor, String descricao) {
        super(id, orcamento, tipo_financa, valor, descricao);
        this.dataPagamento = dataPagamento;
    }

    public Date getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(Date dataPagamento) {
        this.dataPagamento = dataPagamento;
    }
}
