/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs2016btransactionexample;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
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
        Random r = new Random();
        while(true)
        {
            doTransaction(10000, r.nextFloat()*5000+100);
        }
        
    }
    
    public static void wait(int ms)
    {
        try
        {
            Thread.sleep(ms);
        }
        catch (InterruptedException ex)
        {
            Logger.getLogger(CS2016BTransactionExample.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void doTransaction(int customerId, float amount)
    {
        try(Connection con = ds.getConnection())
        {
            con.setAutoCommit(false); // Enable transactions
            con.setTransactionIsolation(
                    Connection.TRANSACTION_SERIALIZABLE);
            
            System.out.println("Current balance: " +
                    getBalance(customerId,con));
            
            System.out.println("Depositing " + amount);
            deposit(customerId, amount,con);

            System.out.println("Current balance: " +
                    getBalance(customerId,con));

            //withdraw(customerId, 846.83f,con);

            //System.out.println("Current balance: " +
            //        getBalance(customerId,con));
            try
            {
                con.commit();
            }
            catch (SQLServerException sqlse)
            {
                con.rollback();
            }
            catch (SQLException sqle)
            {
                con.rollback();
            }
            
        }
        catch (SQLException ex)
        {
            Logger.getLogger(CS2016BTransactionExample.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void deposit(int accountNumber, float amount,
            Connection con) throws SQLException
    {
        Float currentAmount = getBalance(accountNumber, con);
        Float newAmount= currentAmount + amount;
        wait(2000);
        updateBalance(accountNumber, newAmount, con);
    }
    
    public static void withdraw(int accountNumber, float amount,
            Connection con) throws SQLException
    {
        Float currentAmount = getBalance(accountNumber, con);
        Float newAmount= currentAmount - amount;
        wait(2000);
        updateBalance(accountNumber, newAmount, con);
    }
    
    public static void updateBalance(int accountNumber, 
            float balance, Connection con) throws SQLException
    {
            String sql = 
                "UPDATE customer SET balance=? WHERE accountnumber=?";
            
            PreparedStatement pstmt =
                    con.prepareStatement(sql);
            
            pstmt.setFloat(1, balance);
            pstmt.setInt(2, accountNumber);
            pstmt.execute();
    }
    
    public static Float getBalance(int accountNumber, Connection con) throws SQLException
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
    
    public static void setupDB()
    {
        ds.setDatabaseName("Bank");
        ds.setUser("java");
        ds.setPassword("java");
        ds.setServerName("192.168.56.100");
        ds.setPortNumber(1433);
    }
    
}
