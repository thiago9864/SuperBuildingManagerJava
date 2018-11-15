/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.sql.Connection;
import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

/**
 *
 * @author thiagoalmeida
 */
public class CustomPrepareStatement {
    private String sql;

    public CustomPrepareStatement(String sql) {
        this.sql = sql;
    }
    
    public void setInt(Integer indice, Integer valor){
        this.sql = this.sql.replace("$" + indice.toString() + "$", valor.toString());
    }
    
    public void setString(Integer indice, String valor){
        this.sql = this.sql.replace("$" + indice.toString() + "$", "'" + valor + "'");
    }
    
    public void setFloat(Integer indice, Float valor){
        this.sql = this.sql.replace("$" + indice.toString() + "$", valor.toString());
    }
    
    public void setDate(Integer indice, Date data){
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.sql = this.sql.replace("$" + indice.toString() + "$", "'" + fmt.format(data) + "'");
    }
    
    public void setBoolean(Integer indice, boolean valor){
        Integer valor_b = 0;
        if(valor){
            valor_b = 1;
        }
        this.sql = this.sql.replace("$" + indice.toString() + "$", valor_b.toString());
    }
    
    public Integer executeUpdate(Connection connection) throws SQLException {
        Statement stmt = connection.createStatement();
        
        System.out.println("executeUpdate: " + sql);
        Integer rowsAffected = stmt.executeUpdate(sql);
        System.out.println("rowsAffected: " + rowsAffected);

        return rowsAffected;
    }
    
    public ResultSet executeQuery(Connection connection) throws SQLException {
        Statement stmt = connection.createStatement();
        System.out.println("executeQuery: " + sql);
        return stmt.executeQuery(sql);
    }
}
