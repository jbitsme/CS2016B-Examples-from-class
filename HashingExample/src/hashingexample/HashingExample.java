/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hashingexample;

import java.util.ArrayList;
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
        
        HashMap hm = new HashMap(25_000_000);
        HashSet hs = new HashSet();
        ConcurrentHashMap chm = new ConcurrentHashMap();
        
        hm.put("MyKeyNumber: " + 20_250_001, "The droid you have been looking for ;)");
        for (int i = 0; i < 20_250_000; i++)
            hm.put("MyKeyNumber: " + i, "Not the droid you are looking for");
        
        ArrayList<String> al = new ArrayList(hs);
        for (int i = 0; i < 20_250_001; i++)
            al.add("Not the droid you are looking for");
        al.add("The droid you have been looking for ;)");
        
        
        System.out.println("Fetching from hashmap:");
        long start = System.currentTimeMillis();
        System.out.println("Trying to find droid: " + hm.get("MyKeyNumber: " + 2_250_001));
        System.out.println("Time elapsed: " + (System.currentTimeMillis()-start)/1000f + " sek");
        
        
        System.out.println("Fetching from ArrayList:");
        long starta = System.currentTimeMillis();
        System.out.print("Trying to find droid: ");
        long lookups=0;

        for (String str : al)
        {
            lookups++;
            if(str.equals("The droid you have been looking for ;)"))
            {
                System.out.println(str);
                break;
            }
        }
        System.out.println("Lookups: " + lookups + " " + (System.currentTimeMillis()-starta)/1000f + " sek");
        
    }
    
}
