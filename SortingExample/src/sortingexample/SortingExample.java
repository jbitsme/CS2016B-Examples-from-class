/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sortingexample;

import java.util.Arrays;
import java.util.Random;

/**
 *
 * @author jeppjleemoritzled
 */
public class SortingExample
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        int[] numbers = new int[11];
        Random r = new Random(1337);
        for (int i = 0; i < numbers.length; i++)
        {
            numbers[i]= r.nextInt(20);
        }
        
        int[] sortedNumbers = bubbleSort(numbers);
        
        printArray(numbers);
        System.out.println("Sorted: ");
        printArray(sortedNumbers);
    }
    
    public static void printArray(int[] input)
    {
        for (int i = 0; i < input.length; i++)
        {
            System.out.print(input[i] + " ");
        }
    }
    
    public static int[] bubbleSort(int[] input)
    {
        // Needs implementation
        int[] newArray = input.clone();
        
        return input;
    }
    
    public static int[] selectionSort(int[] input)
    {
        // Needs implementation
        int[] newArray = input.clone();
        
        return input;
    }
    
    public static int[] insertionSort(int[] input)
    {
        // Needs implementation
        int[] newArray = input.clone();
        
        return input;
    }
    
}
