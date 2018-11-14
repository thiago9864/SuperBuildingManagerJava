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
public class Sindico {
    private Integer id;
    private Condominio condominio;
    private String nome;
    private String cpf;
    private String telefone;
    private String email;
    private String login;
    private String senha;

    public Sindico() {}

    public Sindico(Integer id, Condominio condominio) {
        this.id = id;
        this.condominio = condominio;
    }

    public Sindico(Integer id, Condominio condominio, String nome, String cpf, String telefone, String email, String login, String senha) {
        this.id = id;
        this.condominio = condominio;
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.email = email;
        this.login = login;
        this.senha = senha;
    }

    public Sindico(Integer id, Condominio condominio, String nome, String cpf, String telefone, String email) {
        this.id = id;
        this.condominio = condominio;
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.email = email;
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

}

