/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hashingexample;

import java.util.HashMap;
import java.util.HashSet;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author jeppjleemoritzled
 */
public class HashingExample
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        HashMap hm = new HashMap();
        HashSet hs = new HashSet();
        ConcurrentHashMap chm = new ConcurrentHashMap();
        
        for (int i = 0; i < 25000; i++)
        {
            hm.put(i, "Not the droid you are looking for");
        }
        
        
        hm.put(25000, "The droid you have been looking for ;)");
        for (int i = 25001; i < 1_000_000; i++)
        {
            hm.put(i, "Not the droid you are looking for");
        }
        
        System.out.println("Trying to find droid: " + hm.get(25000));
        
        // TODO code application logic here
    }
    
}
