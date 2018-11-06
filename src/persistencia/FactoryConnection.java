/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

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
public class FactoryConnection {
    
    private Connection conn = null;
    private String scriptFilePath = "modelo_banco.sql";
    private BufferedReader reader = null;
        
    public FactoryConnection(){}
    
    /**
    * Obtem a conexão com o banco
    * @return Connection
    */ 
    public Connection getConnection(){
        
        //http://www.sqlitetutorial.net/sqlite-java/sqlite-jdbc-driver/
        //https://github.com/tatsushid/mysql-wb-exportsqlite
        
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
    
       return conn;
    }
    
    /**
     * Metodo pra fechar a conexão com o banco
     */
    public void closeConnection(){
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    

    /**
     * Metodo pra criar a estrutura do banco no arquivo SQLite vazio
     * @throws IOException
     * @throws SQLException 
     */
    private void executarScriptInstalacao() throws IOException, SQLException {
	Statement statement = null;
        
        //https://codippa.com/how-to-execute-a-database-script-in-java/
        
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
    
}
