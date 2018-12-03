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
public class Boleto {
    private Integer id;
    private Morador morador;
    private StatusBoleto statusBoleto;
    private String banco;
    private String codigo;
    private Float valor;
    private Float juros;
    private Float desconto;
    private Float multa;
    private Date data_vencimento;
    private boolean is_segunda_via;

    public Boleto(Integer id, Morador morador, StatusBoleto statusBoleto, String banco, String codigo, Float valor, Float juros, Float desconto, Float multa, Date data_vencimento, boolean is_segunda_via) {
        this.id = id;
        this.morador = morador;
        this.statusBoleto = statusBoleto;
        this.banco = banco;
        this.codigo = codigo;
        this.valor = valor;
        this.juros = juros;
        this.desconto = desconto;
        this.multa = multa;
        this.data_vencimento = data_vencimento;
        this.is_segunda_via = is_segunda_via;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Morador getMorador() {
        return morador;
    }

    public void setMorador(Morador morador) {
        this.morador = morador;
    }

    public StatusBoleto getStatusBoleto() {
        return statusBoleto;
    }

    public void setStatusBoleto(StatusBoleto statusBoleto) {
        this.statusBoleto = statusBoleto;
    }

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Float getValor() {
        return valor;
    }

    public void setValor(Float valor) {
        this.valor = valor;
    }

    public Float getJuros() {
        return juros;
    }

    public void setJuros(Float juros) {
        this.juros = juros;
    }

    public Float getDesconto() {
        return desconto;
    }

    public void setDesconto(Float desconto) {
        this.desconto = desconto;
    }

    public Float getMulta() {
        return multa;
    }

    public void setMulta(Float multa) {
        this.multa = multa;
    }

    public Date getDataVencimento() {
        return data_vencimento;
    }

    public void setDataVencimento(Date data_vencimento) {
        this.data_vencimento = data_vencimento;
    }

    public boolean isSegundaVia() {
        return is_segunda_via;
    }

    public void setIsSegundaVia(boolean is_segunda_via) {
        this.is_segunda_via = is_segunda_via;
    }
    
    
    
}
