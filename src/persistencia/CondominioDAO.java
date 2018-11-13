/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import dominio.Condominio;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author thiagoalmeida
 */
public class CondominioDAO {
    
    FactoryConnection factoryConn = new FactoryConnection();

    public CondominioDAO() {
    }
    
    
    
    
    /******** CRUD *********/
    
    
    
    
    /**
     * Metodo que cria uma entrada de condominio na tabela condominio
     * @param condominio (objeto do tipo Condominio)
     * @return (int idInserido ou -1 se der falha)
     */
    public Integer create(Condominio condominio){
        
        Connection conn = factoryConn.getConnection();
        Condominio condominioObj = null;
        int rowsAffected = 0;
        
        String sql = "INSERT INTO condominio (nome, cnpj, telefone, endereco, numero, cidade, estado, cep, valor_aluguel) ";
        sql += "VALUES ($1, $2, $3, $4, $5, $6, $7, $8, $9)";
        
        CustomPrepareStatement prepare = new CustomPrepareStatement(sql);

        try {
            
            prepare.setString(1, condominio.getNome());
            prepare.setString(2, condominio.getCnpj());
            prepare.setString(3, condominio.getTelefone());
            prepare.setString(4, condominio.getEndereco());
            prepare.setString(5, condominio.getNumero());
            prepare.setString(6, condominio.getCidade());
            prepare.setString(7, condominio.getEstado());
            prepare.setInt(8, condominio.getCep());
            prepare.setFloat(9, condominio.getValor_aluguel());
             
            rowsAffected = prepare.executeUpdate(conn);
            System.out.println("rowsAffected: " + rowsAffected);
             
            // close connection
            factoryConn.closeConnection();
                
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        if(rowsAffected > 0){
            //retorna o id do condominio criado
            return factoryConn.maxIDFromTable("condominio");
        } else {
            return -1;
        }
    }
    

    /**
     * Metodo que obtem os dados do condominio indicado pelo parametro idCondominio
     * @param id (int)
     * @return (objeto Condominio)
     */
    public Condominio read(Integer id){
        
        Connection conn = factoryConn.getConnection();
        Condominio condominioObj = null;
        
        String sql = "SELECT * FROM condominio WHERE id=$1";
        
        CustomPrepareStatement prepare = new CustomPrepareStatement(sql);
        
        try {
            
            prepare.setInt(1, id);

            ResultSet rs = prepare.executeQuery(conn);
            
            
            condominioObj = new Condominio(
                    rs.getInt("id"),
                    rs.getString("cnpj"),
                    rs.getString("nome"),                    
                    rs.getString("telefone"),
                    rs.getString("endereco"),
                    rs.getString("numero"),
                    rs.getString("cidade"),
                    rs.getString("estado"),
                    rs.getInt("cep"),
                    rs.getFloat("valor_aluguel")
            );

            // close connection
            factoryConn.closeConnection();
                
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } 
        
        return condominioObj;
    }
    
    
    /**
     * Metodo que atualiza os dados de um condominio dado o objeto recebido
     * @param condominio (objeto Condominio)
     * @return (true se sucesso, false se erro)
     */
    public boolean update(Condominio condominio){
        
        Connection conn = factoryConn.getConnection();
        int rowsAffected = 0;
        
        String sql = "";
        sql += "UPDATE condominio ";
        sql += "SET nome=$1, cnpj=$2, telefone=$3, endereco=$4, numero=$5, cidade=$6, estado=$7, cep=$8, valor_aluguel=$9 ";
        sql += "WHERE id=$10";
        
        CustomPrepareStatement prepare = new CustomPrepareStatement(sql);
        
        try {
            
            prepare.setInt(10, condominio.getId());
            prepare.setString(1, condominio.getNome());
            prepare.setString(2, condominio.getCnpj());
            prepare.setString(3, condominio.getTelefone());
            prepare.setString(4, condominio.getEndereco());
            prepare.setString(5, condominio.getNumero());
            prepare.setString(6, condominio.getCidade());
            prepare.setString(7, condominio.getEstado());
            prepare.setInt(8, condominio.getCep());
            prepare.setFloat(9, condominio.getValor_aluguel());            
             
            rowsAffected = prepare.executeUpdate(conn);
            System.out.println("rowsAffected: " + rowsAffected);
            
            // close connection
            factoryConn.closeConnection();
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        if(rowsAffected > 0){
            return true;
        }
        return false;
    }
    
                                /*
        "id" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
  "nome" TEXT,
  "cnpj" TEXT,
  "telefone" TEXT,
  "endereco" TEXT,
  "numero" TEXT,
  "cidade" TEXT,
  "estado" TEXT,
  "cep" INTEGER,
  "valor_aluguel" REAL
        */
    
    /**
     * Metodo que deleta um condominio dado id fornecido
     * @param id (int)
     * @return (true se excluiu, false se não excluiu)
     */
    public boolean delete(Integer id){
        
        Connection conn = factoryConn.getConnection();
        int rowsAffected = 0;
        
        String sql = "DELETE FROM condominio WHERE id=$1";
        
        CustomPrepareStatement prepare = new CustomPrepareStatement(sql);
        
        try {
            
            prepare.setInt(1, id);
             
            rowsAffected = prepare.executeUpdate(conn);
            System.out.println("rowsAffected: " + rowsAffected);
            
            // close connection
            factoryConn.closeConnection();
                
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        if(rowsAffected > 0){
            return true;
        }
        return false;
        
    }
}
