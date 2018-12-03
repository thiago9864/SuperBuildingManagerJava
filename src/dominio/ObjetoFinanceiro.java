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
public class ObjetoFinanceiro {
    private Integer id;
    private Orcamento orcamento;
    private TipoFinanca tipo_financa;
    private float valor;
    private String descricao;

    public ObjetoFinanceiro(Integer id, Orcamento orcamento, TipoFinanca tipo_financa, float valor, String descricao) {
        this.id = id;
        this.orcamento = orcamento;
        this.tipo_financa = tipo_financa;
        this.valor = valor;
        this.descricao = descricao;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Orcamento getOrcamento() {
        return orcamento;
    }

    public void setOrcamento(Orcamento orcamento) {
        this.orcamento = orcamento;
    }

    public TipoFinanca getTipoFinanca() {
        return tipo_financa;
    }

    public void setTipoFinanca(TipoFinanca tipo_financa) {
        this.tipo_financa = tipo_financa;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

     
    
}
