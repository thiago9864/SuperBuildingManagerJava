/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import dominio.StatusBoleto;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author thiagoalmeida
 */
public class StatusBoletoDAO {
    
    FactoryConnection factoryConn = new FactoryConnection();

    public StatusBoletoDAO() {
    }
    
     /******** CRUD *********/
    
    
    private StatusBoleto buildStatusBoletoObject(ResultSet rs) throws SQLException {
            
        return new StatusBoleto(
            rs.getInt("id"),
            rs.getString("nome"), 
            rs.getString("descricao")
        );            
            
    }
    
    
    /**
     * Metodo que cria uma entrada de status boleto na tabela status_boleto
     * @param statusBoleto (objeto do tipo StatusBoleto)
     * @return (int idInserido ou -1 se der falha)
     */
    public Integer create(StatusBoleto statusBoleto){
        
        Connection conn = factoryConn.getConnection();
        int rowsAffected = 0;
        
        String sql = "INSERT INTO status_boleto (nome, descricao) ";
        sql += "VALUES ($1$, $2$)";
        
        CustomPrepareStatement prepare = new CustomPrepareStatement(sql);

        try {
            
            prepare.setString(1, statusBoleto.getNome());
            prepare.setString(2, statusBoleto.getDescricao());
             
            rowsAffected = prepare.executeUpdate(conn);
            System.out.println("rowsAffected: " + rowsAffected);
             
                
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        // close connection
        factoryConn.closeConnection();
            
        if(rowsAffected > 0){
            //retorna o id do tipo financa criado
            return factoryConn.maxIDFromTable("tipo_financa");
        } else {
            return -1;
        }
    }
    
            

    /**
     * Metodo que obtem os dados do status boleto
     * @param id (int)
     * @return (StatusBoleto)
     */
    public StatusBoleto read(Integer id){
        
        Connection conn = factoryConn.getConnection();
        StatusBoleto objStatusBoleto = null;
        
        String sql = "SELECT id, nome, descricao FROM status_boleto WHERE id=$1$";

        CustomPrepareStatement prepare = new CustomPrepareStatement(sql);
        
        try {
            
            prepare.setInt(1, id);

            ResultSet rs = prepare.executeQuery(conn);
            
            objStatusBoleto = buildStatusBoletoObject(rs);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } 
        
        // close connection
        factoryConn.closeConnection();
        
        return objStatusBoleto;
    }
    
    /**
     * Metodo que lista os status boleto
     * @return (ArrayList StatusBoleto)
     */
    public ArrayList<StatusBoleto> list(){
        
        Connection conn = factoryConn.getConnection();
        ArrayList<StatusBoleto> tipoFinancaArr = new ArrayList<>();
        
        String sql = "SELECT id, nome, descricao FROM status_boleto";
        
        CustomPrepareStatement prepare = new CustomPrepareStatement(sql);
        
        try {
            
            ResultSet rs = prepare.executeQuery(conn);
            
            // loop through the result set
            while (rs.next()) {
                tipoFinancaArr.add(buildStatusBoletoObject(rs));
            }

                
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } 
        
        // close connection
        factoryConn.closeConnection();
        
        return tipoFinancaArr;
    }
    
    
    /**
     * Metodo que atualiza os dados de um status boleto dado o objeto recebido
     * @param statusBoleto (objeto StatusBoleto)
     * @return (true se sucesso, false se erro)
     */
    public boolean update(StatusBoleto statusBoleto){
        
        Connection conn = factoryConn.getConnection();
        int rowsAffected = 0;

        String sql = "";
        sql += "UPDATE status_boleto ";
        sql += "SET nome=$1$, descricao=$2$ ";
        sql += "WHERE id=$3$";
        
        CustomPrepareStatement prepare = new CustomPrepareStatement(sql);
        
        try {
            
            prepare.setString(1, statusBoleto.getNome());
            prepare.setString(2, statusBoleto.getDescricao());
            prepare.setInt(3, statusBoleto.getId());

             
            rowsAffected = prepare.executeUpdate(conn);
            System.out.println("rowsAffected: " + rowsAffected);
           
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        // close connection
        factoryConn.closeConnection();
            
        if(rowsAffected > 0){
            return true;
        }
        return false;
    }
    
    
    /**
     * Metodo que deleta um status boleto dado id fornecido
     * @param id (int)
     * @return (true se excluiu, false se não excluiu)
     */
    public boolean delete(Integer id){
        
        Connection conn = factoryConn.getConnection();
        int rowsAffected = 0;
        
        String sql = "DELETE FROM status_boleto WHERE id=$1$";
        
        CustomPrepareStatement prepare = new CustomPrepareStatement(sql);
        
        try {
            
            prepare.setInt(1, id);
             
            rowsAffected = prepare.executeUpdate(conn);
            System.out.println("rowsAffected: " + rowsAffected);
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        // close connection
        factoryConn.closeConnection();
            
        if(rowsAffected > 0){
            return true;
        }
        return false;
        
    }
}
