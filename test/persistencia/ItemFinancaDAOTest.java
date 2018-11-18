/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import dominio.Gasto;
import dominio.ObjetoFinanceiro;
import dominio.Orcamento;
import dominio.Receita;
import dominio.TipoFinanca;
import java.util.ArrayList;
import java.util.Date;
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
 * @author Thiago
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ItemFinancaDAOTest {
    
    ItemFinancaDAO instance;
    Integer maxID;
    ObjetoFinanceiro objFinanceiro;
    
    public ItemFinancaDAOTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        instance = new ItemFinancaDAO();
        FactoryConnection factoryConn = new FactoryConnection();
        maxID = factoryConn.maxIDFromTable("item_financa");
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void teste01() {
        System.out.println("**** create ItemFinanca (Gasto)");
        Integer newIdGasto = maxID+1;
        
        //cria gasto
        Gasto objGasto = new Gasto(
            new Date(1234567),
            newIdGasto,
            new Orcamento(0),
            new TipoFinanca(1, false), 
            150.0f,
            "Um Gasto"
        );
        
        Integer idInserted = instance.create(objGasto);
        
        System.out.println("idInserted (Gasto): " + idInserted);
        
        assertEquals(newIdGasto, idInserted);
    }
    
    @Test
    public void teste02() {
        System.out.println("**** create ItemFinanca (Receita)");    
        Integer newIdReceita = maxID+1;
        
        //cria receita
        Receita objReceita = new Receita(
            new Date(1234567),
            newIdReceita,
            0,
            new Orcamento(0),
            new TipoFinanca(2, true), 
            250.0f,
            "Uma Receita"
        );
        
        Integer idInserted = instance.create(objReceita);
        
        System.out.println("idInserted (Receita): " + idInserted);
        
        assertEquals(newIdReceita, idInserted);
    }
    

    @Test
    public void teste03() {
        System.out.println("**** update ItemFinanca (Gasto)");
        Integer idGasto = maxID-1;
        
        Gasto objGasto = new Gasto(
            new Date(1234567),
            idGasto,
            new Orcamento(0),
            new TipoFinanca(1, false), 
            350.0f,
            "Um Gasto Editado"
        );
        
        boolean wasUpdated = instance.update(objGasto);

        assertEquals(true, wasUpdated);
    }
    
    @Test
    public void teste04() {
        System.out.println("**** update ItemFinanca (Receita)");
        Integer idReceita = maxID;
         
        Receita objReceita = new Receita(
            new Date(1234567),
            0,
            idReceita,
            new Orcamento(0),
            new TipoFinanca(2, true), 
            640.2f,
            "Uma Receita Editada"
        );
        
        boolean wasUpdated = instance.update(objReceita);

        assertEquals(true, wasUpdated);
        
    }
    
    

    @Test
    public void teste05() {
        System.out.println("**** read ItemFinanca (Gasto)");
        
        Integer idGasto = maxID-1;
        
        //testa leitura do gasto
        ObjetoFinanceiro lido = instance.read(idGasto);
        
        Gasto objGasto = new Gasto(
            new Date(1234567),
            idGasto,
            new Orcamento(0),
            new TipoFinanca(1, false), 
            350.0f,
            "Um Gasto Editado"
        );
        
        if(lido == null){
            fail("objGasto: lido == null");
        } else {
            assertEquals(objGasto.getId(), lido.getId());
            assertEquals(objGasto.getOrcamento().getId(), lido.getOrcamento().getId());
            assertEquals(objGasto.getValor(), lido.getValor());
            assertEquals(objGasto.getDescricao(), lido.getDescricao());
        }
    }
    
    @Test
    public void teste06() {
        System.out.println("**** read ItemFinanca (Receita)");
        
        Integer idReceita = maxID;
        //testa leitura da receita
        ObjetoFinanceiro lido = instance.read(idReceita);
        
        Receita objReceita = new Receita(
            new Date(1234567),
            0,
            idReceita,
            new Orcamento(0),
            new TipoFinanca(2, true), 
            640.2f,
            "Uma Receita Editada"
        );
        
        if(lido == null){
            fail("objReceita: lido == null");
        } else {
            assertEquals(objReceita.getId(), lido.getId());
            assertEquals(objReceita.getOrcamento().getId(), lido.getOrcamento().getId());
            assertEquals(objReceita.getValor(), lido.getValor());
            assertEquals(objReceita.getDescricao(), lido.getDescricao());
        }
    }
    
    @Test
    public void teste07() {
        System.out.println("**** list ItemFinanca <ObjetoFinanceiro> - Gasto");
       
        ArrayList<ObjetoFinanceiro> listado = instance.list(null, null);
        Gasto gastoLido = null;
        
        Integer idGasto = maxID-1;
        
        Gasto objGasto = new Gasto(
            new Date(1234567),
            idGasto,
            new Orcamento(0),
            new TipoFinanca(1, false), 
            350.0f,
            "Um Gasto Editado"
        );
        
        if(listado == null){
            fail("ArrayList<ObjetoFinanceiro> == null");
        } else if(listado.isEmpty()){
            fail("ArrayList<ObjetoFinanceiro> está vazio");
        } else if(listado.get(0) == null){
            fail("ArrayList<ObjetoFinanceiro> listado.get(0) é nulo");
        } else if(listado.get(1) == null){
            fail("ArrayList<ObjetoFinanceiro> listado.get(1) é nulo");
        } else {
            for(ObjetoFinanceiro o : listado){
                if(o.getId().equals(idGasto)){
                    gastoLido = (Gasto)o;
                    break;
                }
            }
            
            if(gastoLido == null){
                fail("Gasto não encontrado na lista");
            } else {
                assertEquals(objGasto.getId(), gastoLido.getId());
                assertEquals(objGasto.getOrcamento().getId(), gastoLido.getOrcamento().getId());
                assertEquals(objGasto.getValor(), gastoLido.getValor());
                assertEquals(objGasto.getDescricao(), gastoLido.getDescricao());
            }
            
        }
    }
    
    @Test
    public void teste08() {
        System.out.println("**** list ItemFinanca <ObjetoFinanceiro> - receita");
       
        ArrayList<ObjetoFinanceiro> listado = instance.list(null, null);
        Receita receitaLido = null;
        
        Integer idReceita = maxID;
        
        Receita objReceita = new Receita(
            new Date(1234567),
            0,
            idReceita,
            new Orcamento(0),
            new TipoFinanca(2, true), 
            640.2f,
            "Uma Receita Editada"
        );
        
        if(listado == null){
            fail("ArrayList<ObjetoFinanceiro> == null");
        } else if(listado.isEmpty()){
            fail("ArrayList<ObjetoFinanceiro> está vazio");
        } else if(listado.get(0) == null){
            fail("ArrayList<ObjetoFinanceiro> listado.get(0) é nulo");
        } else if(listado.get(1) == null){
            fail("ArrayList<ObjetoFinanceiro> listado.get(1) é nulo");
        } else {
            for(ObjetoFinanceiro o : listado){
                if(o.getId().equals(idReceita)){
                    receitaLido = (Receita)o;
                    break;
                }
            }
            
            if(receitaLido == null){
                fail("Receita não encontrada na lista");
            } else {
                assertEquals(objReceita.getId(), receitaLido.getId());
                assertEquals(objReceita.getOrcamento().getId(), receitaLido.getOrcamento().getId());
                assertEquals(objReceita.getValor(), receitaLido.getValor());
                assertEquals(objReceita.getDescricao(), receitaLido.getDescricao());
            }
        }
    }
    
    @Test
    public void teste09() {
        System.out.println("**** list ItemFinanca <Gasto>");
       
        ArrayList<Gasto> listado = instance.listGasto(null, null);//lista do bloco 1
        ObjetoFinanceiro gastoLido = null;
        
        Integer idGasto = maxID-1;
        
        Gasto objGasto = new Gasto(
            new Date(1234567),
            idGasto,
            new Orcamento(0),
            new TipoFinanca(1, false), 
            350.0f,
            "Um Gasto Editado"
        );

        if(listado == null){
            fail("ArrayList<ObjetoFinanceiro> == null");
        } else if(listado.isEmpty()){
            fail("ArrayList<ObjetoFinanceiro> está vazio");
        } else if(listado.get(0) == null){
            fail("ArrayList<ObjetoFinanceiro> listado.get(0) é nulo");
        } else {
            for(Gasto o : listado){
                if(o.getId().equals(idGasto)){
                    gastoLido = o;
                    break;
                }
            }
            
            if(gastoLido == null){
                fail("Gasto não encontrado na lista");
            } else {
                assertEquals(objGasto.getId(), gastoLido.getId());
                assertEquals(objGasto.getOrcamento().getId(), gastoLido.getOrcamento().getId());
                assertEquals(objGasto.getValor(), gastoLido.getValor());
                assertEquals(objGasto.getDescricao(), gastoLido.getDescricao());
            }
        }
    }
    
    
    @Test
    public void teste10() {
        System.out.println("**** list ItemFinanca <Receita>");
       
        ArrayList<Receita> listado = instance.listReceita(null, null);//lista do bloco 1
        Receita receitaLido = null;
        
        Integer idReceita = maxID;
        
        Receita objReceita = new Receita(
            new Date(1234567),
            0,
            idReceita,
            new Orcamento(0),
            new TipoFinanca(2, true), 
            640.2f,
            "Uma Receita Editada"
        );
        
        if(listado == null){
            fail("ArrayList<Receita> == null");
        } else if(listado.isEmpty()){
            fail("ArrayList<Receita> está vazio");
        } else if(listado.get(0) == null){
            fail("ArrayList<Receita> listado.get(0) é nulo");
        } else if(listado.get(1) == null){
            fail("ArrayList<Receita> listado.get(1) é nulo");
        } else {
            for(Receita o : listado){
                if(o.getId().equals(idReceita)){
                    receitaLido = o;
                }
            }

            if(receitaLido == null){
                fail("Receita não encontrada na lista");
            } else {
                assertEquals(objReceita.getId(), receitaLido.getId());
                assertEquals(objReceita.getOrcamento().getId(), receitaLido.getOrcamento().getId());
                assertEquals(objReceita.getValor(), receitaLido.getValor());
                assertEquals(objReceita.getDescricao(), receitaLido.getDescricao());
            }
        }
    }
    
    @Test
    public void teste11() {
        System.out.println("**** delete ItemFinanca - Gasto");

        Integer idGasto = maxID-1;
        
        boolean wasDeleted = instance.delete(idGasto);
        assertEquals(true, wasDeleted);
        
    }
    
    @Test
    public void teste12() {
        System.out.println("**** delete ItemFinanca - Receita");

        Integer idReceita = maxID;
        
        boolean wasDeleted = instance.delete(idReceita);
        assertEquals(true, wasDeleted);
    }
    
}
