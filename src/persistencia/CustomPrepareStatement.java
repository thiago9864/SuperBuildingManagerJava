/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
        this.sql = this.sql.replace("$" + indice.toString(), valor.toString());
    }
    public void setString(Integer indice, String valor){
        this.sql = this.sql.replace("$" + indice.toString(), "'" + valor + "'");
    }
    public void setFloat(Integer indice, Float valor){
        this.sql = this.sql.replace("$" + indice.toString(), valor.toString());
    }
    
    
    public Integer executeUpdate(Connection connection) throws SQLException {
        Statement stmt = connection.createStatement();
        System.out.println("executeUpdate: " + sql);
        return stmt.executeUpdate(sql);
    }
    
    public ResultSet executeQuery(Connection connection) throws SQLException {
        Statement stmt = connection.createStatement();
        System.out.println("executeQuery: " + sql);
        return stmt.executeQuery(sql);
    }
}
