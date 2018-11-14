/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import dominio.Morador;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author thiagoalmeida
 */
public class MoradoresDAOTest {
    
    public MoradoresDAOTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of create method, of class MoradoresDAO.
     */
    @Test
    public void testCreate() {
        System.out.println("create");
        Morador morador = null;
        MoradoresDAO instance = new MoradoresDAO();
        Integer expResult = null;
        Integer result = instance.create(morador);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of read method, of class MoradoresDAO.
     */
    @Test
    public void testRead() {
        System.out.println("read");
        Integer id = null;
        MoradoresDAO instance = new MoradoresDAO();
        Morador expResult = null;
        Morador result = instance.read(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of list method, of class MoradoresDAO.
     */
    @Test
    public void testList() {
        System.out.println("list");
        Integer bloco = null;
        MoradoresDAO instance = new MoradoresDAO();
        ArrayList<Morador> expResult = null;
        ArrayList<Morador> result = instance.list(bloco);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of update method, of class MoradoresDAO.
     */
    @Test
    public void testUpdate() {
        System.out.println("update");
        Morador morador = null;
        MoradoresDAO instance = new MoradoresDAO();
        boolean expResult = false;
        boolean result = instance.update(morador);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of delete method, of class MoradoresDAO.
     */
    @Test
    public void testDelete() {
        System.out.println("delete");
        Integer id = null;
        MoradoresDAO instance = new MoradoresDAO();
        boolean expResult = false;
        boolean result = instance.delete(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
