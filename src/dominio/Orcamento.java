/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

/**
 *
 * @author thiagoalmeida
 */
public class Orcamento {
    
    private Integer id;
    private Integer mes;
    private Integer ano;
    private Float custo;
    private Float renda;
    private Float saldo; 

    public Orcamento(Integer id, Integer mes, Integer ano, Float custo, Float renda, Float saldo) {
        this.id = id;
        this.mes = mes;
        this.ano = ano;
        this.custo = custo;
        this.renda = renda;
        this.saldo = saldo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMes() {
        return mes;
    }

    public void setMes(Integer mes) {
        this.mes = mes;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public Float getCusto() {
        return custo;
    }

    public void setCusto(Float custo) {
        this.custo = custo;
    }

    public Float getRenda() {
        return renda;
    }

    public void setRenda(Float renda) {
        this.renda = renda;
    }

    public Float getSaldo() {
        return saldo;
    }

    public void setSaldo(Float saldo) {
        this.saldo = saldo;
    }

}
