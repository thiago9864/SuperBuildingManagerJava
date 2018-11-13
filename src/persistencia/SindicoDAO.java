/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import dominio.Sindico;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author thiagoalmeida
 */
public class SindicoDAO {
    FactoryConnection factoryConn = new FactoryConnection();

    public SindicoDAO() {
    }
    
    /**
     * Metodo pra checar se os dados de login são válidos
     * @param usuario
     * @param senha
     * @return boolean
     */
    public boolean checarCredenciais(String usuario, String senha){
        
        //http://www.sqlitetutorial.net/sqlite-java/select/
        
        String sql = "SELECT * FROM sindico WHERE usuario='"+usuario+"' AND senha='" + senha + "'";
        boolean resultado = false;
        Statement stmt;
        Connection conn = factoryConn.getConnection();
        
        try {
             stmt  = conn.createStatement();
             System.out.println("SQL: " + sql);
             ResultSet rs = stmt.executeQuery(sql);
            
            // loop through the result set
            while (rs.next()) {
                /*String u = rs.getString("usuario");
                String s = rs.getString("senha");
                System.out.println(rs.getString("id"));
                System.out.println(u);
                System.out.println(s);*/

                resultado = true;
                System.out.println("valido!");
            }
            
            // close statement
            stmt.close();
                
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } 
        
        
        factoryConn.closeConnection();
        
        return resultado;
    }
    
    
    
    
    /******** CRUD *********/
    
    
    
    
    /**
     * Metodo que cria uma entrada de síndico na tabela síndico
     * @param sindico (objeto do tipo Sindico)
     * @return (int idInserido ou -1 se der falha)
     */
    public Integer createSindico(Sindico sindico){
        
        Integer newID = factoryConn.maxIDFromTable("sindico") + 1;
        String sql = "INSERT INTO sindico (id, nome, cpf, telefone, email, usuario, senha, condominio_id) ";
        sql += "VALUES ($1, '$2', '$3', '$4', '$5', '$6', '$7', $8)";
        
        sql = sql.replace("$1", newID.toString());
        sql = sql.replace("$2", sindico.getNome());
        sql = sql.replace("$3", sindico.getCpf());
        sql = sql.replace("$4", sindico.getTelefone());
        sql = sql.replace("$5", sindico.getEmail());
        sql = sql.replace("$6", sindico.getLogin());
        sql = sql.replace("$7", sindico.getSenha());
        sql = sql.replace("$8", sindico.getCondominioId().toString());
        
        Sindico sindicoObj = null;
        Statement stmt;
        int rowsAffected = 0;
        Connection conn = factoryConn.getConnection();
        
        try {
            stmt  = conn.createStatement();
            System.out.println("SQL: " + sql);
             
            rowsAffected = stmt.executeUpdate(sql);
            System.out.println("rowsAffected: " + rowsAffected);
             
            // close statement
            stmt.close();
                
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        if(rowsAffected > 0){
            return newID;
        } else {
            return -1;
        }
    }
    
    /**
     * Metodo que obtem os dados do sindico indicado pelo parametro idSindico
     * @param idSindico (int)
     * @return (objeto Sindico)
     */
    public Sindico readSindico(Integer idSindico){
        String sql = "SELECT * FROM sindico WHERE id=$1";
        Sindico sindicoObj = null;
        Statement stmt;
        Connection conn = factoryConn.getConnection();
        
        sql = sql.replace("$1", idSindico.toString());
        
        try {
             stmt  = conn.createStatement();
             System.out.println("SQL: " + sql);
             ResultSet rs = stmt.executeQuery(sql);
             
             sindicoObj = new Sindico(
                    rs.getInt("id"),
                    rs.getInt("condominio_id"),
                    rs.getString("nome"),
                    rs.getString("cpf"),
                    rs.getString("telefone"),
                    rs.getString("email"),
                    rs.getString("usuario"),
                    rs.getString("senha")
             );

            // close statement
            stmt.close();
                
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } 
        
        return sindicoObj;
    }
    
    /**
     * Metodo que atualiza os dados de um sindico dado o objeto recebido
     * @param sindico (objeto Sindico)
     * @return (true se sucesso, false se erro)
     */
    public boolean updateSindico(Sindico sindico){
        
        String sql = "";
        
        sql += "UPDATE sindico ";
        sql += "SET nome='$1', cpf='$2', telefone='$3', email='$4', usuario='$5', senha='$6', condominio_id=$7 ";
        sql += "WHERE id=$8";
        
        sql = sql.replace("$1", sindico.getNome());
        sql = sql.replace("$2", sindico.getCpf());
        sql = sql.replace("$3", sindico.getTelefone());
        sql = sql.replace("$4", sindico.getEmail());
        sql = sql.replace("$5", sindico.getLogin());
        sql = sql.replace("$6", sindico.getSenha());
        sql = sql.replace("$7", sindico.getCondominioId().toString());
        sql = sql.replace("$8", sindico.getId().toString());
        
        Sindico sindicoObj = null;
        Statement stmt;
        int rowsAffected = 0;
        Connection conn = factoryConn.getConnection();
        
        try {
            stmt  = conn.createStatement();
            System.out.println("SQL: " + sql);
             
            rowsAffected = stmt.executeUpdate(sql);
            System.out.println("rowsAffected: " + rowsAffected);
            
            // close statement
            stmt.close();
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        if(rowsAffected > 0){
            return true;
        }
        return false;
    }
    
    /**
     * Metodo que deleta um sindico dado id fornecido
     * @param idSindico (int)
     * @return (true se excluiu, false se não excluiu)
     */
    public boolean deleteSindico(Integer idSindico){
        
        String sql = "";
        
        sql += "DELETE FROM sindico WHERE id=$1";
        
        sql = sql.replace("$1", idSindico.toString());
        
        Sindico sindicoObj = null;
        Statement stmt;
        int rowsAffected = 0;
        Connection conn = factoryConn.getConnection();
        
        try {
            stmt  = conn.createStatement();
            System.out.println("SQL: " + sql);
             
            rowsAffected = stmt.executeUpdate(sql);
            System.out.println("rowsAffected: " + rowsAffected);
            
            // close statement
            stmt.close();
                
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        if(rowsAffected > 0){
            return true;
        }
        return false;
        
    }
}
