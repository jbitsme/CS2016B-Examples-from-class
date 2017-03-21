/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs2016bfxmlandtransactions.DAL;

import cs2016bfxmlandtransactions.BE.BankAccount;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jeppjleemoritzled
 */
public abstract class DBAccountManager
{
    public abstract void updateBankAccount(BankAccount account);
    public abstract void addAccount(BankAccount account);
    public abstract void removeAccount(BankAccount account);
    public abstract BankAccount getBankAccount(int accountNumber);
    public abstract List<BankAccount> getAllAccount();
    
    protected final ConnectionManager cm
            = new ConnectionManager();

    protected void updateBankAccount(BankAccount account, Connection con) throws SQLException
    {
        String sql
                = "UPDATE Customer "
                + "SET balance=? "
                + "WHERE accountnumber=?";

        PreparedStatement pstmt
                = con.prepareStatement(sql);
        pstmt.setFloat(1, account.getBalance());
        pstmt.setInt(2, account.getAccountNumber());
        pstmt.execute();
    }

    protected void addAccount(BankAccount account, Connection con) throws SQLException
    {
        String sql = "INSERT INTO Customer(accountnumber, balance) VALUES(?, ?)";

        PreparedStatement pstmt
                = con.prepareStatement(sql);
        pstmt.setInt(1, account.getAccountNumber());
        pstmt.setFloat(2, account.getBalance());
        pstmt.execute();
    }

    protected void removeAccount(BankAccount account, Connection con) throws SQLException
    {
        String sql = "DELETE FROM Customer WHERE accountnumber=?";

        PreparedStatement pstmt
                = con.prepareStatement(sql);
        pstmt.setInt(1, account.getAccountNumber());
        pstmt.execute();
    }

    protected BankAccount getBankAccount(int accountNumber, Connection con) throws SQLException
    {
        BankAccount account;

        String sql
                = "SELECT * FROM Customer WHERE accountnumber=?";

        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setInt(1, accountNumber);

        ResultSet rs = pstmt.executeQuery();
        rs.next(); // Point to first result

        account = new BankAccount(accountNumber);
        account.setBalance(rs.getFloat("balance"));

        return account;

    }

    protected List<BankAccount> getAllAccount(Connection con) throws SQLException
    {
        List<BankAccount> accounts = new ArrayList();

        String sql
                = "SELECT * FROM Customer";

        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next())
        {
            BankAccount account
                    = new BankAccount(rs.getInt("accountnumber"));
            account.setBalance(rs.getFloat("balance"));
            accounts.add(account);
        }

        return accounts;

    }
}
