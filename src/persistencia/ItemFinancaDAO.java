/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import dominio.Gasto;
import dominio.ObjetoFinanceiro;
import dominio.Receita;
import dominio.TipoFinanca;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
/**
 *
 * @author Thiago
 */
public class ItemFinancaDAO {
    
    FactoryConnection factoryConn = new FactoryConnection();

    public ItemFinancaDAO() {
    }
    
    /******** CRUD *********/
    
    
    private Receita buildReceitaObject(ResultSet rs) throws SQLException {
        
        Date dataRecebimento = null;
        OrcamentoDAO orcamentoDAO = new OrcamentoDAO();
        
        try {
            dataRecebimento = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(rs.getString("data_item"));
        } catch (ParseException e){
            System.out.println(e.getMessage());
        } 
        
        TipoFinanca tipoFinanca = new TipoFinanca(
                rs.getInt("id"),
                rs.getString("nome_t"),
                rs.getString("descricao_t"),
                rs.getBoolean("is_renda")
        );
        
        return new Receita(
               dataRecebimento,
               rs.getInt("boleto_id"),
               rs.getInt("id"),
               orcamentoDAO.buildOrcamentoObject(rs),
               tipoFinanca,
               rs.getFloat("valor"),
               rs.getString("descricao")
        );

    }
    
    private Gasto buildGastoObject(ResultSet rs) throws SQLException {
        
        Date dataPagamento = null;
        
        try {
            dataPagamento = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(rs.getString("data_item"));
        } catch (ParseException e){
            System.out.println(e.getMessage());
        }
        
        OrcamentoDAO orcamentoDAO = new OrcamentoDAO();
        
        TipoFinanca tipoFinanca = new TipoFinanca(
                rs.getInt("id"),
                rs.getString("nome_t"),
                rs.getString("descricao_t"),
                rs.getBoolean("is_renda")
        );
        
        return new Gasto(
               dataPagamento,
               rs.getInt("id"),
               orcamentoDAO.buildOrcamentoObject(rs),
               tipoFinanca,
               rs.getFloat("valor"),
               rs.getString("descricao")
        );

    }
    
    
    /**
     * Metodo que cria uma entrada de objeto financeiro (receita ou gasto) na tabela item_financa
     * @param objeto (objeto do tipo ObjetoFinanceiro)
     * @return (int idInserido ou -1 se der falha)
     */
    public Integer create(ObjetoFinanceiro objeto){
        
        Connection conn = factoryConn.getConnection();
        Integer newID = factoryConn.maxIDFromTable("item_financa") + 1;
        
        int rowsAffected = 0;

        String sql = "INSERT INTO item_financa (id, orcamento_id, tipo_financa_id, boleto_id, data_item, valor, descricao) ";
        sql += "VALUES ($1$, $2$, $3$, $4$, $5$, $6$, $7$)";
        
        CustomPrepareStatement prepare = new CustomPrepareStatement(sql);
        
        try {            
            prepare.setInt(1, newID);
            prepare.setInt(2, objeto.getOrcamento().getId());
            prepare.setInt(3, objeto.getTipoFinanca().getId());
            
            if(objeto instanceof Receita){
                prepare.setInt(4, ((Receita)objeto).getBoletoId());
                prepare.setDate(5, ((Receita)objeto).getDataRecebimento());
            } else {
                prepare.setInt(4, null);
            }
            if(objeto instanceof Gasto){
                prepare.setDate(5, ((Gasto)objeto).getDataPagamento());
            }

            prepare.setFloat(6, objeto.getValor());
            prepare.setString(7, objeto.getDescricao());
            
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
     * Metodo que obtem os dados do objeto financeiro (receita ou gasto) indicado pelo parametro id
     * @param id (int)
     * @return (objeto ObjetoFinanceiro)
     */
    public ObjetoFinanceiro read(Integer id){
        Connection conn = factoryConn.getConnection();
        ObjetoFinanceiro objeto = null;
        
        
        String sql = "SELECT f.id, f.boleto_id, f.data_item, f.valor, f.descricao, ";
        
        //tipo financa
        sql += "t.id as tipo_financa_id, t.nome as nome_t, t.descricao as descricao_t, t.is_renda, ";
        
        //orcamento
        sql += "o.id as orcamento_id, o.mes, o.ano, o.custo, o.renda, o.saldo, ";
        sql += "s.id as sindico_id, s.nome as nome_s, s.cpf, s.telefone as telefone_s, s.email, ";
        sql += "c.id as condominio_id, c.nome as nome_c, c.cnpj, c.telefone as telefone_c, c.endereco, c.numero, c.cidade, c.estado, c.cep, c.valor_aluguel ";
        
        sql += "FROM item_financa f, tipo_financa t, orcamento o, sindico s, condominio c ";
        
        sql += "WHERE t.id=f.tipo_financa_id AND o.id=f.orcamento_id AND s.id=o.sindico_id AND c.id=s.condominio_id AND f.id=$1$";
        
        CustomPrepareStatement prepare = new CustomPrepareStatement(sql);
        
        try {
            
            prepare.setInt(1, id);
             
            ResultSet rs = prepare.executeQuery(conn);
            
            if(rs.getBoolean("is_renda")){
                objeto = buildReceitaObject(rs);
            } else {
                objeto = buildGastoObject(rs);
            }
                
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } 
        
        // close connection
        factoryConn.closeConnection();
        
        return objeto;
    }
    
    /**
     * Metodo que lista os objetos financeiros (receita ou gasto) baseado no filtro data inicial e data final
     * @param inicio (Date)
     * @param fim (Date)
     * @return (ArrayList<ObjetoFinanceiro>)
     */
    public ArrayList<ObjetoFinanceiro> list(Date inicio, Date fim){
        return listBase(0, inicio, fim);
    }
    
    /**
     * Metodo que lista as receitas baseado no filtro data inicial e data final
     * @param inicio (Date)
     * @param fim (Date)
     * @return (ArrayList<Receita>)
     */
    public ArrayList<Receita> listReceita(Date inicio, Date fim){
        ArrayList<ObjetoFinanceiro> list = listBase(1, inicio, fim);
        ArrayList<Receita> list_r = new ArrayList<>();
        for(ObjetoFinanceiro obj : list){
            list_r.add((Receita)obj);
        }
        return list_r;  
    }
    
    /**
     * Metodo que lista os gastos baseado no filtro data inicial e data final
     * @param inicio (Date)
     * @param fim (Date)
     * @return (ArrayList<Gasto>)
     */
    public ArrayList<Gasto> listGasto(Date inicio, Date fim){
        ArrayList<ObjetoFinanceiro> list = listBase(2, inicio, fim);
        ArrayList<Gasto> list_g = new ArrayList<>();
        for(ObjetoFinanceiro obj : list){
            list_g.add((Gasto)obj);
        }
        return list_g;  
    }
    private ArrayList<ObjetoFinanceiro> listBase(int return_type, Date inicio, Date fim){
        Connection conn = factoryConn.getConnection();
        ArrayList<ObjetoFinanceiro> objetoArr = new ArrayList<>();
        String sql = "";
        
        sql = "SELECT f.id, f.boleto_id, f.data_item, f.valor, f.descricao, ";
        
        //tipo financa
        sql += "t.id as tipo_financa_id, t.nome as nome_t, t.descricao as descricao_t, t.is_renda,  ";
        
        //orcamento
        sql += "o.id as orcamento_id, o.mes, o.ano, o.custo, o.renda, o.saldo, ";
        
        //sindico
        sql += "s.id as sindico_id, s.nome as nome_s, s.cpf, s.telefone as telefone_s, s.email, ";
        
        //condominio
        sql += "c.id as condominio_id, c.nome as nome_c, c.cnpj, c.telefone as telefone_c, c.endereco, c.numero, c.cidade, c.estado, c.cep, c.valor_aluguel ";
        
        sql += "FROM item_financa f, tipo_financa t, orcamento o, sindico s, condominio c ";
        sql += "WHERE t.id=f.tipo_financa_id AND o.id=f.orcamento_id AND s.id=o.sindico_id AND c.id=s.condominio_id ";
        
        //retorna somente receitas
        if(return_type == 1){
            sql += "AND t.is_renda=1 ";
        }
        
        //retorna somente gastos
        if(return_type == 2){
            sql += "AND t.is_renda=0 ";
        }
        
        if(inicio != null && fim != null){
            sql += "BETWEEN $1$ AND $2$";
        }
        else if(inicio != null && fim == null){
            sql += "AND f.data_item > $1$";
        }
        else if(fim != null){
            sql += "AND f.data_item < $2$";
        }
        
        CustomPrepareStatement prepare = new CustomPrepareStatement(sql);
        
        try {
            
            prepare.setDate(1, inicio);
            prepare.setDate(2, fim);

            ResultSet rs = prepare.executeQuery(conn);
            
            // loop through the result set
            while (rs.next()) {
                if(rs.getBoolean("is_renda")){
                    objetoArr.add(buildReceitaObject(rs));
                } else {
                    objetoArr.add(buildGastoObject(rs));
                }
            }
     
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } 
        
        // close connection
        factoryConn.closeConnection();
        
        return objetoArr;
    }
    
    /**
     * Metodo que atualiza os dados de um objeto financeiro (receita ou gasto) dado o objeto recebido
     * @param objeto (objeto ObjetoFinanceiro)
     * @return (true se sucesso, false se erro)
     */
    public boolean update(ObjetoFinanceiro objeto){
        
        Connection conn = factoryConn.getConnection();
        int rowsAffected = 0;
        
        String sql = "";
        
        sql += "UPDATE item_financa ";
        sql += "SET orcamento_id=$1$, tipo_financa_id=$2$, boleto_id=$3$, data_item=$4$, valor=$5$, descricao=$6$ ";
        sql += "WHERE id=$7$";
        
        CustomPrepareStatement prepare = new CustomPrepareStatement(sql);
        
        try {

            prepare.setInt(1, objeto.getOrcamento().getId());
            prepare.setInt(2, objeto.getTipoFinanca().getId());
            
            if(objeto instanceof Receita){
                prepare.setInt(3, ((Receita)objeto).getBoletoId());
                prepare.setDate(4, ((Receita)objeto).getDataRecebimento());
            }
            if(objeto instanceof Gasto){
                prepare.setInt(3, null);
                prepare.setDate(4, ((Gasto)objeto).getDataPagamento());
            }

            prepare.setFloat(5, objeto.getValor());
            prepare.setString(6, objeto.getDescricao());
            
            prepare.setInt(7, objeto.getId());
             
            rowsAffected = prepare.executeUpdate(conn);
            

            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        if(rowsAffected > 0){
            return true;
        }
        
        // close connection
        factoryConn.closeConnection();
            
        return false;
    }
    
    /**
     * Metodo que deleta um objeto financeiro (receita ou gasto) dado id fornecido
     * @param id (int)
     * @return (true se excluiu, false se não excluiu)
     */
    public boolean delete(Integer id){
        Connection conn = factoryConn.getConnection();
        int rowsAffected = 0;
        
        String sql = "DELETE FROM item_financa WHERE id=$1$";
       
        CustomPrepareStatement prepare = new CustomPrepareStatement(sql);
        
        try {
            
            prepare.setInt(1, id);
             
            rowsAffected = prepare.executeUpdate(conn);
            System.out.println("rowsAffected: " + rowsAffected);
            
                
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        if(rowsAffected > 0){
            return true;
        }
        
        // close connection
        factoryConn.closeConnection();
            
        return false;
        
    }
}
