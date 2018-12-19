/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package singletones;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author nugal
 */
public class MySingletoneTest {
    
    public MySingletoneTest() {
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
     * Test of getInstance method, of class MySingletone.
     */
    @Test
    public void testGetInstance() {
        System.out.println("getInstance");
        MySingletone expResult = MySingletone.getInstance();
        MySingletone result = MySingletone.getInstance();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of activity method, of class MySingletone.
     */
    @Test
    public void testActivity() {
        System.out.println("activity");
        int addPoints = 1;
        MySingletone instance = MySingletone.getInstance();
        instance.activity(addPoints);
        assertEquals(1, instance.getPoints());
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of getPoints method, of class MySingletone.
     */
    @Test
    public void testGetPoints() {
        System.out.println("getPoints");
        MySingletone instance = MySingletone.getInstance();
        int expResult = 1;
        int result = instance.getPoints();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }
    
}
