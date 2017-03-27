/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sortingexample;

import java.util.Arrays;
import java.util.Random;
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
    
    /**
     * Test of bubbleSort method, of class SortingExample.
     */
    @Test
    public void testBubbleSort()
    {
        System.out.println("bubbleSort");
        testSort(SortSelector.BUBBLE);
    }
    /**
     * Test of selectionSort method, of class SortingExample.
     */
    @Test
    public void testSelectionSort()
    {
        System.out.println("selectionSort");
        testSort(SortSelector.SELECTION);
    }

    /**
     * Test of insertionSort method, of class SortingExample.
     */
    @Test
    public void testInsertionSort()
    {
        System.out.println("insertionSort");
        testSort(SortSelector.INSERTION);
    }
    
    private void testSort(SortSelector selected)
    {
        int[] input = new int[100];
        Random r = new Random(1337);
        for (int i = 0; i < input.length; i++)
        {
            input[i]= r.nextInt(20);
        }
        int[] result = null;
        switch(selected)
        {
            case BUBBLE:
                result = SortingExample.bubbleSort(input);
                break;
            case SELECTION:
                result = SortingExample.selectionSort(input);
                break;
            case INSERTION:
                result = SortingExample.insertionSort(input);
                break;
        }
        int[] expResult = input.clone();
        Arrays.sort(expResult);
        assertArrayEquals(expResult, result);
    }
    
    private enum SortSelector
    {
        BUBBLE, SELECTION, INSERTION
    }
    
}
