/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import dominio.Sindico;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

/**
 *
 * @author thiagoalmeida
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SindicoDAOTest {
    
    SindicoDAO instance;
    Integer maxID;
    Sindico objSindico;
    
    public SindicoDAOTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        instance = new SindicoDAO();
        FactoryConnection factoryConn = new FactoryConnection();
        maxID = factoryConn.maxIDFromTable("sindico");
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of loginErrado method, of class SindicoDAO.
     */
    @Test
    public void teste0() {
        System.out.println("checarCredenciais - Login Errado");
        
        String usuario = "";
        String senha = "";
        
        //testa caso de login errado
        boolean result = instance.checarCredenciais(usuario, senha);
        assertEquals(false, result);
    }
    
    /**
     * Test of loginCerto method, of class SindicoDAO.
     */
    @Test
    public void teste1() {
        System.out.println("checarCredenciais - Login Certo");
        
        String usuario = "admin";
        String senha = "admin";
        
        //testa caso de login certo
        boolean result = instance.checarCredenciais(usuario, senha);
        assertEquals(true, result);
    }
    
    /**
     * Test of criaSindico method, of class SindicoDAO.
     */
    @Test
    public void teste2() {
        System.out.println("criaSindico");
        Integer newID = maxID+1;
           
        objSindico = new Sindico(
                newID,
                0,
                "nome",
                "cpf",
                "telefone",
                "email",
                "usuario",
                "senha"
        );
        
        Integer idInserted = instance.createSindico(objSindico);
        
        System.out.println("idInserted: " + idInserted);
        
        assertEquals(newID, idInserted);
    }
    
    /**
     * Test of updateSindico method, of class SindicoDAO.
     */
    @Test
    public void teste3() {
        System.out.println("updateSindico");

        objSindico = new Sindico(
                maxID,
                0,
                "nome",
                "112233445566",
                "(032) 9999-9999",
                "email",
                "usuario",
                "senha"
        );
        
        boolean wasUpdated = instance.updateSindico(objSindico);
        
        System.out.println("wasUpdated: " + wasUpdated);
        
        assertEquals(true, wasUpdated);
        
    }
    
    
    /**
     * Test of readSindico method, of class SindicoDAO.
     */
    @Test
    public void teste4() {
        System.out.println("readSindico");
       
        Sindico lido = instance.readSindico(maxID);
        
        objSindico = new Sindico(
                maxID,
                0,
                "nome",
                "112233445566",
                "(032) 9999-9999",
                "email",
                "usuario",
                "senha"
        );
        if(lido == null){
            fail("objSindico == null");
        } else {
            assertEquals(objSindico.getId(), lido.getId());
            assertEquals(objSindico.getCondominioId(), lido.getCondominioId());
            assertEquals(objSindico.getNome(), lido.getNome());
            assertEquals(objSindico.getCpf(), lido.getCpf());
            assertEquals(objSindico.getTelefone(), lido.getTelefone());
            assertEquals(objSindico.getLogin(), lido.getLogin());
            assertEquals(objSindico.getSenha(), lido.getSenha());
        }
    }
    
    /**
     * Test of deleteSindico method, of class SindicoDAO.
     */
    @Test
    public void teste5() {
        System.out.println("deleteSindico");

        boolean wasDeleted = instance.deleteSindico(maxID);
         
        assertEquals(true, wasDeleted);
    }
    
}
