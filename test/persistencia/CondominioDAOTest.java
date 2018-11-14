/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import dominio.Condominio;
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
public class CondominioDAOTest {
    
    CondominioDAO instance;
    Integer maxID;
    Condominio objCondominio;
    
    public CondominioDAOTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        instance = new CondominioDAO();
        FactoryConnection factoryConn = new FactoryConnection();
        maxID = factoryConn.maxIDFromTable("condominio");
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of criaSindico method, of class SindicoDAO.
     */
    @Test
    public void teste2() {
        System.out.println("cria Condominio");
           
        objCondominio = new Condominio(
                -1,//não usar esse campo, essa tabela tem autoincrement
                "nome",
                "cnpj",
                "telefone",
                "endereco",
                "numero",
                "cidade",
                "estado",
                0,
                123.45f
        );
        
        Integer idInserted = instance.create(objCondominio);
        Integer nao_esperado = -1;
        
        System.out.println("idInserted: " + idInserted);
        
        assertNotEquals(nao_esperado, idInserted);
    }
    
    /**
     * Test of updateSindico method, of class SindicoDAO.
     */
    @Test
    public void teste3() {
        System.out.println("update Condominio");

        objCondominio = new Condominio(
                maxID,
                "1122338978574-55",
                "nome",                
                "(032) 9999-9999",
                "endereco",
                "numero",
                "cidade",
                "estado",
                123456,
                999.99f
        );
        
        boolean wasUpdated = instance.update(objCondominio);
        
        System.out.println("wasUpdated: " + wasUpdated);
        
        assertEquals(true, wasUpdated);
        
    }
    
    
    /**
     * Test of readSindico method, of class SindicoDAO.
     */
    @Test
    public void teste4() {
        System.out.println("read Condominio");
       
        Condominio lido = instance.read(maxID);
        
        objCondominio = new Condominio(
                maxID,
                "1122338978574-55",
                "nome",                
                "(032) 9999-9999",
                "endereco",
                "numero",
                "cidade",
                "estado",
                123456,
                999.99f
        );
        
        if(lido == null){
            fail("objCondominio == null");
        } else {
            assertEquals(objCondominio.getId(), lido.getId());
            assertEquals(objCondominio.getNome(), lido.getNome());
            assertEquals(objCondominio.getCnpj(), lido.getCnpj());
            assertEquals(objCondominio.getTelefone(), lido.getTelefone());
            assertEquals(objCondominio.getEndereco(), lido.getEndereco());
            assertEquals(objCondominio.getNumero(), lido.getNumero());
            assertEquals(objCondominio.getCidade(), lido.getCidade());
            assertEquals(objCondominio.getEstado(), lido.getEstado());
            assertEquals(objCondominio.getCep(), lido.getCep());
            assertEquals(objCondominio.getValor_aluguel(), lido.getValor_aluguel());
        }
    }
    
    /**
     * Test of deleteSindico method, of class SindicoDAO.
     */
    @Test
    public void teste5() {
        System.out.println("delete Condominio");

        boolean wasDeleted = instance.delete(maxID);
         
        assertEquals(true, wasDeleted);
    }
    
}
