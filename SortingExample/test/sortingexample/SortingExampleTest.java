/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sortingexample;

import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jeppjleemoritzled
 */
public class SortingExampleTest
{
    
    public SortingExampleTest()
    {
    }
    
    @BeforeClass
    public static void setUpClass()
    {
    }
    
    @AfterClass
    public static void tearDownClass()
    {
    }
    
    @Before
    public void setUp()
    {
    }
    
    @After
    public void tearDown()
    {
    }

    


    /**
     * Test of bubbleSort method, of class SortingExample.
     */
    @Test
    public void testBubbleSort()
    {
        
        System.out.println("bubbleSort");
        int[] input = new int[100];
        Random r = new Random(1337);
        for (int i = 0; i < input.length; i++)
        {
            input[i]= r.nextInt(20);
        }
        int[] expResult = input.clone();
        Arrays.sort(expResult);
        int[] result = SortingExample.bubbleSort(input);
        assertArrayEquals(expResult, result);
    }
    
}
