/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.entities.creatures;

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
public class CreatureTest {
    
    public CreatureTest() {
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
     * Test of shallowCopy method, of class Creature.
     */
    @Test
    public void testShallowCopy() {
        System.out.println("shallowCopy");
        Creature instance = null;
        Creature expResult = null;
        Creature result = instance.shallowCopy();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deepCopy method, of class Creature.
     */
    @Test
    public void testDeepCopy() {
        System.out.println("deepCopy");
        Creature instance = null;
        Creature expResult = null;
        Creature result = instance.deepCopy();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of move method, of class Creature.
     */
    @Test
    public void testMove() {
        System.out.println("move");
        Creature instance = null;
        instance.move();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of moveX method, of class Creature.
     */
    @Test
    public void testMoveX() {
        System.out.println("moveX");
        Creature instance = null;
        instance.moveX();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of moveY method, of class Creature.
     */
    @Test
    public void testMoveY() {
        System.out.println("moveY");
        Creature instance = null;
        instance.moveY();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of attack method, of class Creature.
     */
    @Test
    public void testAttack() {
        System.out.println("attack");
        Creature instance = null;
        instance.attack();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getHealth method, of class Creature.
     */
    @Test
    public void testGetHealth() {
        System.out.println("getHealth");
        Creature instance = null;
        int expResult = 0;
        int result = instance.getHealth();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setHealth method, of class Creature.
     */
    @Test
    public void testSetHealth() {
        System.out.println("setHealth");
        int health = 0;
        Creature instance = null;
        instance.setHealth(health);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSpeed method, of class Creature.
     */
    @Test
    public void testGetSpeed() {
        System.out.println("getSpeed");
        Creature instance = null;
        float expResult = 0.0F;
        float result = instance.getSpeed();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setSpeed method, of class Creature.
     */
    @Test
    public void testSetSpeed() {
        System.out.println("setSpeed");
        float speed = 0.0F;
        Creature instance = null;
        instance.setSpeed(speed);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addBombs method, of class Creature.
     */
    @Test
    public void testAddBombs() {
        System.out.println("addBombs");
        String type = "";
        Creature instance = null;
        instance.addBombs(type);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getBombs method, of class Creature.
     */
    @Test
    public void testGetBombs() {
        System.out.println("getBombs");
        Creature instance = null;
        Bomb expResult = null;
        Bomb result = instance.getBombs();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getBomb method, of class Creature.
     */
    @Test
    public void testGetBomb() {
        System.out.println("getBomb");
        Creature instance = null;
        String expResult = "";
        String result = instance.getBomb();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of putBomb method, of class Creature.
     */
    @Test
    public void testPutBomb() {
        System.out.println("putBomb");
        Creature instance = null;
        instance.putBomb();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

//    public class CreatureImpl extends Creature {
//
//        public CreatureImpl() {
//            super("", null, 0.0F, 0.0F, 0, 0, false);
//        }
//    }
    
}
