/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs2016bfxmlandtransactions.DAL;

import cs2016bfxmlandtransactions.BE.BankAccount;
import cs2016bfxmlandtransactions.Util.BankUserException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jeppjleemoritzled
 */
public class DBAccountManagerTransactions extends DBAccountManager implements AutoCloseable
{
    private Connection transactionConnection;

    public void startTransaction()
    {
        try
        {
            transactionConnection = cm.getConnection();
            transactionConnection.setAutoCommit(false);
            transactionConnection.setTransactionIsolation(
                    Connection.TRANSACTION_SERIALIZABLE);
        }
        catch (SQLException ex)
        {
            Logger.getLogger(DALFacade.class.getName()).log(Level.SEVERE, null, ex);
            throw new BankUserException("Cannot start transaction");
        }

    }

    public void commitTransaction()
    {
        try
        {
            transactionConnection.commit();
        }
        catch (SQLException sqle)
        {
            try
            {
                transactionConnection.rollback();
            }
            catch (SQLException ex)
            {
                Logger.getLogger(DALFacade.class.getName()).log(Level.SEVERE, null, ex);
                throw new BankUserException("Problems with rollback");
            }
            Logger.getLogger(DALFacade.class.getName()).log(Level.SEVERE, null, sqle);
        }
    }

    @Override
    public void updateBankAccount(BankAccount account)
    {
        try
        {
            super.updateBankAccount(account, transactionConnection);
        }
        catch (SQLException ex)
        {
            Logger.getLogger(DBAccountManagerTransactions.class.getName()).log(Level.SEVERE, null, ex);
            throw new BankUserException("Could not update account "  + account.getAccountNumber());
        }
    }

    @Override
    public void addAccount(BankAccount account)
    {
        try
        {
            super.addAccount(account, transactionConnection);
        }
        catch (SQLException ex)
        {
            Logger.getLogger(DBAccountManagerTransactions.class.getName()).log(Level.SEVERE, null, ex);
            throw new BankUserException("Could not add account "  + account.getAccountNumber());
        }
    }

    @Override
    public void removeAccount(BankAccount account)
    {
        try
        {
            super.removeAccount(account, transactionConnection);
        }
        catch (SQLException ex)
        {
            Logger.getLogger(DBAccountManagerTransactions.class.getName()).log(Level.SEVERE, null, ex);
            throw new BankUserException("Could not remove account " + account.getAccountNumber());
        }
    }

    @Override
    public BankAccount getBankAccount(int accountNumber)
    {
        try
        {
            return super.getBankAccount(accountNumber, transactionConnection);
        }
        catch (SQLException ex)
        {
            Logger.getLogger(DBAccountManagerTransactions.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public List<BankAccount> getAllAccount()
    {
        try
        {
            return super.getAllAccount(transactionConnection);
        }
        catch (SQLException ex)
        {
            Logger.getLogger(DBAccountManagerTransactions.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public void close() throws Exception
    {
        transactionConnection.close();
    }
}
