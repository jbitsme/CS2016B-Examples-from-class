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
public class DBAccountManagerDirect extends DBAccountManager
{
    @Override
    public void updateBankAccount(BankAccount account)
    {
        try (Connection con = cm.getConnection())
        {
            addAccount(account, con);
        }
        catch (SQLException ex)
        {
            Logger.getLogger(DALFacade.class.getName()).log(Level.SEVERE, null, ex);
            throw new BankUserException("Could not update account " + account.getAccountNumber());
        }
    }

    @Override
    public void addAccount(BankAccount account)
    {
        try (Connection con = cm.getConnection())
        {
            addAccount(account, con);
        }
        catch (SQLException ex)
        {
            Logger.getLogger(DALFacade.class.getName()).log(Level.SEVERE, null, ex);
            throw new BankUserException("Could not add account " + account.getAccountNumber());
        }
    }

    @Override
    public void removeAccount(BankAccount account)
    {
        try (Connection con = cm.getConnection())
        {
            removeAccount(account, con);
        }
        catch (SQLException ex)
        {
            Logger.getLogger(DALFacade.class.getName()).log(Level.SEVERE, null, ex);
            
            throw new BankUserException("Could not remove account " + account.getAccountNumber());
        }
    }

    @Override
    public BankAccount getBankAccount(int accountNumber)
    {
        try (Connection con = cm.getConnection())
        {
            return getBankAccount(accountNumber, con);
        }
        catch (SQLException ex)
        {
            Logger.getLogger(DALFacade.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }

    public List<BankAccount> getAllAccount()
    {
        try (Connection con = cm.getConnection())
        {
            return getAllAccount(con);
        }
        catch (SQLException ex)
        {
            Logger.getLogger(DALFacade.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

}
