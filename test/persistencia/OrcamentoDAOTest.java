/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import dominio.Condominio;
import dominio.Orcamento;
import dominio.Sindico;
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
public class OrcamentoDAOTest {
    
    OrcamentoDAO instance;
    Integer maxID;
    Orcamento objOrcamento;
    
    public OrcamentoDAOTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        instance = new OrcamentoDAO();
        FactoryConnection factoryConn = new FactoryConnection();
        maxID = factoryConn.maxIDFromTable("orcamento");
        System.out.println("idCondominio: " + factoryConn.maxIDFromTable("orcamento").toString());
    }
    
    @After
    public void tearDown() {
    }


    @Test
    public void teste1() {
        System.out.println("**** criaOrcamento");
        Integer newID = maxID+1;
        
        objOrcamento = new Orcamento(
            newID,
            new Sindico(0, null),
            11, 
            2018,
            100.0f,
            400.0f,
            300.0f
        );
        
        Integer idInserted = instance.create(objOrcamento);
        
        System.out.println("idInserted: " + idInserted);
        
        assertEquals(newID, idInserted);
    }
    

    @Test
    public void teste2() {
        System.out.println("**** updateOrcamento");

        objOrcamento = new Orcamento(
            maxID,
            new Sindico(0, new Condominio(0)),
            9, 
            2018,
            500.0f,
            900.0f,
            100.0f
        );
        
        boolean wasUpdated = instance.update(objOrcamento);
        
        System.out.println("wasUpdated: " + wasUpdated);
        
        assertEquals(true, wasUpdated);
        
    }
    
    

    @Test
    public void teste3() {
        System.out.println("**** readOrcamento");
       
        Orcamento lido = instance.read(maxID);
        
        
        
        objOrcamento = new Orcamento(
            maxID,
            new Sindico(0, new Condominio(0)),
            9, 
            2018,
            500.0f,
            900.0f,
            100.0f
        );
        
        if(lido == null){
            fail("objOrcamento == null");
        } else {
            assertEquals(objOrcamento.getId(), lido.getId());
            assertEquals(objOrcamento.getSindico().getId(), lido.getSindico().getId());
            assertEquals(objOrcamento.getMes(), lido.getMes());
            assertEquals(objOrcamento.getAno(), lido.getAno());
            assertEquals(objOrcamento.getCusto(), lido.getCusto());
            assertEquals(objOrcamento.getRenda(), lido.getRenda());
            assertEquals(objOrcamento.getSaldo(), lido.getSaldo());
        }
    }
    
    @Test
    public void teste4() {
        System.out.println("**** list Orcamento");
       
        ArrayList<Orcamento> listado = instance.list(9, 2018);//lista do bloco 1
        Orcamento lido = null;
        
        objOrcamento = new Orcamento(
            maxID,
            new Sindico(0, new Condominio(0)),
            9, 
            2018,
            500.0f,
            900.0f,
            100.0f
        );
        
        if(listado == null){
            fail("ArrayList<Orcamento> == null");
        } else if(listado.isEmpty()){
            fail("ArrayList<Orcamento> está vazio");
        } else if(listado.get(0) == null){
            fail("ArrayList<Orcamento> listado.get(0) é nulo");
        } else {
            for(Orcamento m : listado){
                if(m.getId().equals(maxID)){
                    lido = m;
                    break;
                }
            }
            if(lido == null){
                fail("Orcamento não encontrado na lista");
            } else {
                assertEquals(objOrcamento.getId(), lido.getId());
                assertEquals(objOrcamento.getSindico().getId(), lido.getSindico().getId());
                assertEquals(objOrcamento.getMes(), lido.getMes());
                assertEquals(objOrcamento.getAno(), lido.getAno());
                assertEquals(objOrcamento.getCusto(), lido.getCusto());
                assertEquals(objOrcamento.getRenda(), lido.getRenda());
                assertEquals(objOrcamento.getSaldo(), lido.getSaldo());
            }
        }
    }
    
    @Test
    public void teste5() {
        System.out.println("**** delete Orcamento");

        boolean wasDeleted = instance.delete(maxID);
         
        assertEquals(true, wasDeleted);
    }
    
}
