/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import dominio.TipoFinanca;
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
public class TipoFinancaDAOTest {
    
    TipoFinancaDAO instance;
    Integer maxID;
    TipoFinanca objTipoFinanca;
    
    public TipoFinancaDAOTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        instance = new TipoFinancaDAO();
        FactoryConnection factoryConn = new FactoryConnection();
        maxID = factoryConn.maxIDFromTable("tipo_financa");
    }
    
    @After
    public void tearDown() {
    }


    @Test
    public void teste01() {
        System.out.println("**** cria ItemFinanca");
        
        
        objTipoFinanca = new TipoFinanca(
            -1,//campo não usado, pois essa tabela tem autoincrement
            "nome", 
            "descricao",
            false//is_renda
        );
        
        Integer idInserted = instance.create(objTipoFinanca);
        Integer nao_esperado = -1;
        
        System.out.println("idInserted: " + idInserted);
        
        assertNotEquals(nao_esperado, idInserted);
    }
    
    @Test
    public void teste02() {
        System.out.println("**** update ItemFinanca");

        objTipoFinanca = new TipoFinanca(
            maxID,
            "boleto", //nome
            "aluguel", //descricao
            true//is_renda
        );
        
        boolean wasUpdated = instance.update(objTipoFinanca);
        
        System.out.println("wasUpdated: " + wasUpdated);
        
        assertEquals(true, wasUpdated);
        
    }
    
    
    @Test
    public void teste03() {
        System.out.println("**** read TipoFinanca");
       
        TipoFinanca lido = instance.read(maxID);
        
        objTipoFinanca = new TipoFinanca(
            maxID,
            "boleto", //nome
            "aluguel", //descricao
            true//is_renda
        );
        
        if(lido == null){
            fail("objTipoFinanca == null");
        } else {
            assertEquals(objTipoFinanca.getId(), lido.getId());
            assertEquals(objTipoFinanca.getNome(), lido.getNome());
            assertEquals(objTipoFinanca.getDescricao(), lido.getDescricao());
            assertEquals(objTipoFinanca.isRenda(), lido.isRenda());
        }
    }
    
    @Test
    public void teste04() {
        System.out.println("**** list TipoFinanca");
       
        ArrayList<TipoFinanca> listado = instance.list();//lista do bloco 1
        TipoFinanca lido = null;
        
        objTipoFinanca = new TipoFinanca(
            maxID,
            "boleto", //nome
            "aluguel", //descricao
            true//is_renda
        );
        
        if(listado == null){
            fail("ArrayList<TipoFinanca> == null");
        } else if(listado.isEmpty()){
            fail("ArrayList<TipoFinanca> está vazio");
        } else if(listado.get(0) == null){
            fail("ArrayList<TipoFinanca> listado.get(0) é nulo");
        } else {
            for(TipoFinanca m : listado){
                if(m.getId().equals(maxID)){
                    lido = m;
                    break;
                }
            }
            if(lido == null){
                fail("TipoFinanca não encontrado na lista");
            } else {
                assertEquals(objTipoFinanca.getId(), lido.getId());
                assertEquals(objTipoFinanca.getNome(), lido.getNome());
                assertEquals(objTipoFinanca.getDescricao(), lido.getDescricao());
                assertEquals(objTipoFinanca.isRenda(), lido.isRenda());
            }
        }
    }
    
    @Test
    public void teste05() {
        System.out.println("**** delete ItemFinanca");

        boolean wasDeleted = instance.delete(maxID);
         
        assertEquals(true, wasDeleted);
    }
    
}
