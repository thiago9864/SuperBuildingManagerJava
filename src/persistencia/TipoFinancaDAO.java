/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import dominio.TipoFinanca;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author thiagoalmeida
 */
public class TipoFinancaDAO {
    
    FactoryConnection factoryConn = new FactoryConnection();

    public TipoFinancaDAO() {
    }
    
    /******** CRUD *********/
    
    
    private TipoFinanca buildTipoFinancaObject(ResultSet rs) throws SQLException {
            
        return new TipoFinanca(
            rs.getInt("id"),
            rs.getString("nome"), 
            rs.getString("descricao"),
            rs.getBoolean("is_renda")
        );            
            
    }
    
    
    /**
     * Metodo que cria uma entrada de tipo financa na tabela tipo_financa
     * @param tipoFinanca (objeto do tipo TipoFinanca)
     * @return (int idInserido ou -1 se der falha)
     */
    public Integer create(TipoFinanca tipoFinanca){
        
        Connection conn = factoryConn.getConnection();
        int rowsAffected = 0;
        
        String sql = "INSERT INTO tipo_financa (nome, descricao, is_renda) ";
        sql += "VALUES ($1$, $2$, $3$)";
        
        CustomPrepareStatement prepare = new CustomPrepareStatement(sql);

        try {
            
            prepare.setString(1, tipoFinanca.getNome());
            prepare.setString(2, tipoFinanca.getDescricao());
            prepare.setBoolean(3, tipoFinanca.isRenda());
             
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
     * Metodo que obtem os dados do morador
     * @param id (int)
     * @return (TipoFinanca)
     */
    public TipoFinanca read(Integer id){
        
        Connection conn = factoryConn.getConnection();
        TipoFinanca objTipoFinanca = null;
        
        String sql = "SELECT id, nome, descricao, is_renda FROM tipo_financa WHERE id=$1$";

        CustomPrepareStatement prepare = new CustomPrepareStatement(sql);
        
        try {
            
            prepare.setInt(1, id);

            ResultSet rs = prepare.executeQuery(conn);
            
            objTipoFinanca = buildTipoFinancaObject(rs);


                
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } 
        
        // close connection
        factoryConn.closeConnection();
        
        return objTipoFinanca;
    }
    
    /**
     * Metodo que lista os tipos financa
     * @return (ArrayList TipoFinanca)
     */
    public ArrayList<TipoFinanca> list(){
        
        Connection conn = factoryConn.getConnection();
        ArrayList<TipoFinanca> tipoFinancaArr = new ArrayList<>();
        
        String sql = "SELECT id, nome, descricao, is_renda FROM tipo_financa";
        
        CustomPrepareStatement prepare = new CustomPrepareStatement(sql);
        
        try {
            
            ResultSet rs = prepare.executeQuery(conn);
            
            // loop through the result set
            while (rs.next()) {
                tipoFinancaArr.add(buildTipoFinancaObject(rs));
            }

                
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } 
        
        // close connection
        factoryConn.closeConnection();
        
        return tipoFinancaArr;
    }
    
    
    /**
     * Metodo que atualiza os dados de um tipo financa dado o objeto recebido
     * @param tipoFinanca (objeto TipoFinanca)
     * @return (true se sucesso, false se erro)
     */
    public boolean update(TipoFinanca tipoFinanca){
        
        Connection conn = factoryConn.getConnection();
        int rowsAffected = 0;

        String sql = "";
        sql += "UPDATE tipo_financa ";
        sql += "SET nome=$1$, descricao=$2$, is_renda=$3$ ";
        sql += "WHERE id=$4$";
        
        CustomPrepareStatement prepare = new CustomPrepareStatement(sql);
        
        try {
            
            prepare.setString(1, tipoFinanca.getNome());
            prepare.setString(2, tipoFinanca.getDescricao());
            prepare.setBoolean(3, tipoFinanca.isRenda());
            prepare.setInt(4, tipoFinanca.getId());

             
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
     * Metodo que deleta um morador dado id fornecido
     * @param id (int)
     * @return (true se excluiu, false se não excluiu)
     */
    public boolean delete(Integer id){
        
        Connection conn = factoryConn.getConnection();
        int rowsAffected = 0;
        
        String sql = "DELETE FROM tipo_financa WHERE id=$1$";
        
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
