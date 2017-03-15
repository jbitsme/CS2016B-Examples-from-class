/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs2016btransactionexample;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jeppjleemoritzled
 */
public class CS2016BTransactionExample
{

    /**
     * @param args the command line arguments
     */
    private static SQLServerDataSource ds =
            new SQLServerDataSource();
    
    public static void main(String[] args)
    {
        setupDB();
        System.out.println("Balance before: "+
                getBalance(10000));
        
        Float startBalance = getBalance(10000);
        float newBalance = startBalance - 1000;
        updateBalance(10000, newBalance);
        
        System.out.println("Balance after: "+
                getBalance(10000));
        // Get balance *
        // Subtract from balance
        // update balance *
    }
    
    public static void deposit(int accountNumber, float amount)
    {
        
    }
    
    public static void withdraw(int accountNumber, float amount)
    {
        
    }
    
    public static void updateBalance(int accountNumber, 
            float balance)
    {
        try (Connection con = ds.getConnection())
        {
            String sql = 
                "UPDATE customer SET balance=? WHERE accountnumber=?";
            
            PreparedStatement pstmt =
                    con.prepareStatement(sql);
            
            pstmt.setFloat(1, balance);
            pstmt.setInt(2, accountNumber);
            pstmt.execute();
        }
        catch (SQLException ex)
        {
            Logger.getLogger(CS2016BTransactionExample.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static Float getBalance(int accountNumber)
    {
        try(Connection con = ds.getConnection())
        {
            String sql = 
                "SELECT balance FROM Customer WHERE accountnumber=?";
            PreparedStatement pstmt =
               con.prepareStatement(sql);
            
            pstmt.setInt(1, accountNumber);
            
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            return rs.getFloat("balance");
        }
        catch (SQLException ex)
        {
            Logger.getLogger(CS2016BTransactionExample.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
    }
    
    public static void setupDB()
    {
        ds.setDatabaseName("Bank");
        ds.setUser("java");
        ds.setPassword("java");
        ds.setServerName("192.168.56.100");
        ds.setPortNumber(1433);
    }
    
}
