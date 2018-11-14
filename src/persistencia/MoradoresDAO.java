/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import dominio.Morador;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author thiagoalmeida
 */
public class MoradoresDAO {
    
    FactoryConnection factoryConn = new FactoryConnection();

    public MoradoresDAO() {
    }
    
    
    
    
    /******** CRUD *********/
    
    
    
    
    /**
     * Metodo que cria uma entrada de morador na tabela morador
     * @param morador (objeto do tipo Morador)
     * @return (int idInserido ou -1 se der falha)
     */
    public Integer create(Morador morador){
        
        Connection conn = factoryConn.getConnection();
        Integer newID = factoryConn.maxIDFromTable("morador") + 1;
        int rowsAffected = 0;
        
        String sql = "INSERT INTO morador (id, condominio_id, nome, telefone, email, cpf, bloco, andar, apartamento) ";
        sql += "VALUES ($1, $2, $3, $4, $5, $6, $7, $8, $9)";
        
        CustomPrepareStatement prepare = new CustomPrepareStatement(sql);

        try {
            
            prepare.setInt(1, newID);
            prepare.setInt(2, morador.getCondominioId());
            prepare.setString(3, morador.getNome());
            prepare.setString(4, morador.getTelefone());
            prepare.setString(5, morador.getEmail());
            prepare.setString(6, morador.getCpf());
            prepare.setInt(7, morador.getBloco());
            prepare.setInt(8, morador.getAndar());
            prepare.setInt(9, morador.getApartamento());
             
            rowsAffected = prepare.executeUpdate(conn);
            System.out.println("rowsAffected: " + rowsAffected);
             
            // close connection
            factoryConn.closeConnection();
                
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        if(rowsAffected > 0){
            //retorna o id do condominio criado
            return newID;
        } else {
            return -1;
        }
    }
    
            

    /**
     * Metodo que obtem os dados do morador
     * @param id (int)
     * @return (ArrayList Morador)
     */
    public Morador read(Integer id){
        
        Connection conn = factoryConn.getConnection();
        Morador objMorador = null;
        String sql = "SELECT * FROM morador WHERE id=$1";
        
        CustomPrepareStatement prepare = new CustomPrepareStatement(sql);
        
        try {
            
            prepare.setInt(1, id);

            ResultSet rs = prepare.executeQuery(conn);
            
            objMorador = new Morador(
                rs.getInt("id"),
                rs.getInt("condominio_id"),
                rs.getString("nome"), 
                rs.getString("telefone"),
                rs.getString("email"),
                rs.getString("cpf"),
                rs.getInt("bloco"),
                rs.getInt("andar"),
                rs.getInt("apartamento")
            );


            // close connection
            factoryConn.closeConnection();
                
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } 
        
        return objMorador;
    }
    
    /**
     * Metodo que lista os moradores de um determinado bloco
     * @param bloco (int)
     * @return (ArrayList Morador)
     */
    public ArrayList<Morador> list(Integer bloco){
        
        Connection conn = factoryConn.getConnection();
        ArrayList<Morador> moradorArr = new ArrayList<>();
        
        String sql = "SELECT * FROM morador WHERE bloco=$1";
        
        CustomPrepareStatement prepare = new CustomPrepareStatement(sql);
        
        try {
            
            prepare.setInt(1, bloco);

            ResultSet rs = prepare.executeQuery(conn);
            
            // loop through the result set
            while (rs.next()) {
                moradorArr.add(new Morador(
                    rs.getInt("id"),
                    rs.getInt("condominio_id"),
                    rs.getString("nome"), 
                    rs.getString("telefone"),
                    rs.getString("email"),
                    rs.getString("cpf"),
                    rs.getInt("bloco"),
                    rs.getInt("andar"),
                    rs.getInt("apartamento")
                ));
            }

            // close connection
            factoryConn.closeConnection();
                
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } 
        
        return moradorArr;
    }
    
    
    /**
     * Metodo que atualiza os dados de um morador dado o objeto recebido
     * @param morador (objeto Morador)
     * @return (true se sucesso, false se erro)
     */
    public boolean update(Morador morador){
        
        Connection conn = factoryConn.getConnection();
        int rowsAffected = 0;

        String sql = "";
        sql += "UPDATE morador ";
        sql += "SET condominio_id=$1, nome=$2, telefone=$3, email=$4, cpf=$5, bloco=$6, andar=$7, apartamento=$8 ";
        sql += "WHERE id=$9";
        
        CustomPrepareStatement prepare = new CustomPrepareStatement(sql);
        
        try {
            
            prepare.setInt(1, morador.getCondominioId());
            prepare.setString(2, morador.getNome());
            prepare.setString(3, morador.getTelefone());
            prepare.setString(4, morador.getEmail());
            prepare.setString(5, morador.getCpf());
            prepare.setInt(6, morador.getBloco());
            prepare.setInt(7, morador.getAndar());
            prepare.setInt(8, morador.getApartamento());    
            prepare.setInt(9, morador.getId());
             
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
    
    
    /**
     * Metodo que deleta um morador dado id fornecido
     * @param id (int)
     * @return (true se excluiu, false se não excluiu)
     */
    public boolean delete(Integer id){
        
        Connection conn = factoryConn.getConnection();
        int rowsAffected = 0;
        
        String sql = "DELETE FROM morador WHERE id=$1";
        
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
