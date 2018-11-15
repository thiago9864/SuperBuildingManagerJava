/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import dominio.Boleto;
import dominio.Condominio;
import dominio.Morador;
import dominio.StatusBoleto;
import java.util.ArrayList;
import java.sql.Date;
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
public class BoletoDAOTest {
    
    BoletoDAO instance;
    Integer maxID;
    Boleto objBoleto;
    
    public BoletoDAOTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        instance = new BoletoDAO();
        FactoryConnection factoryConn = new FactoryConnection();
        maxID = factoryConn.maxIDFromTable("boleto");
        
        System.out.println("idMorador: " + factoryConn.maxIDFromTable("morador").toString());
        System.out.println("idCondominio: " + factoryConn.maxIDFromTable("condominio").toString());
        System.out.println("idStatusBoleto: " + factoryConn.maxIDFromTable("status_boleto").toString());
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void teste1() {
        System.out.println("**** cria Boleto");
        Integer newID = maxID+1;
        
        objBoleto = new Boleto(
                newID,
                new Morador(0, new Condominio(0)),
                new StatusBoleto(0),                    
                "banco",
                "codigo",
                150.0f,//valor
                50.0f,//juros
                0.0f,//desconto
                0.0f,//multa
                new Date(115023),//data_vencimento
                false//is_segunda_via
        );
        
        Integer idInserted = instance.create(objBoleto);
        
        System.out.println("idInserted: " + idInserted);
        
        assertEquals(newID, idInserted);
    }
    

    @Test
    public void teste2() {
        System.out.println("**** update Boleto");

        objBoleto = new Boleto(
                maxID,
                new Morador(0, new Condominio(0)),
                new StatusBoleto(0),                    
                "Banco do Brasil",
                "AA384AFS154DS35F14D35CFDSD",
                350.0f,//valor
                180.0f,//juros
                15.0f,//desconto
                0.0f,//multa
                new Date(115023),//data_vencimento
                false//is_segunda_via
        );
        
        boolean wasUpdated = instance.update(objBoleto);
        
        System.out.println("wasUpdated: " + wasUpdated);
        
        assertEquals(true, wasUpdated);
        
    }
    
    

    @Test
    public void teste3() {
        System.out.println("**** read Boleto");
       
        Boleto lido = instance.read(maxID);
        
        
        objBoleto = new Boleto(
                maxID,
                new Morador(0, new Condominio(0)),
                new StatusBoleto(0, "nome", "descricao"),                    
                "Banco do Brasil",
                "AA384AFS154DS35F14D35CFDSD",
                350.0f,//valor
                180.0f,//juros
                15.0f,//desconto
                0.0f,//multa
                new Date(115023),//data_vencimento
                false//is_segunda_via
        );
        
        if(lido == null){
            fail("objBoleto == null");
        } else {
            assertEquals(objBoleto.getId(), lido.getId());
            assertEquals(objBoleto.getMorador().getId(), lido.getMorador().getId());
            assertEquals(objBoleto.getStatusBoleto().getId(), lido.getStatusBoleto().getId());
            assertEquals(objBoleto.getBanco(), lido.getBanco());
            assertEquals(objBoleto.getValor(), lido.getValor());
            assertEquals(objBoleto.getJuros(), lido.getJuros());
            assertEquals(objBoleto.getDesconto(), lido.getDesconto());
            assertEquals(objBoleto.getMulta(), lido.getMulta());
            //assertEquals(objBoleto.getDataVencimento(), lido.getDataVencimento());
            assertEquals(objBoleto.isSegundaVia(), lido.isSegundaVia());
        }
    }
    
    @Test
    public void teste4() {
        System.out.println("**** list Boleto");
       
        ArrayList<Boleto> listado = instance.list(0);//lista do bloco 1
        Boleto lido = null;
        
        objBoleto = new Boleto(
                maxID,
                new Morador(0, new Condominio(0)),
                new StatusBoleto(0, "nome", "descricao"),                    
                "Banco do Brasil",
                "AA384AFS154DS35F14D35CFDSD",
                350.0f,//valor
                180.0f,//juros
                15.0f,//desconto
                0.0f,//multa
                new Date(115023),//data_vencimento
                false//is_segunda_via
        );
        
        if(listado == null){
            fail("ArrayList<Boleto> == null");
        } else if(listado.isEmpty()){
            fail("ArrayList<Boleto> está vazio");
        } else if(listado.get(0) == null){
            fail("ArrayList<Boleto> listado.get(0) é nulo");
        } else {
            for(Boleto m : listado){
                if(m.getId().equals(maxID)){
                    lido = m;
                    break;
                }
            }
            if(lido == null){
                fail("Boleto não encontrado na lista");
            } else {
                assertEquals(objBoleto.getId(), lido.getId());
                assertEquals(objBoleto.getMorador().getId(), lido.getMorador().getId());
                assertEquals(objBoleto.getStatusBoleto().getId(), lido.getStatusBoleto().getId());
                assertEquals(objBoleto.getBanco(), lido.getBanco());
                assertEquals(objBoleto.getValor(), lido.getValor());
                assertEquals(objBoleto.getJuros(), lido.getJuros());
                assertEquals(objBoleto.getDesconto(), lido.getDesconto());
                assertEquals(objBoleto.getMulta(), lido.getMulta());
                //assertEquals(objBoleto.getDataVencimento(), lido.getDataVencimento());
                assertEquals(objBoleto.isSegundaVia(), lido.isSegundaVia());
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
