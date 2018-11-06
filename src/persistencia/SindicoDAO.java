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
        
        String sql = "SELECT * FROM sindico WHERE usuario='"+usuario+"' AND senha='" + senha + "'";
        boolean resultado = false;
        Statement stmt = null;
        Connection conn = factoryConn.getConnection();
        
        try {
             stmt  = conn.createStatement();
             System.out.println("SQL: " + sql);
             ResultSet rs = stmt.executeQuery(sql);
            
            // loop through the result set
            while (rs.next()) {
                /*String u = rs.getString("usuario");
                String s = rs.getString("senha");
                System.out.println(rs.getString("id"));
                System.out.println(u);
                System.out.println(s);*/

                resultado = true;
                System.out.println("valido!");

            }
            
            // close statement
            if (stmt != null) {
                stmt.close();
            }
                
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } 
        
        
        factoryConn.closeConnection();
        
        return resultado;
    }
}
