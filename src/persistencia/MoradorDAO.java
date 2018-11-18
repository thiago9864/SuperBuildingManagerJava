/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import dominio.Condominio;
import dominio.Morador;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author thiagoalmeida
 */
public class MoradorDAO {
    
    FactoryConnection factoryConn = new FactoryConnection();

    public MoradorDAO() {
    }
    
    
    
    
    /******** CRUD *********/
    
    
    private Morador buildMoradorObject(ResultSet rs) throws SQLException {
            Condominio condominioObj = new Condominio(
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
        
            
            return new Morador(
                rs.getInt("id"),
                condominioObj,
                rs.getString("nome"), 
                rs.getString("telefone"),
                rs.getString("email"),
                rs.getString("cpf"),
                rs.getInt("bloco"),
                rs.getInt("andar"),
                rs.getInt("apartamento")
            );
            
            
    }
    
    
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
        sql += "VALUES ($1$, $2$, $3$, $4$, $5$, $6$, $7$, $8$, $9$)";
        
        CustomPrepareStatement prepare = new CustomPrepareStatement(sql);

        try {
            
            prepare.setInt(1, newID);
            prepare.setInt(2, morador.getCondominio().getId());
            prepare.setString(3, morador.getNome());
            prepare.setString(4, morador.getTelefone());
            prepare.setString(5, morador.getEmail());
            prepare.setString(6, morador.getCpf());
            prepare.setInt(7, morador.getBloco());
            prepare.setInt(8, morador.getAndar());
            prepare.setInt(9, morador.getApartamento());
             
            rowsAffected = prepare.executeUpdate(conn);
            System.out.println("rowsAffected: " + rowsAffected);
                
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        // close connection
        factoryConn.closeConnection();
            
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
        String sql = "SELECT m.id, m.nome, m.telefone, m.email, m.cpf, m.bloco, m.andar, m.apartamento, ";
        sql += "c.id as condominio_id, c.cnpj, c.nome as nome_c, c.telefone as telefone_c, c.endereco, c.numero, c.cidade, c.estado, c.cep, c.valor_aluguel ";
        sql += "FROM morador m, condominio c WHERE c.id=m.condominio_id AND m.id=$1$";
        
        CustomPrepareStatement prepare = new CustomPrepareStatement(sql);
        
        try {
            
            prepare.setInt(1, id);

            ResultSet rs = prepare.executeQuery(conn);
            
            objMorador = buildMoradorObject(rs);

     
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } 
        
        // close connection
        factoryConn.closeConnection();
        
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
        
        String sql = "SELECT m.id, m.nome, m.telefone, m.email, m.cpf, m.bloco, m.andar, m.apartamento, ";
        sql += "c.id as condominio_id, c.cnpj, c.nome as nome_c, c.telefone as telefone_c, c.endereco, c.numero, c.cidade, c.estado, c.cep, c.valor_aluguel ";
        sql += "FROM morador m, condominio c WHERE c.id=m.condominio_id AND bloco=$1$";
        
        CustomPrepareStatement prepare = new CustomPrepareStatement(sql);
        
        try {
            
            prepare.setInt(1, bloco);

            ResultSet rs = prepare.executeQuery(conn);
            
            // loop through the result set
            while (rs.next()) {
                moradorArr.add(buildMoradorObject(rs));
            }
   
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } 
        
        // close connection
        factoryConn.closeConnection();
            
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
        sql += "SET condominio_id=$1$, nome=$2$, telefone=$3$, email=$4$, cpf=$5$, bloco=$6$, andar=$7$, apartamento=$8$ ";
        sql += "WHERE id=$9$";
        
        CustomPrepareStatement prepare = new CustomPrepareStatement(sql);
        
        try {
            
            prepare.setInt(1, morador.getCondominio().getId());
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
        
        String sql = "DELETE FROM morador WHERE id=$1$";
        
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
