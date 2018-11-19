/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import dominio.Boleto;
import dominio.Condominio;
import dominio.Morador;
import dominio.StatusBoleto;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author thiagoalmeida
 */
public class BoletoDAO {
    FactoryConnection factoryConn = new FactoryConnection();

    public BoletoDAO() {
    }
    
    
    
    
    /******** CRUD *********/
    

    
    private Boleto buildBoletoObject(ResultSet rs) throws SQLException {
        
        Condominio condominioObj = new Condominio(
                rs.getInt("morador_condominio_id"),
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


        Morador objMorador = new Morador(
            rs.getInt("morador_id"),
            condominioObj,
            rs.getString("nome_m"), 
            rs.getString("telefone_m"),
            rs.getString("email"),
            rs.getString("cpf"),
            rs.getInt("bloco"),
            rs.getInt("andar"),
            rs.getInt("apartamento")
        );
        
        StatusBoleto objStatusBoleto = new StatusBoleto(
            rs.getInt("status_boleto_id"),
            rs.getString("nome_s"),
            rs.getString("descricao")
        );
        
        Date data_vencimento = null;
        
        try {
            data_vencimento = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(rs.getString("data_vencimento"));
        } catch (ParseException e){
            System.out.println(e.getMessage());
        }
        
        
        return new Boleto(
                rs.getInt("id"),
                objMorador,
                objStatusBoleto,                    
                rs.getString("banco"),
                rs.getString("codigo"),
                rs.getFloat("valor"),
                rs.getFloat("juros"),
                rs.getFloat("desconto"),
                rs.getFloat("multa"),
                data_vencimento,
                rs.getBoolean("is_segunda_via")
        );
        
    }
    
    
    /**
     * Metodo que cria uma entrada de boleto na tabela boleto
     * @param boleto (objeto do tipo Boleto)
     * @return (int idInserido ou -1 se der falha)
     */
    public Integer create(Boleto boleto){
        
        Connection conn = factoryConn.getConnection();
        int rowsAffected = 0;
        
        String sql = "INSERT INTO boleto (id, morador_id, morador_condominio_id, status_boleto_id, banco, codigo, valor, juros, desconto, multa, data_vencimento, is_segunda_via) ";
        sql += "VALUES ($1$, $2$, $3$, $4$, $5$, $6$, $7$, $8$, $9$, $10$, $11$, $12$)";
        
        CustomPrepareStatement prepare = new CustomPrepareStatement(sql);

        try {
            
            prepare.setInt(1, boleto.getId());
            prepare.setInt(2, boleto.getMorador().getId());
            prepare.setInt(3, boleto.getMorador().getCondominio().getId());
            prepare.setInt(4, boleto.getStatusBoleto().getId());
            prepare.setString(5, boleto.getBanco());
            prepare.setString(6, boleto.getCodigo());
            prepare.setFloat(7, boleto.getValor());
            prepare.setFloat(8, boleto.getJuros());
            prepare.setFloat(9, boleto.getDesconto());
            prepare.setFloat(10, boleto.getMulta());
            prepare.setDate(11, boleto.getDataVencimento());
            prepare.setBoolean(12, boleto.isSegundaVia());
             
            rowsAffected = prepare.executeUpdate(conn);
            System.out.println("rowsAffected: " + rowsAffected);
      
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        // close connection
        factoryConn.closeConnection();
            
        if(rowsAffected > 0){
            //retorna o id do condominio criado
            return factoryConn.maxIDFromTable("boleto");
        } else {
            return -1;
        }
    }
    

    /**
     * Metodo que obtem os dados do boleto
     * @param id (int)
     * @return (objeto Boleto)
     */
    public Boleto read(Integer id){
        
        Connection conn = factoryConn.getConnection();
        Boleto boletoObj = null;
        
        String sql = "SELECT b.id, b.banco, b.codigo, b.valor, b.juros, b.desconto, b.multa, b.data_vencimento, b.is_segunda_via, ";
        sql += "m.id as morador_id, m.nome as nome_m, m.telefone as telefone_m, m.email, m.cpf, m.bloco, m.andar, m.apartamento, ";
        sql += "c.id as morador_condominio_id, c.nome as nome_c, c.cnpj, c.telefone as telefone_c, c.endereco, c.numero, c.cidade, c.estado, c.cep, c.valor_aluguel, ";
        sql += "s.id as status_boleto_id, s.nome as nome_s, s.descricao ";
        sql += "FROM boleto b, morador m, condominio c, status_boleto s ";
        sql += "WHERE m.id=b.morador_id AND c.id=b.morador_condominio_id AND s.id=b.status_boleto_id AND b.id=$1$";
        
        CustomPrepareStatement prepare = new CustomPrepareStatement(sql);
        
        try {
            
            prepare.setInt(1, id);

            ResultSet rs = prepare.executeQuery(conn);
            
            boletoObj = buildBoletoObject(rs);
  
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } 
        
        // close connection
        factoryConn.closeConnection();
        
        return boletoObj;
    }
    
    
    /**
     * Metodo que lista os boletos por morador
     * @param idMorador
     * @return ArrayList<Boleto>
     */
    public ArrayList<Boleto> list(Integer idMorador){
        
        Connection conn = factoryConn.getConnection();
        ArrayList<Boleto> orcamentoArr = new ArrayList<>();
        String sql = "";
        
        sql = "SELECT b.id, b.banco, b.codigo, b.valor, b.juros, b.desconto, b.multa, b.data_vencimento, b.is_segunda_via, ";
        sql += "m.id as morador_id, m.nome as nome_m, m.telefone as telefone_m, m.email, m.cpf, m.bloco, m.andar, m.apartamento, ";
        sql += "c.id as morador_condominio_id, c.nome as nome_c, c.cnpj, c.telefone as telefone_c, c.endereco, c.numero, c.cidade, c.estado, c.cep, c.valor_aluguel, ";
        sql += "s.id as status_boleto_id, s.nome as nome_s, s.descricao ";
        sql += "FROM boleto b, morador m, condominio c, status_boleto s ";
        sql += "WHERE m.id=b.morador_id AND c.id=m.condominio_id AND s.id=b.status_boleto_id AND b.morador_id=$1$";
        
        CustomPrepareStatement prepare = new CustomPrepareStatement(sql);
        
        try {
            
            prepare.setInt(1, idMorador);

            ResultSet rs = prepare.executeQuery(conn);
            
            // loop through the result set
            while (rs.next()) {
                orcamentoArr.add(buildBoletoObject(rs));
            }
                
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } 
        
        // close connection
        factoryConn.closeConnection();
        
        return orcamentoArr;
    }
    
    
    /**
     * Metodo que atualiza os dados de um boleto dado o objeto recebido
     * @param boleto (objeto Boleto)
     * @return (true se sucesso, false se erro)
     */
    public boolean update(Boleto boleto){
        
        Connection conn = factoryConn.getConnection();
        int rowsAffected = 0;
        
        String sql = "";
        sql += "UPDATE boleto ";
        sql += "SET morador_id=$1$, morador_condominio_id=$2$, status_boleto_id=$3$, banco=$4$, codigo=$5$, valor=$6$, juros=$7$, desconto=$8$, multa=$9$, data_vencimento=$10$, is_segunda_via=$11$ ";
        sql += "WHERE id=$12$";
        
        CustomPrepareStatement prepare = new CustomPrepareStatement(sql);
        
        try {
            
            
            prepare.setInt(1, boleto.getMorador().getId());
            prepare.setInt(2, boleto.getMorador().getCondominio().getId());
            prepare.setInt(3, boleto.getStatusBoleto().getId());
            prepare.setString(4, boleto.getBanco());
            prepare.setString(5, boleto.getCodigo());
            prepare.setFloat(6, boleto.getValor());
            prepare.setFloat(7, boleto.getJuros());
            prepare.setFloat(8, boleto.getDesconto());
            prepare.setFloat(9, boleto.getMulta());
            prepare.setDate(10, boleto.getDataVencimento());
            prepare.setBoolean(11, boleto.isSegundaVia());  
            prepare.setInt(12, boleto.getId());
             
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
     * Metodo que deleta um boleto dado id fornecido
     * @param id (int)
     * @return (true se excluiu, false se não excluiu)
     */
    public boolean delete(Integer id){
        
        Connection conn = factoryConn.getConnection();
        int rowsAffected = 0;
        
        String sql = "DELETE FROM boleto WHERE id=$1$";
        
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
