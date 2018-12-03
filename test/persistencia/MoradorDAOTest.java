/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import dominio.Condominio;
import dominio.Morador;
import java.util.ArrayList;
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
public class MoradorDAOTest {
    
    MoradorDAO instance;
    Integer maxID;
    Morador objMorador;
    
    public MoradorDAOTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        instance = new MoradorDAO();
        FactoryConnection factoryConn = new FactoryConnection();
        maxID = factoryConn.maxIDFromTable("morador");
    }
    
    @After
    public void tearDown() {
    }

   /**
     * Test of cria morador.
     */
    @Test
    public void teste1() {
        System.out.println("cria Morador");
        
        Integer newID = maxID+1;
        
        objMorador = new Morador(
                newID,
                new Condominio(0),
                "nome",
                "telefone",
                "email",
                "cpf",
                0,
                0,
                0
        );
        
        Integer idInserted = instance.create(objMorador);
        Integer nao_esperado = -1;
        
        System.out.println("idInserted: " + idInserted);
        
        assertNotEquals(nao_esperado, idInserted);
    }
    
    /**
     * Test of update Morador
     */
    @Test
    public void teste2() {
        System.out.println("update Morador");

        objMorador = new Morador(
                maxID,
                new Condominio(0),
                "Fulano",
                "(032) 9999-9999",
                "teste@teste.com",
                "111111111111",
                1,
                0,
                0
        );
        
        boolean wasUpdated = instance.update(objMorador);
        
        System.out.println("wasUpdated: " + wasUpdated);
        
        assertEquals(true, wasUpdated);
        
    }
    
    
    /**
     * Test of read Morador
     */
    @Test
    public void teste3() {
        System.out.println("read Morador");
       
        Morador lido = instance.read(maxID);
        
        objMorador = new Morador(
                maxID,
                new Condominio(0),
                "Fulano",
                "(032) 9999-9999",
                "teste@teste.com",
                "111111111111",
                1,
                0,
                0
        );
        
        if(lido == null){
            fail("objMorador == null");
        } else {
            assertEquals(objMorador.getId(), lido.getId());
            //assertEquals(objMorador.getCondominio().getId(), lido.getCondominio().getId());
            assertEquals(objMorador.getNome(), lido.getNome());
            assertEquals(objMorador.getTelefone(), lido.getTelefone());
            assertEquals(objMorador.getEmail(), lido.getEmail());
            assertEquals(objMorador.getCpf(), lido.getCpf());
            assertEquals(objMorador.getBloco(), lido.getBloco());
            assertEquals(objMorador.getAndar(), lido.getAndar());
            assertEquals(objMorador.getApartamento(), lido.getApartamento());
        }
    }
    
    /**
     * Test of list Morador
     */
    @Test
    public void teste4() {
        System.out.println("read Morador");
       
        ArrayList<Morador> listado = instance.list(1);//lista do bloco 1
        Morador lido = null;
        
        objMorador = new Morador(
                maxID,
                new Condominio(0),
                "Fulano",
                "(032) 9999-9999",
                "teste@teste.com",
                "111111111111",
                1,
                0,
                0
        );
        
        if(listado == null){
            fail("ArrayList<Morador> == null");
        } else if(listado.isEmpty()){
            fail("ArrayList<Morador> está vazio");
        } else if(listado.get(0) == null){
            fail("ArrayList<Morador> listado.get(0) é nulo");
        } else {
            for(Morador m : listado){
                if(m.getId().equals(maxID)){
                    lido = m;
                    break;
                }
            }
            if(lido == null){
                fail("Morador não encontrado na lista");
            } else {
                assertEquals(objMorador.getId(), lido.getId());
                //assertEquals(objMorador.getCondominio().getId(), lido.getCondominio().getId());
                assertEquals(objMorador.getNome(), lido.getNome());
                assertEquals(objMorador.getTelefone(), lido.getTelefone());
                assertEquals(objMorador.getEmail(), lido.getEmail());
                assertEquals(objMorador.getCpf(), lido.getCpf());
                assertEquals(objMorador.getBloco(), lido.getBloco());
                assertEquals(objMorador.getAndar(), lido.getAndar());
                assertEquals(objMorador.getApartamento(), lido.getApartamento());
            }
        }
    }
    
    /**
     * Test of delete Morador
     */
    @Test
    public void teste5() {
        System.out.println("delete Morador");

        boolean wasDeleted = instance.delete(maxID);
         
        assertEquals(true, wasDeleted);
    }
    
}