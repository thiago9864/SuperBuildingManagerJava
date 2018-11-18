/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

/**
 *
 * @author Thiago
 */
public class TipoFinanca {
    private Integer id;
    private String nome;
    private String descricao;
    private boolean is_renda;

    public TipoFinanca(Integer id, boolean is_renda) {
        this.id = id;
        this.is_renda = is_renda;
    }

    public TipoFinanca(Integer id, String nome, String descricao, boolean is_renda) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.is_renda = is_renda;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public boolean isRenda() {
        return is_renda;
    }

    public void setIsRenda(boolean is_renda) {
        this.is_renda = is_renda;
    }
    
    
}
