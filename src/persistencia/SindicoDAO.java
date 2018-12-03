/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import dominio.Condominio;
import dominio.Sindico;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

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
        
        boolean resultado = false;
        Connection conn = factoryConn.getConnection();
        
        String sql = "SELECT * FROM sindico WHERE usuario=$1$ AND senha=$2$";
        
        CustomPrepareStatement prepare = new CustomPrepareStatement(sql);
        
        try {
            prepare.setString(1, usuario);
            prepare.setString(2, senha);

            ResultSet rs = prepare.executeQuery(conn);
            
            // loop through the result set
            while (rs.next()) {
                resultado = true;
                System.out.println("valido!");
            }
            
            // close connection
            factoryConn.closeConnection();
                
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } 
        
        return resultado;
    }
    
    
    
    
    /******** CRUD *********/
    
    
    private Sindico buildSindicoObject(ResultSet rs) throws SQLException {
        
        Condominio condominioObj = new Condominio(
                    rs.getInt("id_condominio"),
                    rs.getString("cnpj"),
                    rs.getString("nome_c"),                    
                    rs.getString("telefone_c"),
                    rs.getString("endereco"),
                    rs.getString("numero"),
                    rs.getString("cidade"),
                    rs.getString("estado"),
                    rs.getInt("cep"),
                    rs.getFloat("valor_aluguel")
            );
            return new Sindico(
                   rs.getInt("id"),
                   condominioObj,
                   rs.getString("nome"),
                   rs.getString("cpf"),
                   rs.getString("telefone"),
                   rs.getString("email"),
                   rs.getString("usuario"),
                   rs.getString("senha")
            );
            
    }
    
    
    /**
     * Metodo que cria uma entrada de síndico na tabela síndico
     * @param sindico (objeto do tipo Sindico)
     * @return (int idInserido ou -1 se der falha)
     */
    public Integer create(Sindico sindico){
        
        Connection conn = factoryConn.getConnection();
        Integer newID = factoryConn.maxIDFromTable("sindico") + 1;
        
        int rowsAffected = 0;
        

        String sql = "INSERT INTO sindico (id, nome, cpf, telefone, email, usuario, senha, condominio_id) ";
        sql += "VALUES ($1$, $2$, $3$, $4$, $5$, $6$, $7$, $8$)";
        
        CustomPrepareStatement prepare = new CustomPrepareStatement(sql);
        
        try {
            System.out.println("SQL: " + sql);
            
            prepare.setInt(1, newID);
            prepare.setString(2, sindico.getNome());
            prepare.setString(3, sindico.getCpf());
            prepare.setString(4, sindico.getTelefone());
            prepare.setString(5, sindico.getEmail());
            prepare.setString(6, sindico.getLogin());
            prepare.setString(7, sindico.getSenha());
            prepare.setInt(8, sindico.getCondominio().getId());
            
            rowsAffected = prepare.executeUpdate(conn);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        // close connection
        factoryConn.closeConnection();
            
        if(rowsAffected > 0){
            return newID;
        } else {
            return -1;
        }
    }
    
    /**
     * Metodo que obtem os dados do sindico indicado pelo parametro idSindico
     * @param id (int)
     * @return (objeto Sindico)
     */
    public Sindico read(Integer id){
        
        Connection conn = factoryConn.getConnection();
        Sindico sindicoObj = null;
        
        
        String sql = "SELECT s.id, s.nome, s.cpf, s.telefone, s.email, s.usuario, s.senha, ";
        sql += "c.id as id_condominio, c.nome as nome_c, c.cnpj, c.telefone as telefone_c, c.endereco, c.numero, c.cidade, c.estado, c.cep, c.valor_aluguel ";
        sql += "FROM sindico s, condominio c ";
        sql += "WHERE s.id=$1$";
        
        CustomPrepareStatement prepare = new CustomPrepareStatement(sql);
        
        try {
            
            prepare.setInt(1, id);
             
            ResultSet rs = prepare.executeQuery(conn);
            
            sindicoObj = buildSindicoObject(rs);
 
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } 
        
        // close connection
        factoryConn.closeConnection();
            
        return sindicoObj;
    }
    
    /**
     * Metodo que atualiza os dados de um sindico dado o objeto recebido
     * @param sindico (objeto Sindico)
     * @return (true se sucesso, false se erro)
     */
    public boolean update(Sindico sindico){
        
        Connection conn = factoryConn.getConnection();
        int rowsAffected = 0;
        
        String sql = "";
        
        sql += "UPDATE sindico ";
        sql += "SET nome=$1$, cpf=$2$, telefone=$3$, email=$4$, usuario=$5$, senha=$6$, condominio_id=$7$ ";
        sql += "WHERE id=$8$";
        
        CustomPrepareStatement prepare = new CustomPrepareStatement(sql);
        
        try {

            prepare.setString(1, sindico.getNome());
            prepare.setString(2, sindico.getCpf());
            prepare.setString(3, sindico.getTelefone());
            prepare.setString(4, sindico.getEmail());
            prepare.setString(5, sindico.getLogin());
            prepare.setString(6, sindico.getSenha());
            prepare.setInt(7, sindico.getCondominio().getId());
            prepare.setInt(8, sindico.getId());
             
            rowsAffected = prepare.executeUpdate(conn);
            
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
     * Metodo que deleta um sindico dado id fornecido
     * @param id (int)
     * @return (true se excluiu, false se não excluiu)
     */
    public boolean delete(Integer id){
        Connection conn = factoryConn.getConnection();
        int rowsAffected = 0;
        
        String sql = "DELETE FROM sindico WHERE id=$1$";
       
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
