/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import dominio.StatusBoleto;
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
public class StatusBoletoDAOTest {
    
    StatusBoletoDAO instance;
    Integer maxID;
    StatusBoleto objStatusBoleto;
    
    public StatusBoletoDAOTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        instance = new StatusBoletoDAO();
        FactoryConnection factoryConn = new FactoryConnection();
        maxID = factoryConn.maxIDFromTable("status_boleto");
    }
    
    @After
    public void tearDown() {
    }

     @Test
    public void teste01() {
        System.out.println("**** cria StatusBoleto");
        
        
        objStatusBoleto = new StatusBoleto(
            -1,//campo não usado, pois essa tabela tem autoincrement
            "nome", 
            "descricao"
        );
        
        Integer idInserted = instance.create(objStatusBoleto);
        Integer nao_esperado = -1;
        
        System.out.println("idInserted: " + idInserted);
        
        assertNotEquals(nao_esperado, idInserted);
    }
    
    @Test
    public void teste02() {
        System.out.println("**** update StatusBoleto");

        objStatusBoleto = new StatusBoleto(
            maxID,
            "boleto", //nome
            "aluguel"
        );
        
        boolean wasUpdated = instance.update(objStatusBoleto);
        
        System.out.println("wasUpdated: " + wasUpdated);
        
        assertEquals(true, wasUpdated);
        
    }
    
    
    @Test
    public void teste03() {
        System.out.println("**** read StatusBoleto");
       
        StatusBoleto lido = instance.read(maxID);
        
        objStatusBoleto = new StatusBoleto(
            maxID,
            "boleto", //nome
            "aluguel" //descricao
        );
        
        if(lido == null){
            fail("objTipoFinanca == null");
        } else {
            assertEquals(objStatusBoleto.getId(), lido.getId());
            assertEquals(objStatusBoleto.getNome(), lido.getNome());
            assertEquals(objStatusBoleto.getDescricao(), lido.getDescricao());
        }
    }
    
    @Test
    public void teste04() {
        System.out.println("**** list StatusBoleto");
       
        ArrayList<StatusBoleto> listado = instance.list();
        StatusBoleto lido = null;
        
        objStatusBoleto = new StatusBoleto(
            maxID,
            "boleto", //nome
            "aluguel" //descricao
        );
        
        if(listado == null){
            fail("ArrayList<StatusBoleto> == null");
        } else if(listado.isEmpty()){
            fail("ArrayList<StatusBoleto> está vazio");
        } else if(listado.get(0) == null){
            fail("ArrayList<StatusBoleto> listado.get(0) é nulo");
        } else {
            for(StatusBoleto m : listado){
                if(m.getId().equals(maxID)){
                    lido = m;
                    break;
                }
            }
            if(lido == null){
                fail("TipoFinanca não encontrado na lista");
            } else {
                assertEquals(objStatusBoleto.getId(), lido.getId());
                assertEquals(objStatusBoleto.getNome(), lido.getNome());
                assertEquals(objStatusBoleto.getDescricao(), lido.getDescricao());
            }
        }
    }
    
    @Test
    public void teste05() {
        System.out.println("**** delete StatusBoleto");

        boolean wasDeleted = instance.delete(maxID);
         
        assertEquals(true, wasDeleted);
    }
    
}
