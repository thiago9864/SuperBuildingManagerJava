/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import dominio.Orcamento;
import dominio.Condominio;
import dominio.Sindico;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
/**
 *
 * @author thiagoalmeida
 */
public class OrcamentoDAO {
    
    FactoryConnection factoryConn = new FactoryConnection();

    public OrcamentoDAO() {
    }
    
    
    
    
    /******** CRUD *********/
    
    
    private Orcamento buildOrcamentoObject(ResultSet rs) throws SQLException {
        
        Condominio objCondominio = new Condominio(
                rs.getInt("condominio_id"),
                rs.getString("nome_c"),
                rs.getString("cnpj"),
                rs.getString("telefone_c"),
                rs.getString("endereco"),
                rs.getString("numero"),
                rs.getString("cidade"),
                rs.getString("estado"),
                rs.getInt("cep"),
                rs.getFloat("valor_aluguel")
            );
            
            Sindico objSindico = new Sindico(
                rs.getInt("sindico_id"),
                objCondominio,
                rs.getString("nome_s"),
                rs.getString("cpf"),
                rs.getString("telefone_s"),
                rs.getString("email")
            );
            
            return new Orcamento(
                rs.getInt("id"),
                objSindico,
                rs.getInt("mes"), 
                rs.getInt("ano"),
                rs.getFloat("custo"),
                rs.getFloat("renda"),
                rs.getFloat("saldo")
            );
    }
    
    
    /**
     * Metodo que cria uma entrada de orcamento na tabela orcamento
     * @param orcamento (objeto do tipo Orcamento)
     * @return (int idInserido ou -1 se der falha)
     */
    public Integer create(Orcamento orcamento){
        
        Connection conn = factoryConn.getConnection();
        Integer newID = factoryConn.maxIDFromTable("orcamento") + 1;
        int rowsAffected = 0;
        
        String sql = "INSERT INTO orcamento (id, sindico_id, mes, ano, custo, renda, saldo) ";
        sql += "VALUES ($1, $2, $3, $4, $5, $6, $7)";
        
        CustomPrepareStatement prepare = new CustomPrepareStatement(sql);

        try {
            
            prepare.setInt(1, newID);
            prepare.setInt(2, orcamento.getSindico().getId());
            prepare.setInt(3, orcamento.getMes());
            prepare.setInt(4, orcamento.getAno());
            prepare.setFloat(5, orcamento.getCusto());
            prepare.setFloat(6, orcamento.getRenda());
            prepare.setFloat(7, orcamento.getSaldo());
             
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
    public Orcamento read(Integer id){
        
        Connection conn = factoryConn.getConnection();
        Orcamento objOrcamento = null;
        String sql = "";
        
        sql = "SELECT o.id, o.mes, o.ano, o.custo, o.renda, o.saldo, ";
        sql += "s.id as sindico_id, s.nome as nome_s, s.cpf. s.telefone as telefone_s, s.email, ";
        sql += "c.id as condominio_id, c.nome as nome_c, c.cnpj, c.telefone as telefone_c, c.endereco, c.numero, c.cidade, c.estado, c.cep, c.valor_aluguel ";
        sql += "FROM orcamento o, sindico s, condominio c ";
        sql += "WHERE s.id=o.sindico_id AND c.id=s.condominio_id AND id=$1";
        
        CustomPrepareStatement prepare = new CustomPrepareStatement(sql);
        
        /*
        "id" INTEGER NOT NULL,
  "sindico_id" INTEGER NOT NULL,
  "mes" INTEGER,
  "ano" INTEGER,
  "custo" REAL,
  "renda" REAL,
  "saldo" REAL,
        
        *** sindico ***
        
        "id" INTEGER NOT NULL,
  "nome" TEXT,
  "cpf" TEXT,
  "telefone" TEXT,
  "email" TEXT,
  "usuario" TEXT,
  "senha" TEXT,
  "condominio_id" INTEGER NOT NULL,
        
        *** condominio ***
        
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
        try {
            
            prepare.setInt(1, id);

            ResultSet rs = prepare.executeQuery(conn);
            
            objOrcamento = buildOrcamentoObject(rs);

            // close connection
            factoryConn.closeConnection();
                
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } 
        
        return objOrcamento;
    }
    
    /**
     * Metodo que lista os orçamentos baseado no filtro ano e mês
     * @param ano (int)
     * @param mes (int)
     * @return (ArrayList<Orcamento>)
     */
    public ArrayList<Orcamento> list(Integer ano, Integer mes){
        
        Connection conn = factoryConn.getConnection();
        ArrayList<Orcamento> orcamentoArr = new ArrayList<>();
        String sql = "";
        
        sql = "SELECT o.id, o.mes, o.ano, o.custo, o.renda, o.saldo, ";
        sql += "s.id as sindico_id, s.nome as nome_s, s.cpf. s.telefone as telefone_s, s.email, ";
        sql += "c.id as condominio_id, c.nome as nome_c, c.cnpj, c.telefone as telefone_c, c.endereco, c.numero, c.cidade, c.estado, c.cep, c.valor_aluguel ";
        sql += "FROM orcamento o, sindico s, condominio c ";
        sql += "WHERE s.id=o.sindico_id AND c.id=s.condominio_id AND ano=$1 AND mes=$2";
        
        CustomPrepareStatement prepare = new CustomPrepareStatement(sql);
        
        try {
            
            prepare.setInt(1, ano);
            prepare.setInt(2, mes);

            ResultSet rs = prepare.executeQuery(conn);
            
            // loop through the result set
            while (rs.next()) {
                orcamentoArr.add(buildOrcamentoObject(rs));
            }

            // close connection
            factoryConn.closeConnection();
                
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } 
        
        return orcamentoArr;
    }
    
    
    /**
     * Metodo que atualiza os dados de um orcamento dado o objeto recebido
     * @param orcamento (objeto Orcamento)
     * @return (true se sucesso, false se erro)
     */
    public boolean update(Orcamento orcamento){
        
        Connection conn = factoryConn.getConnection();
        int rowsAffected = 0;

        String sql = "";
        sql += "UPDATE orcamento ";
        sql += "SET condominio_id=$1, nome=$2, telefone=$3, email=$4, cpf=$5, bloco=$6, andar=$7, apartamento=$8 ";
        sql += "WHERE id=$9";
        
        CustomPrepareStatement prepare = new CustomPrepareStatement(sql);
        
        try {
            
            /* DESCOMENTAR - Está aqui só pra não bugar o projeto
            prepare.setInt(1, morador.getCondominioId());
            prepare.setString(2, morador.getNome());
            prepare.setString(3, morador.getTelefone());
            prepare.setString(4, morador.getEmail());
            prepare.setString(5, morador.getCpf());
            prepare.setInt(6, morador.getBloco());
            prepare.setInt(7, morador.getAndar());
            prepare.setInt(8, morador.getApartamento());    
            prepare.setInt(9, morador.getId());
            */
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
     * Metodo que deleta um orcamento dado id fornecido
     * @param id (int)
     * @return (true se excluiu, false se não excluiu)
     */
    public boolean delete(Integer id){
        
        Connection conn = factoryConn.getConnection();
        int rowsAffected = 0;
        
        String sql = "DELETE FROM orcamento WHERE id=$1";
        
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
