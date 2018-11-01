/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sqlite;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author thiagoalmeida
 */
public class DBManager {
    
    private static DBManager instance;
    private Connection conn = null;
    private String scriptFilePath = "modelo_banco.sql";
    private BufferedReader reader = null;
        
    private DBManager(){}
    
    public static DBManager getInstance(){
        if(instance == null){
            instance = new DBManager();
        }
        
        return instance;
    }
    
    /**
     * Connect to a sample database
     * http://www.sqlitetutorial.net/sqlite-java/sqlite-jdbc-driver/
     * https://github.com/tatsushid/mysql-wb-exportsqlite
     */
    public void connect() {
        Statement statement = null;
        String url = "jdbc:sqlite:sbm.db";
 
        try {
            
            conn = DriverManager.getConnection(url);
            
            if (conn != null) {
                //checa se tabelas já foram criadas
                statement = conn.createStatement();
                ResultSet rs = statement.executeQuery(
                        "SELECT name FROM sqlite_master WHERE name='sindico'"
                );
                String name = "";
                if(!rs.isClosed()){
                    name = rs.getString("name");
                }
                statement.close();

                //se não tiver a tabela do sindico, cria o banco denovo
                if(!name.equals("sindico")){
                    DatabaseMetaData meta = conn.getMetaData();
                    executarScriptInstalacao();
                    System.out.println("The driver name is " + meta.getDriverName());
                    System.out.println("A new database has been created.");
                } else {
                    System.out.println("A database already exists.");
                }
            }
            
        } catch (IOException e) {
            System.out.println("IOException: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
        } 
    }
    
    public void closeConnection(){
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    /*
    https://codippa.com/how-to-execute-a-database-script-in-java/
    */
    private void executarScriptInstalacao() throws IOException, SQLException {
	Statement statement = null;
        
	try {
                // create statement object
		statement = conn.createStatement();
                
		// initialize file reader
		reader = new BufferedReader(new FileReader(scriptFilePath));
		String line = null;
                String file = "";
                
		// read script line by line
		while ((line = reader.readLine()) != null) {
                    // execute query
                    file += line;
		}
                
                file = file.replace("\n", "");
                String[] arr_statements = file.split(";");
                
                //System.out.println(file);
                //System.out.println(arr_statements);
                
                for(int i=0; i < arr_statements.length; i++){
                    String stm = arr_statements[i];
                    statement.execute(stm);
                }
                
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		// close file reader
		if (reader != null) {
                    reader.close();
		}
                // close statement
		if (statement != null) {
                    statement.close();
		}
	}
   }
    
   public Connection getConnection(){
       return conn;
   }
    
   
   /**
     * select all rows in the warehouses table
     * http://www.sqlitetutorial.net/sqlite-java/select/
     */
    public boolean checarCredenciais(String usuario, String senha){
        
        String sql = "SELECT * FROM sindico WHERE usuario='"+usuario+"' AND senha='" + senha + "'";
        boolean resultado = false;
        Statement stmt = null;
        

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
        
        return resultado;
    }
}
