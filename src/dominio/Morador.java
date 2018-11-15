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
public class Morador {
    
    private Integer id;
    private Condominio condominio;
    private String nome;
    private String telefone;
    private String email;
    private String cpf;
    private Integer bloco;
    private Integer andar;
    private Integer apartamento;

    public Morador(Integer id, Condominio condominio) {
        this.id = id;
        this.condominio = condominio;
    }

    public Morador(Integer id, Condominio condominio, String nome, String telefone, String email, String cpf, Integer bloco, Integer andar, Integer apartamento) {
        this.id = id;
        this.condominio = condominio;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.cpf = cpf;
        this.bloco = bloco;
        this.andar = andar;
        this.apartamento = apartamento;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Condominio getCondominio() {
        return condominio;
    }

    public void setCondominio(Condominio condominio) {
        this.condominio = condominio;
    }

    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Integer getBloco() {
        return bloco;
    }

    public void setBloco(Integer bloco) {
        this.bloco = bloco;
    }

    public Integer getAndar() {
        return andar;
    }

    public void setAndar(Integer andar) {
        this.andar = andar;
    }

    public Integer getApartamento() {
        return apartamento;
    }

    public void setApartamento(Integer apartamento) {
        this.apartamento = apartamento;
    }
    
    

}
